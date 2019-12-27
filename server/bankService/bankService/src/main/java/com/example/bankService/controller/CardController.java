package com.example.bankService.controller;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.bankService.domain.Payment;
import com.example.bankService.dto.PaymentDTO;
import com.example.bankService.dto.PaymentInfoDTO;
import com.example.bankService.dto.PaymentInfoFromKPDTO;
import com.example.bankService.service.PaymentService;


@RestController
@CrossOrigin("https://localhost:1234")
public class CardController {
	
	@Autowired
	private PaymentService paymentService;
	
	private static final Logger logger  = LoggerFactory.getLogger(CardController.class);
	
	private String address ="https://localhost:8086/kpService/paymentinfo";
	
	@RequestMapping(value = "/create",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity paying(@Valid @RequestBody PaymentInfoFromKPDTO pdto) {

        Payment payment= this.paymentService.createPayment(pdto);

        if(payment.getPaymentUrl() == null) {
        	System.out.println("PaymentUrl is null");
        	logger.error(" 6 13 4 1");
        }
        RestTemplate temp = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        PaymentInfoDTO dto = new PaymentInfoDTO();
        dto.setMerchantEmail(pdto.getMerchantEmail());
        dto.setOrderNumberId(payment.getId());
        dto.setPaid(false);
        dto.setPaymentMethod("bank");
        HttpEntity<PaymentInfoDTO> entity = new HttpEntity<>(dto, headers);
        
        try {
        	temp.postForObject(address+"/create", dto, Payment.class);
            System.out.println("Successfull created payment!");
			logger.info(" 3 12 4 0");
            
        } catch (HttpStatusCodeException exception) {
            System.out.println("Error while creating payment - HttpStatusCodeException!");
			logger.error(" 3 12 4 1");
        }
        return new ResponseEntity<String>(payment.getPaymentUrl(), HttpStatus.OK);
    }
}