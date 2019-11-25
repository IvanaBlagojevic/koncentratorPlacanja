package com.example.authService.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.authService.domain.User;
import com.example.authService.dto.UserDTO;
import com.example.authService.service.UserService;



@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userServ;
	
	@RequestMapping(value = "/add",method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<?> newUser(@RequestBody UserDTO user)
	{
		System.out.println("stiglo, email: " + user.getEmail() + ", " + user.getSurname() + ", " + user.getName());
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setSurname(user.getSurname());
		newUser.setName(user.getName());
		userServ.addNewUser(newUser);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get",method=RequestMethod.GET)
	public ResponseEntity<List<User>> getByEmail()
	{
		List<User> users = userServ.findUserByEmail();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
