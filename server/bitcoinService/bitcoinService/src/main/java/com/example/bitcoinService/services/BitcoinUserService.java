package com.example.bitcoinService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bitcoinService.domain.BitcoinUser;
import com.example.bitcoinService.repository.BitcoinUserRepository;

@Service
public class BitcoinUserService {
	
	@Autowired
	BitcoinUserRepository bur;

	public BitcoinUser findOneByUsername(String username) {
		// TODO Auto-generated method stub
		return bur.findOneByUsername(username);
	}

	public void save(BitcoinUser bu) {
		// TODO Auto-generated method stub
		bur.save(bu);
	}

}
