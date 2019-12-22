package com.example.bankService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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

import com.example.bankService.configuration.AES;
import com.example.bankService.domain.Merchant;
import com.example.bankService.dto.MerchantDTO;
import com.example.bankService.repository.MerchantRepository;
import com.example.bankService.service.MerchantService;

@RefreshScope
@RestController
@RequestMapping("merchant")
@CrossOrigin(origins = "http://localhost:4200")
public class MerchantController {
	
	@Autowired
	private MerchantService merchantService;
	
	private String address = "http://localhost:8090/payment/";
	
	@RequestMapping(value = "/add",method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<?> newUser(@RequestBody MerchantDTO merchant)
	{
		System.out.println("add merchent");
		RestTemplate temp = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        final String secretKey = "ssshhhhhhhhhhh!!!!";
        
        String originalString = merchant.getMerchantPassword();
        String encryptedString = AES.encrypt(originalString, secretKey) ;
        String decryptedString = AES.decrypt(encryptedString, secretKey) ;
         
        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);

        HttpEntity<MerchantDTO> responseMerchant = new HttpEntity<>(merchant ,headers);
        try {
            Boolean valid = temp.postForObject(address+"validate", responseMerchant, Boolean.class);
            if (!valid) {
                return new ResponseEntity<>("Data is not valid!", HttpStatus.BAD_REQUEST);
            }
        } catch (HttpStatusCodeException exception) {
            System.out.println("Error validating payment card data!");
        }
        Merchant newMerchant = new Merchant(merchant);
        newMerchant.setMerchantPassword(encryptedString);
		merchantService.addNewMerchant(newMerchant);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	


}
