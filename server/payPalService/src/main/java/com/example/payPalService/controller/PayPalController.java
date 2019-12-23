package com.example.payPalService.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.payPalService.dto.PaymentDTO;
import com.example.payPalService.service.PayPalService;

@RestController
@RequestMapping("/paypal") //treba dodati crossOrigin za kpService kad se napravi front
@CrossOrigin("https://localhost:1234")
public class PayPalController {
	
	@Autowired
	private PayPalService payPalService;
	
	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public Map<String,Object> createPayment(@RequestBody PaymentDTO payment){
		
		return payPalService.createPayment(payment);
	}
	
	@RequestMapping(path = "/complete/{paymentId}/{PayerID}/{username}", method =  RequestMethod.GET)
	public ResponseEntity completePayment(@PathVariable String paymentId, @PathVariable String PayerID,@PathVariable String username) {
		
		Map<String,Object> response = payPalService.completePayment(paymentId, PayerID,username);
		String redirectionUrl = (String) response.get("redirect_url");
		
		return new ResponseEntity<>(redirectionUrl,HttpStatus.OK);
		
	}
	

}
