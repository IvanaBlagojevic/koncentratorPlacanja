package com.example.bankService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.example.bankService.repository.MerchantRepository;
import com.example.bankService.repository.PaymentRepository;

@Service
@RefreshScope
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	


}
