package com.example.kpService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.kpService.domain.User;
import com.example.kpService.service.UserService;



@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userServ;
	
	@RequestMapping(value = "/get",method=RequestMethod.GET)
	public ResponseEntity<List<User>> getByEmail()
	{
		List<User> users = userServ.findUserByEmail();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
