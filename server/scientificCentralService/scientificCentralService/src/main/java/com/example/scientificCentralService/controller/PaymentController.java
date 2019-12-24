package com.example.scientificCentralService.controller;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.scientificCentralService.dto.PaymentDataDTO;


@RestController
@RequestMapping("payment")
@CrossOrigin(origins = "https://localhost:4202")
public class PaymentController {

	private String address="https:/localhost:8086/kpService";
	
	@RequestMapping(value = "/sendPaymentData",method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<?> newUser(@Valid @RequestBody PaymentDataDTO payment)
	{
		System.out.println("send payment");
		/*RestTemplate temp = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<PaymentDataDTO> responseMerchant = new HttpEntity<>(payment ,headers);
        try {
            Boolean valid = temp.postForObject(address+"payment", responseMerchant, Boolean.class);
            if (!valid) {
                return new ResponseEntity<>("Payment data is not valid!", HttpStatus.BAD_REQUEST);
            }
        } catch (HttpStatusCodeException exception) {
            System.out.println("Error while sending payment data to KP!");
        }
        */
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
