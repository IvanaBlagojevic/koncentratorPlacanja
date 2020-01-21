package com.example.kpService.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.kpService.domain.Merchant;
import com.example.kpService.domain.MerchantSystem;
import com.example.kpService.domain.MethodOfPayment;
import com.example.kpService.domain.MethodOfPaymentFields;
import com.example.kpService.dto.MerchantSubmissionDTO;
import com.example.kpService.dto.MethodOfPaymentDTO;
import com.example.kpService.dto.MethodOfPaymentFieldsDTO;
import com.example.kpService.service.MerchantSystemService;
import com.example.kpService.service.MethodOfPaymentService;

@Component
public class KpServiceConverter {
	
	@Autowired
	private MethodOfPaymentService methodService;
	
	@Autowired 
	private MerchantSystemService systemService;
	
	
	public Merchant convert(MerchantSubmissionDTO dto) {
		
		List<MethodOfPayment> payments = new ArrayList<MethodOfPayment>();
		MerchantSystem company = systemService.findByName(dto.getCompanyName());
		
		for(MethodOfPaymentDTO m : dto.getSupportedMethods()) {
			
			payments.add(methodService.getOneByName(m.getName()));
			
		}
		
		return new Merchant(dto.getMerchantName(), dto.getUsername(), company, payments);
	}
	
	public MerchantSubmissionDTO convert(Merchant merchant) {
		
		return new MerchantSubmissionDTO(merchant.getSystem().getSystemName(), 
				merchant.getName(), merchant.getUsername(), this.convertMethods(merchant.getPaymentMethods()));
	}
	
	
	public MethodOfPaymentDTO convert(MethodOfPayment method) {
		
		return new MethodOfPaymentDTO(method.getId(), method.getName(), method.getPath(), this.convertFields(method.getFields()));
	}
	
	
	public MethodOfPaymentFieldsDTO convert(MethodOfPaymentFields field)
	{
		return new MethodOfPaymentFieldsDTO(field.getCode(),field.getName(),field.getType(),null);
	}
	
	
	public List<MethodOfPaymentDTO> convertMethods(List<MethodOfPayment> list){
	
		List<MethodOfPaymentDTO> ret = new ArrayList<MethodOfPaymentDTO>();
		
		for(MethodOfPayment method : list)
		{
			ret.add(this.convert(method));
		}
		
		return ret;
	}
	
	
	
	public List<MethodOfPaymentFieldsDTO> convertFields(List<MethodOfPaymentFields> list){
		
		List<MethodOfPaymentFieldsDTO> ret = new ArrayList<MethodOfPaymentFieldsDTO>();
		
		for(MethodOfPaymentFields field : list)
		{
			ret.add(this.convert(field));
		}
		
		return ret;
	}

}
