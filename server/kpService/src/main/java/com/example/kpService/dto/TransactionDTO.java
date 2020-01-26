package com.example.kpService.dto;

import com.example.kpService.domain.PaymentInfo;
import com.example.kpService.domain.PaymentStatus;

public class TransactionDTO {
private String buyerEmail;
	
	private Double amount;
	
	private String orderId;
	
	private Long journalId;
	
	private PaymentStatus status;
	
	private String successURL;
	
	private String errorURL;
	
	private String failedURL;
	
	 private String merchantIssn;
	
	public TransactionDTO() {
		super();
		// TODO Auto-generated constructor stub
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




	public String getFailedURL() {
		return failedURL;
	}




	public void setFailedURL(String failedURL) {
		this.failedURL = failedURL;
	}




	

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}




	public Long getJournalId() {
		return journalId;
	}




	public String getMerchantIssn() {
		return merchantIssn;
	}








	public void setMerchantIssn(String merchantIssn) {
		this.merchantIssn = merchantIssn;
	}








	public void setJournalId(Long journalId) {
		this.journalId = journalId;
	}








	public PaymentStatus getStatus() {
		return status;
	}








	public void setStatus(PaymentStatus status) {
		this.status = status;
	}








	public PaymentInfo convertToDomain() {
		// TODO Auto-generated method stub
		PaymentInfo payment = new PaymentInfo();
		payment.setErrorURL(this.errorURL);
		payment.setFailedURL(this.failedURL);
		payment.setIsPaid(this.status);
		payment.setOrderNumberNC(this.orderId);
		payment.setSuccessURL(this.successURL);
		payment.setUserEmail(this.buyerEmail);
		payment.setMerchantIssn(this.merchantIssn);
		payment.setAmount(this.amount);
		return payment;
	}

	
	
	

}


