package com.example.kpService.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("test")
public class TestConnectionController {
	
	@GetMapping("/1")
	public String getHello() {
		return "Concentrator service";
	}
	
	@GetMapping("/2")
	public String test2() {
		RestTemplate rt = new RestTemplate();
		return rt.getForObject("http://localhost:8087/test/1", String.class);
	}

	@GetMapping("/3") 
	public String test3() {
		RestTemplate rt = new RestTemplate();
		return rt.getForObject("http://localhost:8088/test/1", String.class);
	}
}
