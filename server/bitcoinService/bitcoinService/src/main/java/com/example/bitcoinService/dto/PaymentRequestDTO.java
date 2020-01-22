package com.example.bitcoinService.dto;

public class PaymentRequestDTO {

	//private String merchantOrderId;
	private String merchantIssn; 	
	private double amount;
    //private String currency;

    public PaymentRequestDTO() {
    }
    
    

	public PaymentRequestDTO(String merchantIssn, double amount) {
		//this.merchantOrderId = merchantOrderId;
		this.merchantIssn = merchantIssn;
		this.amount = amount;
		//this.currency = currency;
	}



	/*public String getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}*/

	public double getAmount() {
		return amount;
	}

	public String getMerchantIssn() {
		return merchantIssn;
	}

	public void setMerchantIssn(String merchantIssn) {
		this.merchantIssn = merchantIssn;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	/*public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}*/


	
    
    
}
