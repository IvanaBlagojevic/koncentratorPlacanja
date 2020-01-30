package com.example.payPalService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.payPalService.domain.UserPayPal;
import com.example.payPalService.repository.UserPayPalRepository;

@Service
public class UserPayPalService {
	
	@Autowired
	private UserPayPalRepository userRep;
	
	public Optional<UserPayPal> getUserByUsername(String username) {
		
		return userRep.findByUsername(username);
	}
	
	public UserPayPal saveUser(UserPayPal user) {
		
		return userRep.save(user);
	}

}
