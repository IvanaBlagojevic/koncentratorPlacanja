package com.example.bankAcquirer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankAcquirer.domain.Payment;
import com.example.bankAcquirer.repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;

	
	
	public Payment addNewPayment(Payment payment) 
	{
		
		return paymentRepository.save(payment);
	}
	
	public Optional<Payment> getPayment(Long id) 
	{
		
		return paymentRepository.findById(id);
	}

}
