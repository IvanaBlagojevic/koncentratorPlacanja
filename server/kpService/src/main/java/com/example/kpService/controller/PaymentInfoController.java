package com.example.kpService.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.kpService.domain.Merchant;
import com.example.kpService.domain.MethodOfPayment;
import com.example.kpService.domain.PaymentInfo;
import com.example.kpService.domain.PaymentStatus;
import com.example.kpService.dto.MerchantSubmissionDTO;
import com.example.kpService.dto.MethodOfPaymentDTO;
import com.example.kpService.dto.PaymentInfoDTO;
import com.example.kpService.dto.TransactionDTO;
import com.example.kpService.service.PaymentInfoService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("paymentinfo")
public class PaymentInfoController {
	
	@Autowired
	private PaymentInfoService pis;
	
	@RequestMapping(value = "/create/{orderId}/", method = RequestMethod.POST)
	public ResponseEntity<?> createPaymentInfo(@PathVariable String orderId,@RequestBody PaymentInfoDTO dto) {
		
		System.out.println("CREATE method "+orderId);
		//PaymentInfo method =  dto.convertToDomain();
		PaymentInfo method = this.pis.findOneByOrderNumberNC(orderId);
		method.setOrderNumberId(dto.getOrderNumberId());
		method.setPaymentMethod(dto.getPaymentMethod());
		method.setCreated(dto.getCreated());
		pis.save(method);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update/{id}/{status}/{method}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePaymentInfo(@PathVariable("id") Long id, @PathVariable("status") boolean status, @PathVariable("method") String method) {
		PaymentInfo pi = pis.findOneByOrderNumberIdAndPaymentMethod(id,method);
		String back = "";
		
		pi.setPaymentMethod(method);
		if (status == true) {
			back = "paid";
			pi.setPaid(PaymentStatus.PAID);
		}else if (status == false){
			back = "error";
			pi.setPaid(PaymentStatus.ERROR);
		}
		
		this.pis.save(pi);
		/*RestTemplate toNC = new RestTemplate();
		HttpHeaders headersToNC = new HttpHeaders();
		headersToNC.add("Authorization", "Bearer "+));
		Map<String, Object> mapToNC = new HashMap<String, Object>();
		HttpEntity<Map<String,Object>> requesttoNC = new HttpEntity<>(mapToNC, headersToNC);
		toNC.postForEntity("https://localhost:8088/transaction/updateStatus/"+ pi.getOrderNumberNC() +"/" + back, requesttoNC, String.class);
		*/
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	

	@RequestMapping(value = "/createFromNC", method = RequestMethod.POST)
	public ResponseEntity<?> createPaymentInfoFromNC(@RequestBody TransactionDTO dto) {
		
		System.out.println("CREATE method ");
		PaymentInfo method =  dto.convertToDomain();
		pis.save(method);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/getOne/{orderId}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getOne(@PathVariable String orderId) {
		System.out.println("KP getOne ");
		
		PaymentInfo pi = pis.findOneByOrderNumberNC(orderId);
		String isPaid = "";
		if (pi.isPaid() == PaymentStatus.CREATED) {
	 		isPaid = "created";
	 	}else if (pi.isPaid() == PaymentStatus.PAID) {
	 		isPaid = "paid";
	 	}else if (pi.isPaid() == PaymentStatus.ERROR) {
	 		isPaid = "error";
	 	}else if (pi.isPaid() == PaymentStatus.UNFINISHED) {
	 		isPaid = "unfinished";
	 	}else if (pi.isPaid() == PaymentStatus.FAILED) {
	 		isPaid = "failed";
	 	}
        
		
		Map<String, Object> mapToKP = new HashMap<String, Object>();
		mapToKP.put("isPaid", isPaid);
		
		
		return new ResponseEntity<Map<String, Object>>(mapToKP, HttpStatus.CREATED);
	}
	
	
		
}
