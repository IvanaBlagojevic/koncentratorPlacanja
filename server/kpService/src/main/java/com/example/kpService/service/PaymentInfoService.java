package com.example.kpService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kpService.domain.PaymentInfo;
import com.example.kpService.repository.PaymentInfoRepository;

@Service
public class PaymentInfoService {
	
	@Autowired
	private PaymentInfoRepository pir;

	public void save(PaymentInfo method) {
		// TODO Auto-generated method stub
		pir.save(method);
	}

	public PaymentInfo findOneByOrderNumberIdAndPaymentMethod(Long id, String method) {
		// TODO Auto-generated method stub
		return pir.findOneByOrderNumberIdAndPaymentMethod(id,method);
	}

}
