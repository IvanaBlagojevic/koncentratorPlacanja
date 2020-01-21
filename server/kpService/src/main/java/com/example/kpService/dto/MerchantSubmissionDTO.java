package com.example.kpService.dto;

import java.util.List;

import com.example.kpService.domain.MethodOfPayment;

public class MerchantSubmissionDTO {

	private String companyName;
	
	private String merchantName;
	
	private String username;
	
	private List<MethodOfPaymentDTO> supportedMethods;
	
	public MerchantSubmissionDTO() {}

	public MerchantSubmissionDTO(String companyName, String merchantName, String username,
			List<MethodOfPaymentDTO> supportedMethods) {
		super();
		this.companyName = companyName;
		this.merchantName = merchantName;
		this.username = username;
		this.supportedMethods = supportedMethods;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<MethodOfPaymentDTO> getSupportedMethods() {
		return supportedMethods;
	}

	public void setSupportedMethods(List<MethodOfPaymentDTO> supportedMethods) {
		this.supportedMethods = supportedMethods;
	}
	
}
