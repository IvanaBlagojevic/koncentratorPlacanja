package com.example.bankService.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.bankService.configuration.AES;
import com.example.bankService.domain.Merchant;
import com.example.bankService.domain.Payment;
import com.example.bankService.domain.StatusOfPayment;
import com.example.bankService.dto.MerchantDTO;
import com.example.bankService.dto.PaymentDTO;
import com.example.bankService.dto.PaymentInfoDTO;
import com.example.bankService.dto.PaymentInfoFromKPDTO;
import com.example.bankService.service.MerchantService;
import com.example.bankService.service.PaymentService;


@RestController
@CrossOrigin("https://localhost:1234")
public class CardController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private MerchantService merchantService;
	
	private static final Logger logger  = LoggerFactory.getLogger(CardController.class);
	
	private String address ="https://localhost:8086/kpService/paymentinfo";
	
	private String addressAcquirer = "https://localhost:8090/payment/";
	
	@RequestMapping(value = "/create/{orderId}",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity paying(@PathVariable String orderId, @Valid @RequestBody PaymentInfoDTO pdto) {

        Payment payment= this.paymentService.createPayment(pdto);

        if(payment.getPaymentUrl() == null) {
        	System.out.println("PaymentUrl is null");
        	logger.error(" 6 13 4 1");
        }
        RestTemplate temp = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        PaymentInfoDTO dto = new PaymentInfoDTO();
        dto.setMerchantIssn(pdto.getMerchantIssn());
        dto.setOrderNumberId(payment.getId());
        dto.setIsPaid(StatusOfPayment.CREATED);
        dto.setPaymentMethod("bankService");
        HttpEntity<PaymentInfoDTO> entity = new HttpEntity<>(dto, headers);
        System.out.println("gadjaj "+address+"/create/"+orderId+"/");
        try {
        	temp.postForObject(address+"/create/"+orderId+"/", dto, Payment.class);
            System.out.println("Successfull created payment!");
			logger.info(" 3 12 4 0");
            
        } catch (HttpStatusCodeException exception) {
            System.out.println("Error while creating payment - HttpStatusCodeException!");
			logger.error(" 3 12 4 1");
			return new ResponseEntity<String>(payment.getPaymentUrl(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(payment.getPaymentUrl(), HttpStatus.OK);
    }
	
	@RequestMapping(
			path="addUser",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addNewUser(@RequestBody MerchantDTO merchant) {
		
		
		System.out.println("add merchent");
		RestTemplate temp = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        final String secretKey = "ssshhhhhhhhhhh!!!!";
        
        String originalString = merchant.getMerchantPassword();
        String encryptedString = AES.encrypt(originalString, secretKey) ;
        String decryptedString = AES.decrypt(encryptedString, secretKey) ;
         

        HttpEntity<MerchantDTO> responseMerchant = new HttpEntity<>(merchant ,headers);
        try {
            Boolean valid = temp.postForObject(addressAcquirer+"validate", responseMerchant, Boolean.class);
            if (!valid) {
            	System.out.println("Merchant data is not valid!");
            	logger.error(" 3 21 4 1");
                return new ResponseEntity<>("Merchant data is not valid!", HttpStatus.BAD_REQUEST);
            }else {
            	System.out.println("Merchant data is valid!");
            	logger.error(" 3 21 4 0");
            }
        } catch (HttpStatusCodeException exception) {
            System.out.println("Error validating payment card data!");
            logger.error(" 3 22 4 1");
            return new ResponseEntity<>("Merchant data is not valid!", HttpStatus.BAD_REQUEST);
        }
        Merchant newMerchant = new Merchant(merchant);
        newMerchant.setMerchantPassword(encryptedString);
		merchantService.addNewMerchant(newMerchant);
		
		return new ResponseEntity<>("User added to Card!",HttpStatus.OK);
		
	}
	
	 @RequestMapping(value="/getOne/{oid}", method = RequestMethod.GET)
	 public ResponseEntity<Map<String, Object>> getPayment(@PathVariable("oid") Long oid) {
		 	System.out.println("usao u getOne payment " + oid);
		 	Optional<Payment> o = this.paymentService.findOneById(oid);
		 	if (o.isPresent()) {
		 		System.out.println("Order with this oid does not exists!");
		 		logger.error(" 6 31 4 1");
		 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 	}
		 	
		
		 	String isPaid="";
		 	//NEW, PENDING, CONFIRMING, PAID, INVALID, CANCELED, REFUNDED, EXPIRED - bitcoin statusi
		 	if (o.get().getStatus() == StatusOfPayment.CREATED  ) {
		 		isPaid = "new";
		 	}else if (o.get().getStatus() == StatusOfPayment.SUCCESS ) {
		 		isPaid = "paid";
		 	}else if (o.get().getStatus() == StatusOfPayment.ERROR || o.get().getStatus() == StatusOfPayment.FAILURE ) {
		 		isPaid = "invalid";
		 	}
	        
		 	Map<String, Object> mapToKP = new HashMap<String, Object>();
		 	Merchant merchant = this.merchantService.findByMerchantId(o.get().getMerchantId());
			mapToKP.put("merchantIssn", merchant.getMerchantIssn());
			mapToKP.put("userEmail", "ivana");								//zakucano
			mapToKP.put("orderNumberId", o.get().getId());
			mapToKP.put("isPaid", isPaid);
			mapToKP.put("paymentMethod", "bankService");
			mapToKP.put("created", o.get().getMerchantTimestamp());
			//mapToKP.put("updated", o.getCreated());
			
		 	
		 	return new ResponseEntity<Map<String, Object>>(mapToKP, HttpStatus.ACCEPTED);
	 }

}