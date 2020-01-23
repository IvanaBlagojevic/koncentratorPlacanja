package com.example.kpService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kpService.domain.MethodOfPayment;
import com.example.kpService.domain.MethodOfPaymentFields;
import com.example.kpService.repository.MethodOfPaymentFieldsRespository;
import com.example.kpService.repository.MethodOfPaymentRepository;


@Service
public class MethodOfPaymentFieldsService {
	
	@Autowired
    private MethodOfPaymentFieldsRespository repository;
	
	

	public MethodOfPaymentFields save(MethodOfPaymentFields field) {
		// TODO Auto-generated method stub
		 return this.repository.save(field);
	}

	

}
