package com.example.bankAcquirer.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankAcquirer.controller.PaymentController;
import com.example.bankAcquirer.domain.Merchant;
import com.example.bankAcquirer.domain.Payment;
import com.example.bankAcquirer.dto.PaymentDTO;
import com.example.bankAcquirer.repository.MerchantRepository;
import com.example.bankAcquirer.repository.PaymentRepository;

import javassist.NotFoundException;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private MerchantRepository merchantRepository;

	private String frontUrl ="http://localhost:4201/";
	
	private static final Logger logger  = LoggerFactory.getLogger(PaymentService.class);
	
	public Payment addNewPayment(Payment payment) 
	{
		
		return paymentRepository.save(payment);
	}
	
	public Optional<Payment> findById(Long id) 
	{
		
		return paymentRepository.findById(id);
	}

	public Payment generatePayment(PaymentDTO payment) throws NotFoundException {
		// TODO Auto-generated method stub
		Merchant merchant = this.merchantRepository.findByMerchantId(payment.getMerchantId());
		if (merchant == null) {
			System.out.println("Invalid merchant id!");
			logger.error(" 4 61 4 1");
			throw new NotFoundException("Invalid merchant id!");
		}
		if (!merchant.getMerchantPassword().equals(payment.getMerchantPassword())) {
			System.out.println("Invalid merchant password!");
			throw new NotFoundException("Invalid merchant password!");
		}
		
		Payment entity = new Payment(payment);
		List<Payment> listofAll=paymentRepository.findAll();
		entity.setPaymentId(Long.valueOf(listofAll.size()+1));
		entity.setPaymentUrl(this.frontUrl + "payment/" + entity.getPaymentId());
		
		Payment savedEntity= this.paymentRepository.save(entity);
		
		this.paymentRepository.save(savedEntity);
			
		return savedEntity;
	}

	public Optional<Payment> findByPaymentId(Long id) {
		// TODO Auto-generated method stub
		return this.paymentRepository.findByPaymentId(id);
	}

	


}
