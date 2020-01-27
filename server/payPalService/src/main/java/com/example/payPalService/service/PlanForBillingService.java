package com.example.payPalService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.payPalService.domain.PlanForBilling;
import com.example.payPalService.repository.PlanForBillingRepository;

@Service
public class PlanForBillingService {

	@Autowired
	private PlanForBillingRepository planRepository;
	
	public PlanForBilling savePlan(PlanForBilling plan) {
		
		return planRepository.save(plan);
	}
}
