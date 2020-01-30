package com.example.kpService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kpService.domain.SubscriptionPlan;
import com.example.kpService.repository.SubscriptionPlanRepository;

@Service
public class SubscriptionPlanService {

	@Autowired
	private SubscriptionPlanRepository subRepository;
	
	public SubscriptionPlan getByMerchantId(Long id) {
		
		return subRepository.findByMerchantId(id);
	}
	
	public SubscriptionPlan saveSubscritpion(SubscriptionPlan sub) {
		
		return subRepository.save(sub);
	}
	
	public SubscriptionPlan getById(Long id) {
		
		return subRepository.findOneById(id);
	}
	
	public SubscriptionPlan getByMerchantAndPlan(Long idM,String idP) {
		
		return subRepository.findByMerchantIdAndPlanId(idM, idP);
	}
	
	public SubscriptionPlan getByPlanId(String idP) {
		
		return subRepository.findByPlanId(idP);
	}
	
	public SubscriptionPlan getByPeriodFrequencyAndMerchant(String period, int f, Long id) {
		
		return subRepository.findByPeriodAndFrequencyAndMerchantId(period, f, id);
	}
}
