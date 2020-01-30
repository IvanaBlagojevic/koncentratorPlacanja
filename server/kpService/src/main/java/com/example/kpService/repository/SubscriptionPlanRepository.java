package com.example.kpService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kpService.domain.SubscriptionPlan;

@Repository 
public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {

	SubscriptionPlan findOneById(Long id);
	
	SubscriptionPlan findByMerchantId(Long id);
	
	SubscriptionPlan findByMerchantIdAndPlanId(Long idM, String idP);
	
	SubscriptionPlan findByPlanId(String planId);
	
	SubscriptionPlan findByPeriodAndFrequencyAndMerchantId(String period, int f ,Long idM);
	
}
