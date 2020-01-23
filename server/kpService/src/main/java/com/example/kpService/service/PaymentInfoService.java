package com.example.kpService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kpService.domain.PaymentInfo;
import com.example.kpService.domain.PaymentStatus;
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

	public List<PaymentInfo> findAll() {
		// TODO Auto-generated method stub
		return pir.findAll();
	}

	public List<PaymentInfo> findAllByIsPaidNot(PaymentStatus paid) {
		// TODO Auto-generated method stub
		return pir.findAllByIsPaidNot(paid);
	}

	public List<PaymentInfo> findAllByIsPaid(PaymentStatus paid) {
		// TODO Auto-generated method stub
		return pir.findAllByIsPaid(paid);
	}

	public PaymentInfo findOneByOrderNumberNC(String orderId) {
		// TODO Auto-generated method stub
		return pir.findOneByOrderNumberNC(orderId);
	}

}
