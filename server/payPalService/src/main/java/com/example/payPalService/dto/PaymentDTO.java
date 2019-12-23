package com.example.payPalService.dto;

public class PaymentDTO {
	
	private String merchantId;
	
	private String merchantEmail;
	
	private double amount;
	
	public PaymentDTO() {}

	public PaymentDTO(String merchantId, String merchantEmail, double amount) {
		super();
		this.merchantId = merchantId;
		this.merchantEmail = merchantEmail;
		this.amount = amount;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantEmail() {
		return merchantEmail;
	}

	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
