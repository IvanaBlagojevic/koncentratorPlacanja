package com.example.kpService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kpService.domain.MethodOfPayment;
import com.example.kpService.repository.MethodOfPaymentRepository;

@Service
public class MethodOfPaymentService {
	
	@Autowired
    private MethodOfPaymentRepository paymentRepository;

    public List<MethodOfPayment> getAllMethodsOfPayment() {

        return this.paymentRepository.findAll();

    }

	public MethodOfPayment save(MethodOfPayment method) {
		// TODO Auto-generated method stub
		 return this.paymentRepository.save(method);
	}

	public MethodOfPayment findOneById(Long id) {
		// TODO Auto-generated method stub
		return this.paymentRepository.findOneById(id);
	}
	
	public MethodOfPayment getOneByName(String name) {
		
		return this.paymentRepository.findOneByName(name);
	}

}
