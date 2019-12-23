package com.example.payPalService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payPalService.service.UserPayPalService;

@RefreshScope
@RestController
@RequestMapping("user")
public class UserPayPalController {

	@Autowired
	private UserPayPalService userService;
	
}
