package com.example.bankService.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.bankService.configuration.AES;
import com.example.bankService.controller.CardController;
import com.example.bankService.domain.Merchant;
import com.example.bankService.domain.Payment;
import com.example.bankService.domain.ResponseToKP;
import com.example.bankService.domain.StatusOfPayment;
import com.example.bankService.domain.Transaction;
import com.example.bankService.dto.PaymentDTO;
import com.example.bankService.dto.PaymentInfoDTO;
import com.example.bankService.dto.PaymentInfoFromKPDTO;
import com.example.bankService.dto.TransactionDTO;
import com.example.bankService.repository.MerchantRepository;
import com.example.bankService.repository.PaymentRepository;
import com.example.bankService.repository.ResponseToKPRepository;
import com.example.bankService.repository.TransactionRepository;

@Service
@RefreshScope
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	private String address ="https://localhost:4202/";
	
	@Autowired
	private ResponseToKPRepository responseRepository;
	
	
	private String addressBank = "https://localhost:8090/payment/";

	private static final Logger logger  = LoggerFactory.getLogger(PaymentService.class);
	
	public ResponseToKP saveResponse(ResponseToKP entity) 
	{
		
		return this.responseRepository.save(entity);
	}
	
	public Payment save(Payment entity) 
	{
		
		return this.paymentRepository.save(entity);
	}

	public Payment createPayment( @Valid PaymentInfoDTO pdto) {
		// TODO Auto-generated method stub
		final String secretKey = "ssshhhhhhhhhhh!!!!";
		Merchant merchant= this.merchantRepository.findByMerchantIssn(pdto.getMerchantIssn());
		if(merchant == null) {
			System.out.println("Merchant with this email doesn't exists!");
			logger.error(" 3 11 4 1");
		}
		List<Payment> listOfPayment = this.paymentRepository.findByMerchantId(merchant.getMerchantId());
		PaymentDTO payment = new PaymentDTO();
		payment.setSuccessURL(pdto.getSuccessURL());
		System.out.println(pdto.getSuccessURL());
		payment.setErrorURL(pdto.getErrorURL());
		System.out.println(pdto.getErrorURL());
		payment.setFailedURL(pdto.getFailedURL());
		System.out.println(pdto.getFailedURL());
		payment.setAmount(pdto.getAmount());
		payment.setMerchantId(merchant.getMerchantId());
		payment.setMerchantPassword(AES.decrypt(merchant.getMerchantPassword(), secretKey));
		payment.setMerchantTimestamp(new Date());
		payment.setMerchantOrderId(new Long(listOfPayment.size()+1));
		
        RestTemplate temp = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentDTO> entity = new HttpEntity<>(payment, headers);
        
        try {
        	Payment paymentResponse = temp.postForObject(addressBank+"/payment", entity, Payment.class);
        	paymentResponse.setStatus(StatusOfPayment.CREATED);
            paymentRepository.save(paymentResponse);
            System.out.println("Successfull created payment!");
			logger.info(" 3 12 4 0");
        	return paymentResponse;
            
        } catch (HttpStatusCodeException exception) {
            System.out.println("Error while creating payment - HttpStatusCodeException!");
			logger.error(" 3 12 4 1");
        }

        return null;
	}

	public Optional<Payment> findOneById(Long id) {
		// TODO Auto-generated method stub
		return this.paymentRepository.findById(id);
	}

	public Optional<Payment> findOneByPaymentId(String paymentId) {
		// TODO Auto-generated method stub
		return this.paymentRepository.findOneByPaymentId(paymentId);
	}

}
