package com.example.kpService.dto;

import java.util.List;

import com.example.kpService.domain.MerchantSystem;
import com.example.kpService.domain.MethodOfPayment;
import com.example.kpService.domain.SubscriptionPlan;

public class MerchantSubmissionDTO {

	private String companyName;
	
	private String merchantName;
	
	private String username;
	
	private MerchantSystemDTO SCsystem;
	
	private List<MethodOfPaymentDTO> supportedMethods;
	
	private List<SubscriptionPlanParamsDTO> subscriptions;
	
	public MerchantSubmissionDTO() {}

	public MerchantSubmissionDTO(String companyName, String merchantName, String username, MerchantSystemDTO SCsystem,
			List<MethodOfPaymentDTO> supportedMethods, List<SubscriptionPlanParamsDTO> subscriptions) {
		super();
		this.companyName = companyName;
		this.merchantName = merchantName;
		this.username = username;
		this.SCsystem = SCsystem;
		this.supportedMethods = supportedMethods;
		this.subscriptions = subscriptions;
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
	
	public MerchantSystemDTO getSCsystem() {
		return SCsystem;
	}

	public void setSCsystem(MerchantSystemDTO sCsystem) {
		SCsystem = sCsystem;
	}

	public List<MethodOfPaymentDTO> getSupportedMethods() {
		return supportedMethods;
	}

	public void setSupportedMethods(List<MethodOfPaymentDTO> supportedMethods) {
		this.supportedMethods = supportedMethods;
	}

	public List<SubscriptionPlanParamsDTO> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<SubscriptionPlanParamsDTO> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
}
