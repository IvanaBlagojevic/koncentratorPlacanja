package com.example.kpService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.example.kpService.domain.User;
import com.example.kpService.repository.UserRepository;


@Service
@RefreshScope
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Value("${user-email}")
	private String name;
	
	public List<User> findUserByEmail()
	{
		System.out.println("Trazi se korisnik sa imenom: " + name);
		return userRepository.findAllByName(name);
	}
}
