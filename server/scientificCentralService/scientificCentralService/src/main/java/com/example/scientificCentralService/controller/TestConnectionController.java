package com.example.scientificCentralService.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("test")
@CrossOrigin(origins = "https://localhost:4202")
public class TestConnectionController {
	
	@GetMapping("/1")
	public String getHello() {
		return "Scientific Central Service"; 
	}
	
	@GetMapping("/2") 
	public String test2() {
		RestTemplate rt = new RestTemplate();
		return rt.getForObject("https://localhost:8086/kpService/test/1", String.class);
	}
	
	@RequestMapping(value = "/test",method=RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> paying() {


        return new ResponseEntity<String>("test", HttpStatus.OK);
    }

}
