package com.example.bitcoinService.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("test")
public class TestController {
	
	@Value("${proba}")
	private String proba;

	@GetMapping("/1")
	public String getHello() {
		return proba;
	}
}
