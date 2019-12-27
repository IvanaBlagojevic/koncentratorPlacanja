package com.example.payPalService.controller;

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


import com.example.payPalService.dto.PaymentDTO;
import com.example.payPalService.service.PayPalService;

@RestController
@CrossOrigin("https://localhost:1234")
public class PayPalController {
	
	@Autowired
	private PayPalService payPalService;
	
	@RequestMapping(path = "/create", method = RequestMethod.POST, produces = "text/plain")
	@ResponseBody
	public String createPayment(@RequestBody PaymentDTO payment){
		
		Map<String, Object> response = payPalService.createPayment(payment);
		
		
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
}
