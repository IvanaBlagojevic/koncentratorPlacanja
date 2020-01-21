package com.example.kpService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kpService.domain.MerchantSystem;
import com.example.kpService.repository.MerchantSystemRepository;

@Service
public class MerchantSystemService {

	@Autowired
	private MerchantSystemRepository systemRepository;
	
	public MerchantSystem findByName(String name) {
		
		return systemRepository.findBySystemName(name);
	}
}
