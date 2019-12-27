package com.example.bitcoinService.dto;

public class PaymentRequestDTO {

	
	//private String merchantOrderId;
	private String merchantEmail; 
	private double amount;
    //private String currency;

    public PaymentRequestDTO() {
    }
    
    

	public PaymentRequestDTO(String merchantEmail, double amount) {
		//this.merchantOrderId = merchantOrderId;
		this.merchantEmail = merchantEmail;
		this.amount = amount;
		//this.currency = currency;
	}



	/*public String getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}*/

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

	/*public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}*/


	
    
    
}
