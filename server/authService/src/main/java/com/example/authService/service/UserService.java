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
    private UserRepository repository;
	
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
	public User save(User user) {
		return repository.save(user);
	}

	

	public void delete(User user) {
		// TODO Auto-generated method stub
		repository.delete(user);
	}

	public Optional<User> getByEmail(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email);
	}
}
