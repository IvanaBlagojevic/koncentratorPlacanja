package com.example.payPalService.dto;

public class PayPalCreationResponse {
	
	private String redirectUrl;
	
	public PayPalCreationResponse() {}

	public PayPalCreationResponse(String redirectUrl) {
		super();
		this.redirectUrl = redirectUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
}
