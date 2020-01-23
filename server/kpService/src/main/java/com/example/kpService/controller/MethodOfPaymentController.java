package com.example.kpService.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.example.kpService.domain.MethodOfPaymentFields;
import com.example.kpService.domain.PaymentInfo;
import com.example.kpService.dto.MethodOfPaymentDTO;
import com.example.kpService.dto.PaymentInfoDTO;
import com.example.kpService.service.MethodOfPaymentFieldsService;
import com.example.kpService.service.MethodOfPaymentService;
import com.example.kpService.service.PaymentInfoService;


@RestController
@RequestMapping("methodOfPayment")
@CrossOrigin("https://localhost:1234")
public class MethodOfPaymentController {

	@Autowired
	private MethodOfPaymentService paymentServ;
	@Autowired
	private MethodOfPaymentFieldsService fieldsSerivce;
	
	@Autowired
	private PaymentInfoService pis;
	
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
		List<MethodOfPaymentFields> fields = new ArrayList<MethodOfPaymentFields>();
		for(int i=0; i<dto.getFields().size(); i++) {
			System.out.println("code "+dto.getFields().get(i).getCode());
			MethodOfPaymentFields field =this.fieldsSerivce.save(new MethodOfPaymentFields(dto.getFields().get(i)));
			fields.add(field);
		}
		method.setFields(fields);
		paymentServ.save(method);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/aim/{id}", method = RequestMethod.GET)
	public String test3(@PathVariable("id") Long id) {
		
		MethodOfPayment method = paymentServ.findOneById(id);
		RestTemplate rt = new RestTemplate();
		return rt.getForObject(method.getPath(), String.class);
	}
	
	@RequestMapping(
			value = "getPaymentInfo/{id}",
			method = RequestMethod.GET)
	public ResponseEntity<?> getByUsername(@PathVariable("id") String orderId){
		System.out.println("pogodio get "+orderId);
		PaymentInfo payment = this.pis.findOneByOrderNumberNC(orderId);
		
		if(payment == null)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}else
		{
			
			PaymentInfoDTO paymentDTO = payment.convertToDTO();
			return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
		}
	}
	
	
}
