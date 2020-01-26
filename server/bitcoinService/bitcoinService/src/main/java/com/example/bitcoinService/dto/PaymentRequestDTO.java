package com.example.bitcoinService.dto;

public class PaymentRequestDTO {

	//private String merchantOrderId;
	private String merchantIssn; 	
	private double amount;
	private String successURL;
	private String errorURL;
    //private String currency;

    public PaymentRequestDTO() {
    }


	public PaymentRequestDTO(String merchantIssn, double amount, String successURL, String errorURL) {
		this.merchantIssn = merchantIssn;
		this.amount = amount;
		this.successURL = successURL;
		this.errorURL = errorURL;
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



	public String getSuccessURL() {
		return successURL;
	}



	public void setSuccessURL(String successURL) {
		this.successURL = successURL;
	}



	public String getErrorURL() {
		return errorURL;
	}



	public void setErrorURL(String errorURL) {
		this.errorURL = errorURL;
	}
	
	

	/*public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}*/


	
    
    
}
