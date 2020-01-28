package com.example.payPalService.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.payPalService.converter.PayPalConverter;
import com.example.payPalService.domain.Order;
import com.example.payPalService.domain.UserPayPal;
import com.example.payPalService.dto.BillingAgreementDTO;
import com.example.payPalService.dto.BillingPlanDTO;
import com.example.payPalService.dto.PaymentDTO;
import com.example.payPalService.dto.UserPayPalDTO;
import com.example.payPalService.service.PayPalService;
import com.example.payPalService.service.UserPayPalService;
import com.paypal.api.payments.MerchantInfo;

@RestController
@CrossOrigin("https://localhost:1234")
public class PayPalController {
	
	@Autowired
	private PayPalService payPalService;
	
	@Autowired
	private UserPayPalService userService;
	
	@Autowired
	private PayPalConverter converter;
	
	@RequestMapping(path = "/create/{orderId}", method = RequestMethod.POST, produces = "text/plain")
	@ResponseBody
	public String createPayment(@PathVariable String orderId, @RequestBody PaymentDTO payment){
		
		Map<String, Object> response = payPalService.createPayment(payment, orderId);
		
		
		return  (String)response.get("redirect_url");
	}
	
	@RequestMapping(path = "/complete/{paymentId}/{PayerID}/{username}", method =  RequestMethod.GET,  produces = "text/plain")
	public String completePayment(@PathVariable String paymentId, @PathVariable String PayerID,@PathVariable String username) {
		
		Map<String,Object> response = payPalService.completePayment(paymentId, PayerID,username);
		String redirectionUrl = (String) response.get("redirect_url");
		//System.out.println("Dobijenooo: " + redirectionUrl);
		return redirectionUrl;
		
	}
	
	@RequestMapping(path="/cancel/{oid}", method = RequestMethod.GET, produces = "text/plain")
	public String setStatusToCanceled(@PathVariable Long oid)
	{
		return this.payPalService.changePaymentStatusToCanceled(oid);
	}
	
	@RequestMapping(
			path="addUser",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addNewUser(@RequestBody UserPayPalDTO user) {
		
		
		UserPayPal userP = converter.convert(user);
		
		this.userService.saveUser(userP);
		
		return new ResponseEntity<>("User added to payPal!",HttpStatus.OK);
		
	}

	
	//za refresh iz kp-a
	@RequestMapping(value="/getOne/{oid}", method = RequestMethod.GET)
	 public ResponseEntity<Map<String, Object>> getPayment(@PathVariable("oid") Long oid) {
		 	System.out.println("usao u getOne payment " + oid);
		 	
		 	Map<String, Object> o = payPalService.getByOrderId(oid);

		 	
		 	return new ResponseEntity<Map<String, Object>>(o, HttpStatus.ACCEPTED);
	 }

	@RequestMapping(
			value="/createPlan",
			method = RequestMethod.POST)
	public ResponseEntity createBillingPlan(@RequestBody BillingPlanDTO planDTO) {
		
		String ret = payPalService.createPlan(planDTO);
		
		if(!ret.equals("Error"))
		{
			return new ResponseEntity<>( ret, HttpStatus.OK);
		}else
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(
			value="/createAgreement",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createAgreement(@RequestBody BillingAgreementDTO agrDTO) {
		
		
		UserPayPal user = userService.getUserByUsername(agrDTO.getMerchantUsername());
		
		String ret =  payPalService.createUserAgreement(agrDTO.getPlanId(), user);
		
		if(ret.equals("Error"))
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else
		{
			return new ResponseEntity<>(ret, HttpStatus.OK);
		}
	}
	
	@RequestMapping(
			value="/activateAgreement",
			method = RequestMethod.GET)
	public ResponseEntity activateAgr(@RequestParam("token") String token,@RequestParam("username")String username
										,@RequestParam("callbackUrl")String callbackUrl) {
		
		System.out.println("Aktivacijaa ");
		payPalService.activateAgreement(token, callbackUrl, username);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

}
