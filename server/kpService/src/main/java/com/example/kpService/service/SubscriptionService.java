package com.example.kpService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kpService.domain.Subscription;
import com.example.kpService.repository.SubscriptionRepository;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionRepository subRepository;
	
	public Subscription saveSub(Subscription s) {
		
		return subRepository.save(s);
	}
	
	public Subscription getByPlanId(String planId) {
		
		return subRepository.findByPlanId(planId);
	}
}
