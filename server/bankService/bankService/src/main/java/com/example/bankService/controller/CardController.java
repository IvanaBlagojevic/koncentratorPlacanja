package com.example.bankService.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankService.dto.PaymentInfoFromKPDTO;
import com.example.bankService.service.PaymentService;


@RestController
@CrossOrigin("https://localhost:1234")
public class CardController {
	
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(value = "/create",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity paying(@Valid @RequestBody PaymentInfoFromKPDTO pdto) {

        String paymentUrl = this.paymentService.createPayment(pdto);

        return new ResponseEntity<String>(paymentUrl, HttpStatus.OK);
    }
}