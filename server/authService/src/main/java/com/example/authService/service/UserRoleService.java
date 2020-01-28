package com.example.authService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.authService.domain.UserRole;
import com.example.authService.domain.UserRoleName;
import com.example.authService.repository.UserRoleRepository;


@Service
public class UserRoleService {
	@Autowired
	private UserRoleRepository roleRep;
	
	public UserRole findRoleByName(UserRoleName name){
		
		return roleRep.findRoleByName(name);
	}

}
