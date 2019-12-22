package com.example.bankService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.bankService.domain.Payment;
import com.example.bankService.domain.Transaction;
import com.example.bankService.dto.PaymentDTO;
import com.example.bankService.dto.TransactionDTO;
import com.example.bankService.repository.MerchantRepository;
import com.example.bankService.repository.PaymentRepository;
import com.example.bankService.repository.TransactionRepository;

@Service
@RefreshScope
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	private String address ="http://localhost:4200/";
	
	private String addressBank = "http://localhost:8090/payment/";

	public String createPayment(PaymentDTO payment) {
		       
		payment.setSuccessUrl(address+"success");
		payment.setErrorUrl(address+"error");
		payment.setFailedUrl(address+"failed");
       
        RestTemplate temp = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentDTO> entity = new HttpEntity<>(payment, headers);
        
        try {
        	Payment paymentResponse = temp.postForObject(addressBank+"/payment", entity, Payment.class);
        	//Payment entityResponse = new Payment(paymentResponse);
            paymentRepository.save(paymentResponse);
        	//System.out.println("gotov response od banke do kpa"+paymentResponse.getPaymentUrl());
        	return paymentResponse.getPaymentUrl();//paymentResponse.getPaymentUrl();
            
        } catch (HttpStatusCodeException exception) {
            System.out.println("Error creating payment!");
        }

        return null;
    }
	


}
