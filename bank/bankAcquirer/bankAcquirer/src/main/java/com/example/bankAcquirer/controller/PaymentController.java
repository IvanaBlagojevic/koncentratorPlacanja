package com.example.bankAcquirer.controller;

import java.util.Optional;

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
	
	@RequestMapping(value = "/validate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> validate ( @RequestBody MerchantDTO dto, BindingResult bindingResult) {
		System.out.println("Validacija ");
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<String>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		
		boolean valid = merchantService.validate(dto);
		System.out.println("Validacija "+valid);
		
		return new ResponseEntity<Boolean>(valid, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/payment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> generatePayment(@RequestBody PaymentDTO payment, BindingResult bindingResult) {
		System.out.print("pogodio banku");
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<String>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		
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
			return new ResponseEntity<PaymentDTO>(paymentDTO, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/addBuyerInfo",method=RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseToKPDTO> addBuyerInfo(@RequestBody BuyerInfoDTO dto)
	{
		System.out.print(dto.getPaymentId());
		System.out.print("iznad je vrednsot");
		BuyerInfo user = new BuyerInfo(dto);
		user.setPayment(paymentService.findByPaymentId(Long.valueOf(dto.getPaymentId())).get());
		try {
			ResponseToKP response =buyerInfoService.addNewBuyerInfo(user);
			ResponseToKPDTO resDTO = new ResponseToKPDTO(response);
			RestTemplate temp = new RestTemplate();
			//HttpHeaders headers = new HttpHeaders();
			//headers.setContentType(MediaType.APPLICATION_JSON);
			//HttpEntity<ResponseToKPDTO> entity = new HttpEntity<>(resDTO ,headers);
			
			ResponseToKPDTO isSaved = temp.postForObject(addressBankService+"saveResponse", resDTO, ResponseToKPDTO.class);
			
			if(response != null ) {
				if(response.getStatus().equals("success")) {
					resDTO.setUrl(user.getPayment().getSuccessUrl());
					return new ResponseEntity<ResponseToKPDTO>(resDTO, HttpStatus.OK);
				}else if(response.getStatus().equals("error")) {
					resDTO.setUrl(user.getPayment().getErrorUrl());
					return new ResponseEntity<ResponseToKPDTO>(resDTO, HttpStatus.OK);
				}else {
					resDTO.setUrl(user.getPayment().getFailedUrl());
					return new ResponseEntity<ResponseToKPDTO>(resDTO, HttpStatus.OK);
				}
			}
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseToKPDTO>(HttpStatus.OK);
	}
	
}