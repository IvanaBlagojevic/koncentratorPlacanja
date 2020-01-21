package com.example.kpService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kpService.domain.Merchant;
import com.example.kpService.repository.MerchantRepository;

@Service
public class MerchantService {

	@Autowired
	private MerchantRepository merchantRepository;
	
	public Merchant getByUsername(String username) {
		
		return merchantRepository.findOneByUsername(username);
	}
	
	public Merchant saveMerchant(Merchant m) {
		
		return merchantRepository.save(m);
	}
}
