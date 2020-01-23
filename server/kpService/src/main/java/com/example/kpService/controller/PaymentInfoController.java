package com.example.kpService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.kpService.domain.Merchant;
import com.example.kpService.domain.MethodOfPayment;
import com.example.kpService.domain.PaymentInfo;
import com.example.kpService.domain.PaymentStatus;
import com.example.kpService.dto.MerchantSubmissionDTO;
import com.example.kpService.dto.MethodOfPaymentDTO;
import com.example.kpService.dto.PaymentInfoDTO;
import com.example.kpService.dto.TransactionDTO;
import com.example.kpService.service.PaymentInfoService;

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
		pis.save(method);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update/{id}/{status}/{method}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePaymentInfo(@PathVariable("id") Long id, @PathVariable("status") boolean status, @PathVariable("method") String method) {

		
		System.out.println("update method ");
		PaymentInfo pi = pis.findOneByOrderNumberIdAndPaymentMethod(id,method);
		
		pi.setPaymentMethod(method);
		if (status == true) {
			pi.setPaid(PaymentStatus.PAID);
		}else {
			pi.setPaid(PaymentStatus.ERROR);
		}
		
		this.pis.save(pi);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	

	@RequestMapping(value = "/createFromNC", method = RequestMethod.POST)
	public ResponseEntity<?> createPaymentInfoFromNC(@RequestBody TransactionDTO dto) {
		
		System.out.println("CREATE method ");
		PaymentInfo method =  dto.convertToDomain();
		pis.save(method);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
		
}
