package com.example.payPalService.dto;

public class PaymentDTO {
	
	private String merchantId;
	
	private String merchantIssn;
	
	private double amount;
	
	public PaymentDTO() {}

	public PaymentDTO(String merchantId, String merchantIssn, double amount) {
		super();
		this.merchantId = merchantId;
		this.merchantIssn = merchantIssn;
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
