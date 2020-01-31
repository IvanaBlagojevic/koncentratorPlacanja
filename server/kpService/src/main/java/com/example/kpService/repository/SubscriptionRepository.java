package com.example.kpService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kpService.domain.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	List<Subscription> findAllByPlanId(String planId);
	
	Subscription findByPlanIdAndActive(String planId, boolean active);

	Subscription findByMerchantUsername(String username);
	
	Subscription findByAgreementIdAndSubEmail(String agId,String subEmail);
	
	Subscription findByMerchantUsernameAndSubEmailAndActive(String username, String subEmail, boolean active);
	
	Subscription findByMerchantUsernameAndActive(String username, boolean active);
}
