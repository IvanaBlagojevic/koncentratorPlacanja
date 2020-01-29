package com.example.bankService.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.bankService.configuration.AES;
import com.example.bankService.domain.Merchant;
import com.example.bankService.domain.Payment;
import com.example.bankService.domain.ResponseToKP;
import com.example.bankService.domain.StatusOfPayment;
import com.example.bankService.dto.MerchantDTO;
import com.example.bankService.dto.PaymentDTO;
import com.example.bankService.dto.PaymentInfoDTO;
import com.example.bankService.dto.ResponseToKPDTO;
import com.example.bankService.service.MerchantService;
import com.example.bankService.service.PaymentService;

@RefreshScope
@RestController
@RequestMapping("payment")
public class PaymentController {
	
	
	
	@Autowired
	private PaymentService paymentService;
	
	private static final Logger logger  = LoggerFactory.getLogger(PaymentController.class);
	
	private String address ="https://localhost:8086/kpService/paymentinfo";

	
	@RequestMapping(value = "/saveResponse", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> validate (@Valid @RequestBody ResponseToKPDTO dto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			System.out.println("Binding error!");
        	logger.error(" 3 31 4 1");
			return new ResponseEntity<String>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		
		System.out.println("******pogodio update "+dto.getPaymentId());
		ResponseToKP toSave = new ResponseToKP(dto);
		ResponseToKP valid = this.paymentService.saveResponse(toSave);
		Optional<Payment> payment = this.paymentService.findOneByPaymentId(valid.getPaymentId().toString());
		System.out.println("*****valid "+payment.get().getPaymentId());
		if(payment.isPresent()) {
			if(valid!=null) {
				System.out.println("*******Successfully saved data!");
	        	logger.error(" 3 32 4 0");
			}
			RestTemplate temp = new RestTemplate();
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        System.out.println("gadjaj update "+valid.getPaymentId());
	        try {
	        	if(valid.getStatus().equals("success")) {
	        		payment.get().setStatus(StatusOfPayment.PAID);
	        		temp.put(address+"/update/"+valid.getPaymentId()+"/true/bankService", null);
	        	}else if(valid.getStatus().equals("error")) {
	        		payment.get().setStatus(StatusOfPayment.ERROR);
	        		temp.put(address+"/update/"+valid.getPaymentId()+"/false/bankService", null);
	        	}else {
	        		payment.get().setStatus(StatusOfPayment.FAILED);
	        		temp.put(address+"/update/"+valid.getPaymentId()+"/false/bankService", null);
	        	}
	        	this.paymentService.save(payment.get());
	            System.out.println("Successfull created payment!");
				logger.info(" 3 12 4 0");
	            
	        } catch (HttpStatusCodeException exception) {
	            System.out.println("Error while creating payment - HttpStatusCodeException!");
				logger.error(" 3 12 4 1");
				return new ResponseEntity<ResponseToKPDTO>(dto,HttpStatus.BAD_REQUEST);
	        }
		}else {
			return new ResponseEntity<ResponseToKPDTO>(dto,HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<ResponseToKPDTO>(dto,HttpStatus.OK);
	}
	
	
}
