package com.example.kpService.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kpService.domain.Merchant;
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
	
	public Subscription getByMerchantUsername(String username) {
		
		return subRepository.findByMerchantUsername(username);
	}
	
	public Subscription getByAgrIdAndSubscriberEmail(String agrId, String email) {
		
		return subRepository.findByAgreementIdAndSubEmail(agrId, email);
	}
	
	public void deleteSubscription(Subscription sub) {
		
		subRepository.delete(sub);
	}
	
	public Subscription getByMerchantSubsciberAndActivity(String username, String subEmail, boolean active) {
		
		return subRepository.findByMerchantUsernameAndSubEmailAndActive(username, subEmail, active);
	}
	
	public List<Subscription> getAll(){
		
		return subRepository.findAll();
	}
	
	public Subscription getByMerchantAndActivity(String username, boolean activity) {
		
		return subRepository.findByMerchantUsernameAndActive(username, activity);
	}
}
