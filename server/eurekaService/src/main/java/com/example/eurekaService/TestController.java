package com.example.eurekaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
	
		
	 @RequestMapping(value="/hello", method = RequestMethod.GET)
	 public ResponseEntity<String> hello() {
		 System.out.println("Pogodio");
		 return new ResponseEntity<>("Hello World!! You are seeing this only because I TRUST YOU!!!", HttpStatus.OK);
	 
	 }
	
}
