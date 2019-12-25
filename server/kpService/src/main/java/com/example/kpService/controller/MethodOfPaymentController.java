package com.example.kpService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.kpService.domain.MethodOfPayment;
import com.example.kpService.domain.User;
import com.example.kpService.dto.MethodOfPaymentDTO;
import com.example.kpService.service.MethodOfPaymentService;


@RestController
@RequestMapping("methodOfPayment")
@CrossOrigin("https://localhost:1234")
public class MethodOfPaymentController {

	@Autowired
	private MethodOfPaymentService paymentServ;
	
	@RequestMapping(value = "/getAll",method=RequestMethod.GET)
	public ResponseEntity<List<MethodOfPayment>> getAllMethods()
	{
		List<MethodOfPayment> methods = paymentServ.getAllMethodsOfPayment();
		return new ResponseEntity<>(methods, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<MethodOfPaymentDTO> createNewMethodOfPayment(@RequestBody MethodOfPaymentDTO dto) {
		
		System.out.println("CREATE method ");
		MethodOfPayment method =  dto.convertToDomain();
		paymentServ.save(method);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/aim/{id}", method = RequestMethod.GET)
	public String test3(@PathVariable("id") Long id) {
		
		MethodOfPayment method = paymentServ.findOneById(id);
		RestTemplate rt = new RestTemplate();
		return rt.getForObject(method.getPath(), String.class);
	}
}
