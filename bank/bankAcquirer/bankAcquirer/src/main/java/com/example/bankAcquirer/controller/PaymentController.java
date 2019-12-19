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

import com.example.bankAcquirer.dto.BuyerInfoDTO;
import com.example.bankAcquirer.dto.MerchantDTO;
import com.example.bankAcquirer.service.MerchantService;
import com.example.bankAcquirer.service.PaymentService;

@RestController
@RequestMapping("payment")
@CrossOrigin(origins = "http://localhost:4201")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private MerchantService merchantService;
	
	@RequestMapping(value = "/getTest",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BuyerInfoDTO> getTest()
	{
		BuyerInfoDTO a = new BuyerInfoDTO();
		a.setInfo("Success");
		System.out.println("pogodio");
		return new ResponseEntity<>(a,HttpStatus.OK);
	}
	
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
	
	
}