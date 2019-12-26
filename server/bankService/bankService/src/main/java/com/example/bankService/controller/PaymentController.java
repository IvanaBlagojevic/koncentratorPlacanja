package com.example.bankService.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankService.domain.Merchant;
import com.example.bankService.domain.ResponseToKP;
import com.example.bankService.dto.MerchantDTO;
import com.example.bankService.dto.PaymentDTO;
import com.example.bankService.dto.ResponseToKPDTO;
import com.example.bankService.service.MerchantService;
import com.example.bankService.service.PaymentService;

@RefreshScope
@RestController
@RequestMapping("payment")
public class PaymentController {
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private PaymentService paymentService;
	
	


	
	@RequestMapping(value = "/saveResponse", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> validate (@Valid @RequestBody ResponseToKPDTO dto, BindingResult bindingResult) {
		System.out.println("Validacija ");
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<String>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		ResponseToKP toSave = new ResponseToKP(dto);
		ResponseToKP valid = this.paymentService.saveResponse(toSave);
		System.out.println("Validacija "+valid);
		
		return new ResponseEntity<ResponseToKPDTO>(dto,HttpStatus.OK);
	}
}
