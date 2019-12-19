package com.example.bankService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.example.bankService.domain.Merchant;
import com.example.bankService.repository.MerchantRepository;

@Service
@RefreshScope
public class MerchantService {
	

	@Autowired
	private MerchantRepository merchantRepository;

	
	
	public Merchant addNewMerchant(Merchant user) 
	{
		
		return merchantRepository.save(user);
	}

}
