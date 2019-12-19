package com.example.bankService.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankService.domain.Merchant;
import com.example.bankService.dto.MerchantDTO;

@RefreshScope
@RestController
@RequestMapping("payment")
public class PaymentController {
	
	
}
