package com.example.authService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.example.authService.domain.User;
import com.example.authService.repository.UserRepository;

@Service
@RefreshScope
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Value("${user-email}")
	private String email;
	
	
	public User addNewUser(User user) 
	{
		
		return userRepository.save(user);
	}
	
	public List<User> findUserByEmail()
	{
		return userRepository.findAllByEmail(email);
	}
}
