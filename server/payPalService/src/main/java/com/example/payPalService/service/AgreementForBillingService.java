package com.example.payPalService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.payPalService.domain.AgreementForBilling;
import com.example.payPalService.repository.AgreementForBillingRepository;

@Service
public class AgreementForBillingService {

	@Autowired
	private AgreementForBillingRepository agrRepository;
	
	public AgreementForBilling saveAgr(AgreementForBilling agr) {
		
		return agrRepository.save(agr);
	}
	
	public AgreementForBilling getById(String id) {
		
		return agrRepository.findByAgreementId(id);
	}
}
