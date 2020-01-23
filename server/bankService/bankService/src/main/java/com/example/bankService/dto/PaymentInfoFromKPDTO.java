package com.example.bankService.dto;

public class PaymentInfoFromKPDTO {
	
	private String merchantId;
	
	private String merchantIssn;
	
	private double amount;
	
	public PaymentInfoFromKPDTO() {}

	public PaymentInfoFromKPDTO(String merchantId, String merchantEmail, double amount) {
		super();
		this.merchantId = merchantId;
		this.merchantIssn = merchantEmail;
		this.amount = amount;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	

	public String getMerchantIssn() {
		return merchantIssn;
	}

	public void setMerchantIssn(String merchantIssn) {
		this.merchantIssn = merchantIssn;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


}
