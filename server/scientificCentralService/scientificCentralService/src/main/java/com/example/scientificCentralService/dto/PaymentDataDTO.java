package com.example.scientificCentralService.dto;

public class PaymentDataDTO {
	
	private String merchantEmail;
	
	private Double amount;

	public PaymentDataDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMerchantEmail() {
		return merchantEmail;
	}

	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	

}
