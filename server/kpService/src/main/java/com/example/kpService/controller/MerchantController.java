package com.example.kpService.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.kpService.converter.KpServiceConverter;
import com.example.kpService.domain.Merchant;
import com.example.kpService.domain.MerchantSystem;
import com.example.kpService.dto.MerchantSubmissionDTO;
import com.example.kpService.dto.MethodOfPaymentDTO;
import com.example.kpService.dto.MethodOfPaymentFieldsDTO;
import com.example.kpService.service.MerchantService;
import com.example.kpService.service.MerchantSystemService;

@RestController
@RequestMapping("merchant")
@CrossOrigin("https://localhost:1234")
public class MerchantController {
	
	@Autowired
	private MerchantSystemService systemService;
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private KpServiceConverter converter;
	
	@RequestMapping(
			value = "getMerchant/{id}",
			method = RequestMethod.GET)
	public ResponseEntity<MerchantSubmissionDTO> getByUsername(@PathVariable("id") String username){
		
		Merchant merchant = merchantService.getByUsername(username);
		
		if(merchant == null)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}else
		{
			return new ResponseEntity<>(converter.convert(merchant), HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(
	       value = "/registerMerchant",
	       method = RequestMethod.POST,
	       consumes = MediaType.APPLICATION_JSON_VALUE,
	       produces = MediaType.TEXT_PLAIN_VALUE
	)
	public ResponseEntity registerMerchant (@RequestBody MerchantSubmissionDTO merchantSubmission) {
		
		MerchantSystem company = systemService.findByName(merchantSubmission.getCompanyName());
		
		if(company == null)
		{
			return new ResponseEntity<>("Company with given name doesn't exist!", HttpStatus.BAD_REQUEST);
		}
		
		Merchant ret = merchantService.getByUsername(merchantSubmission.getUsername());
		
		if(ret != null)
		{
			return new ResponseEntity<>("Merchant with that issn already exist", HttpStatus.BAD_REQUEST);
		}
		
		
		Merchant merchant = converter.convert(merchantSubmission);
		
		Merchant retM = merchantService.saveMerchant(merchant);
		
		if(retM != null)
		{
			return new ResponseEntity<>("Merchant registration success!", HttpStatus.OK);
			
		}else
		{
			return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	@RequestMapping(
			value = "supportedMethods/{id}",
			method = RequestMethod.GET)
	public ResponseEntity<List<MethodOfPaymentDTO>> getSupportedPaymentMethods(@PathVariable("id") String issn) {
		
		Merchant merchant = merchantService.getByUsername(issn);
		
		if(merchant == null)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		List<MethodOfPaymentDTO> ret = new ArrayList<MethodOfPaymentDTO>();
		
		ret = converter.convertMethods(merchant.getPaymentMethods());
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
		
	}

}
