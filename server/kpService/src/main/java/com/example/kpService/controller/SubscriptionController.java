package com.example.kpService.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.kpService.converter.KpServiceConverter;
import com.example.kpService.domain.Merchant;
import com.example.kpService.domain.Subscription;
import com.example.kpService.domain.SubscriptionPeriod;
import com.example.kpService.domain.SubscriptionPlan;
import com.example.kpService.dto.BillingAgreementDTO;
import com.example.kpService.dto.BillingPlanDTO;
import com.example.kpService.dto.SubscriptionParamsDTO;
import com.example.kpService.dto.SubscriptionSCenterDTO;
import com.example.kpService.service.MerchantService;
import com.example.kpService.service.SubscriptionPlanService;
import com.example.kpService.service.SubscriptionService;

import javassist.expr.NewArray;

@RestController
@RequestMapping("sub")
@CrossOrigin("https://localhost:1234")
public class SubscriptionController {

	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private KpServiceConverter converter;
	
	@Autowired
	private SubscriptionPlanService subPlanService;
	
	@Autowired
	private SubscriptionService subService;
	
	private String zuulUrl = "https://localhost:8086/";
	
	
	@RequestMapping(
			value="/save",
			method = RequestMethod.POST)
	public ResponseEntity saveSubscriptionPlan(@RequestBody SubscriptionParamsDTO subDTO) {
	
		Merchant merchant = merchantService.getByUsername(subDTO.getMerchantUsername());
		
		if(merchant == null)
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		SubscriptionPlan sub = new SubscriptionPlan(subDTO.getSubPeriod(),subDTO.getFrequency(),subDTO.getSubPrice(),null,merchant);
		SubscriptionPlan retSub = subPlanService.saveSubscritpion(sub); 
		
		
		BillingPlanDTO plan = new BillingPlanDTO(merchant.getUsername(),retSub.getId(),subDTO.getSubPeriod(),subDTO.getFrequency(),subDTO.getSubPrice(),
				this.zuulUrl+"kpService/sub/changeInfo/"+retSub.getId(), this.zuulUrl+"kpService/sub/changeInfoAgr/"+retSub.getId());
		
		RestTemplate template = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<BillingPlanDTO> entity = new HttpEntity<BillingPlanDTO>(plan, header);
		String  ret = template.postForObject(this.zuulUrl + "payPalService/createPlan",entity,String.class);
		
		RestTemplate template2 = new RestTemplate();
		
		template2.getForEntity(ret, null, String.class); 
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/changeInfo/{id}/{id2}",
			method = RequestMethod.GET)
	public ResponseEntity changeInfo(@PathVariable("id") Long id, @PathVariable("id2") String planId) {
		
		SubscriptionPlan subscription = subPlanService.getById(id);
		subscription.setPlanId(planId);
		
		subPlanService.saveSubscritpion(subscription);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/changeInfoAgr/{id}/{id2}",
			method = RequestMethod.GET)
	public ResponseEntity changeInfoAfterAgreement(@PathVariable("id")Long subId,@PathVariable("id2") String agreementId) {
		
		SubscriptionPlan plan = subPlanService.getById(subId);
		
		Subscription subscription = subService.getByPlanId(plan.getPlanId());
		
		subscription.setAgreementId(agreementId);
		subscription.setActive(true);
		subService.saveSub(subscription);
		
		//azuriranje info u NC
		RestTemplate template = new RestTemplate();
		String ret = plan.getMerchant().getSystem().getBackUrl() + "/subscription/changeInfo/"+plan.getMerchant().getUsername() + "/" + subscription.getSubEmail();
		
		try {
			template.getForEntity(ret, null, String.class); 
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(HttpStatusCodeException e)
		{
			System.out.println("Greska prilikom javljanja Nc");
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(
			value="/subscribeTo/{id}/{id2}/{id3}/",
			method = RequestMethod.GET,  produces = "text/plain")
	public String subscribeToJournal(@PathVariable("id") String username, @PathVariable("id2") String planId, @PathVariable("id3") String subEmail) {
		
		Merchant m = merchantService.getByUsername(username);
		
		if(m == null)
		{
			
			return null;
		}
		
		
		SubscriptionPlan sub = subPlanService.getByMerchantAndPlan(m.getId(), planId);
		
		if(sub == null)
		{
			return null;
		}else
		{
			Subscription subscription =  new Subscription(m.getUsername(),sub.getPrice(),planId,false,null,subEmail);
			subService.saveSub(subscription);
			
			String ret="";
				
			RestTemplate template = new RestTemplate();
			HttpHeaders header = new HttpHeaders();
			BillingAgreementDTO agr = new BillingAgreementDTO(sub.getPlanId(), sub.getMerchant().getUsername());
			header.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<BillingAgreementDTO> entity = new HttpEntity<BillingAgreementDTO>(agr, header);
			
			try {
				//kreiranje ugovora
				ret = template.postForObject(this.zuulUrl + "payPalService/createAgreement",entity,String.class);
				
				// NC
				SubscriptionSCenterDTO subSC = new SubscriptionSCenterDTO(this.convertToEnum(sub.getPeriod()), sub.getFrequency(), sub.getPrice(), false, subEmail, sub.getMerchant().getUsername());
				HttpEntity<SubscriptionSCenterDTO> entity2 = new HttpEntity<SubscriptionSCenterDTO>(subSC, header);
				String url = sub.getMerchant().getSystem().getBackUrl() + "/subscription/createSubscription";
				
				try {
					template.postForLocation(url, entity2, String.class);
					return ret;
				}catch(HttpStatusCodeException e1)
				{
					System.out.println("Greska prilikom upisivanja podataka u NC");
				}
				
				
			}catch (HttpStatusCodeException e) {
				// TODO: handle exception
				System.out.println("Greska prilikom upisivanja podataka u kp servis");
			}
			
			return null;
	
		}
		
		
	}
	
	public SubscriptionPeriod convertToEnum(String s) {
		
		if(s.equals("DAY"))
		{
			return SubscriptionPeriod.DAY;
		}
		
		if(s.equals("WEEK"))
		{
			return SubscriptionPeriod.WEEK;
		}
		
		if(s.equals("MONTH"))
		{
			return SubscriptionPeriod.MONTH;
		}
		
		if(s.equals("YEAR"))
		{
			return SubscriptionPeriod.YEAR;
		}
		
		return null;
	}
	
}
