package com.example.bankAcquirer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankAcquirer.domain.Merchant;
import com.example.bankAcquirer.dto.MerchantDTO;
import com.example.bankAcquirer.repository.MerchantRepository;


@Service
public class MerchantService {
	

	@Autowired
	private MerchantRepository merchantRepository;

	
	
	public Merchant addNewMerchant(Merchant user) 
	{
		
		return merchantRepository.save(user);
	}



	public boolean validate(MerchantDTO dto) {
		// TODO Auto-generated method stub
		Merchant merchant = this.merchantRepository.findByMerchantId(dto.getMerchantId());
		System.out.println(dto.getMerchantId());
		if (merchant == null || !merchant.getMerchantPassword().equals(dto.getMerchantPassword())) {
			return false;
		}
		return true;
	}

}