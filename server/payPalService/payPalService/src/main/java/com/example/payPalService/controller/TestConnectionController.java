package com.example.payPalService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("test")
public class TestConnectionController {
	
	@GetMapping("/1")
	public String getHello() {
		return "PayPal service";
	}
	
	@GetMapping("/2")
	public String test2() {
		RestTemplate rt = new RestTemplate();
		return rt.getForObject("https://localhost:8086/kpService/test/1", String.class);
	}
}
