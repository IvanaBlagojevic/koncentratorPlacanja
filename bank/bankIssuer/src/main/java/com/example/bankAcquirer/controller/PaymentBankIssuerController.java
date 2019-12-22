package com.example.bankAcquirer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankAcquirer.domain.Transaction;
import com.example.bankAcquirer.dto.InterbankRequestDTO;
import com.example.bankAcquirer.dto.IssuerResponseDTO;
import com.example.bankAcquirer.service.BuyerInfoService;
import com.example.bankAcquirer.service.MerchantService;
import com.example.bankAcquirer.service.PaymentService;

@RestController
public class PaymentBankIssuerController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private BuyerInfoService buyerInfoService;
	
	@RequestMapping(value = "/pccRequest", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> pccRequest(@RequestBody InterbankRequestDTO dto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<String>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		
		Transaction transaction = this.paymentService.generatePayment(dto);
		IssuerResponseDTO response =this.buyerInfoService.requestToPCC(transaction,dto);
		
		return new ResponseEntity<IssuerResponseDTO>(response, HttpStatus.OK);
	}

}
