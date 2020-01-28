package com.example.bankAcquirer.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.bankAcquirer.domain.BuyerInfo;
import com.example.bankAcquirer.domain.Payment;
import com.example.bankAcquirer.domain.ResponseToKP;
import com.example.bankAcquirer.dto.BuyerInfoDTO;
import com.example.bankAcquirer.dto.InterbankRequestDTO;
import com.example.bankAcquirer.dto.InterbankResponseDTO;
import com.example.bankAcquirer.dto.MerchantDTO;
import com.example.bankAcquirer.dto.PaymentDTO;
import com.example.bankAcquirer.dto.ResponseToKPDTO;
import com.example.bankAcquirer.service.BuyerInfoService;
import com.example.bankAcquirer.service.MerchantService;
import com.example.bankAcquirer.service.PaymentService;

import javassist.NotFoundException;

@RestController
@RequestMapping("payment")
@CrossOrigin(origins = "http://localhost:4201")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private BuyerInfoService buyerInfoService;
	
	private String addressBankService = "https://localhost:8089/payment/" ;
	
	private static final Logger logger  = LoggerFactory.getLogger(PaymentController.class);
	
	@RequestMapping(value = "/validate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> validate ( @RequestBody MerchantDTO dto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			logger.error(" 4 11 4 1");
			return new ResponseEntity<String>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		
		boolean valid = merchantService.validate(dto);
		if(valid==true) {
			System.out.println("Merchant with this id exists!");
			logger.info(" 4 12 4 0");
		}else {
			System.out.println("Merchant with this id doesn't exists!");
			logger.info(" 4 12 4 1");
		}
		
		return new ResponseEntity<Boolean>(valid, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/payment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> generatePayment(@RequestBody PaymentDTO payment, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			logger.error(" 4 21 4 1");
			return new ResponseEntity<String>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		System.out.println(payment.getFailedURL());
		System.out.println(payment.getSuccessURL());
		System.out.println(payment.getFailedURL());
		
		Payment entity;
		try {
			entity = paymentService.generatePayment(payment);
			return new ResponseEntity<Payment>(entity, HttpStatus.OK);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/payment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPaymentById(@PathVariable("id") String id) {
		Optional<Payment> payment = paymentService.findByPaymentId(Long.valueOf(id));
		if(payment.get() != null) {
			PaymentDTO paymentDTO = new PaymentDTO(payment);
			logger.info(" 4 31 4 0");
			return new ResponseEntity<PaymentDTO>(paymentDTO, HttpStatus.OK);
		}else {
			System.out.println("Payment with this id does not exists!");
			logger.error(" 4 31 4 1");
		}
		
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/addBuyerInfo",method=RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseToKPDTO> addBuyerInfo(@RequestBody BuyerInfoDTO dto)
	{
		BuyerInfo user = new BuyerInfo(dto);
		user.setPayment(paymentService.findByPaymentId(Long.valueOf(dto.getPaymentId())).get());
		try {
			ResponseToKP response =buyerInfoService.addNewBuyerInfo(user);
			ResponseToKPDTO resDTO = new ResponseToKPDTO(response);
			RestTemplate temp = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			
			
			
			if(response != null ) {
				System.out.println("Successfully get response from bank Service!");
				logger.info(" 4 41 4 0");
				if(response.getStatus().equals("success")) {
					resDTO.setUrl(user.getPayment().getSuccessUrl());
				
				}else if(response.getStatus().equals("error")) {
					resDTO.setUrl(user.getPayment().getErrorUrl());
					
				}else {
					resDTO.setUrl(user.getPayment().getFailedUrl());
					
				}
				HttpEntity<ResponseToKPDTO> entity = new HttpEntity<>(resDTO ,headers);
				ResponseToKPDTO isSaved = temp.postForObject(addressBankService+"saveResponse", entity, ResponseToKPDTO.class);
				return new ResponseEntity<ResponseToKPDTO>(resDTO, HttpStatus.OK);
			}else {
				System.out.println("Problem in response from bank Service!");
				logger.error(" 4 41 4 1");
			}
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseToKPDTO>(HttpStatus.OK);
	}
	
}