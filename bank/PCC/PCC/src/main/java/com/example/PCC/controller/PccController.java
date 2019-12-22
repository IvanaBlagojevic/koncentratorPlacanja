package com.example.PCC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.PCC.domain.Bank;
import com.example.PCC.domain.IssuerResponse;
import com.example.PCC.domain.Request;
import com.example.PCC.dto.InterbankRequestDTO;
import com.example.PCC.dto.InterbankResponseDTO;
import com.example.PCC.dto.IssuerResponseDTO;
import com.example.PCC.dto.RequestDTO;
import com.example.PCC.service.PccService;

@RestController
public class PccController {
	
	@Autowired
	private PccService pccService;
	
	@RequestMapping(value = "/pcc", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> pccRequest(@RequestBody InterbankRequestDTO request, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<String>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		}
		
		Bank issuerBank = this.pccService.findBank(request.getPan());
		if(issuerBank==null) {
			return new ResponseEntity<String>("Issuer bank not found", HttpStatus.NOT_FOUND);
		}
		
		
		IssuerResponseDTO dtoIP = this.pccService.requestToIssuer(issuerBank, request);
	        
		IssuerResponse toSave = new IssuerResponse(dtoIP);
		this.pccService.saveResponse(toSave);
	        
		InterbankResponseDTO response = new InterbankResponseDTO(dtoIP.getAccount(), dtoIP.getTransactionStatus());
		
		
        return new ResponseEntity<InterbankResponseDTO>(response, HttpStatus.OK);
	}
}
