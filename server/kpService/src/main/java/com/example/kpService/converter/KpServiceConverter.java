package com.example.kpService.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.kpService.domain.Merchant;
import com.example.kpService.domain.MerchantSystem;
import com.example.kpService.domain.MethodOfPayment;
import com.example.kpService.domain.MethodOfPaymentFields;
import com.example.kpService.domain.SubscriptionPlan;
import com.example.kpService.dto.MerchantSubmissionDTO;
import com.example.kpService.dto.MerchantSystemDTO;
import com.example.kpService.dto.MethodOfPaymentDTO;
import com.example.kpService.dto.MethodOfPaymentFieldsDTO;
import com.example.kpService.dto.SubscriptionParamsDTO;
import com.example.kpService.service.MerchantSystemService;
import com.example.kpService.service.MethodOfPaymentService;

@Component
public class KpServiceConverter {
	
	@Autowired
	private MethodOfPaymentService methodService;
	
	@Autowired 
	private MerchantSystemService systemService;
	
	
	public Merchant convert(MerchantSubmissionDTO dto) {
		
		List<MethodOfPayment> payments = new ArrayList<MethodOfPayment>();
		MerchantSystem company = systemService.findByName(dto.getCompanyName());
		
		for(MethodOfPaymentDTO m : dto.getSupportedMethods()) {
			
			payments.add(methodService.getOneByName(m.getName()));
			
		}
		
		return new Merchant(dto.getCompanyName(), dto.getUsername(), company, payments);
	}
	
	public MerchantSubmissionDTO convert(Merchant merchant) {
		
		return new MerchantSubmissionDTO(merchant.getSystem().getSystemName(), 
				merchant.getName(), merchant.getUsername(), 
				this.convert(merchant.getSystem()),
				this.convertMethods(merchant.getPaymentMethods()),
				this.convertSubscriptions(merchant.getSubscriptions()));
	}
	
	
	public MethodOfPaymentDTO convert(MethodOfPayment method) {
		
		return new MethodOfPaymentDTO(method.getId(), method.getName(), method.getPath(), this.convertFields(method.getFields()));
	}
	
	public MerchantSystemDTO convert(MerchantSystem sys) {
		
		return new MerchantSystemDTO(sys.getId(), sys.getSystemName(), sys.getFrontUrl(), sys.getBackUrl());
	}
	
	public MethodOfPaymentFieldsDTO convert(MethodOfPaymentFields field)
	{
		return new MethodOfPaymentFieldsDTO(field.getCode(),field.getName(),field.getType(),null);
	}
	
	
	public List<MethodOfPaymentDTO> convertMethods(List<MethodOfPayment> list){
	
		List<MethodOfPaymentDTO> ret = new ArrayList<MethodOfPaymentDTO>();
		
		for(MethodOfPayment method : list)
		{
			ret.add(this.convert(method));
		}
		
		return ret;
	}
	
	
	
	public List<MethodOfPaymentFieldsDTO> convertFields(List<MethodOfPaymentFields> list){
		
		List<MethodOfPaymentFieldsDTO> ret = new ArrayList<MethodOfPaymentFieldsDTO>();
		
		for(MethodOfPaymentFields field : list)
		{
			ret.add(this.convert(field));
		}
		
		return ret;
	}
	
	public List<SubscriptionParamsDTO> convertSubscriptions(List<SubscriptionPlan> subs){
		
		List<SubscriptionParamsDTO> ret = new ArrayList<SubscriptionParamsDTO>();
		
		for(SubscriptionPlan s : subs) {
			
			ret.add(new SubscriptionParamsDTO(s.getId(),s.getMerchant().getUsername(),s.getPeriod(),s.getFrequency(),s.getPrice(), s.getPlanId()));
			
		}
		
		return ret;
	}

}
