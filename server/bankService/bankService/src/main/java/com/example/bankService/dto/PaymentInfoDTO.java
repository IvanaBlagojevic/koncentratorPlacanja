package com.example.bankService.dto;

import java.util.Date;

import javax.persistence.Column;

import com.example.bankService.domain.StatusOfPayment;

public class PaymentInfoDTO {
	
	private String merchantIssn;
    
    private String userEmail;
    
    private Long orderNumberId;
    
    private StatusOfPayment isPaid;
    
    private String paymentMethod;
    
    private Date created;

    
    private Date updated;
    
    private String orderNumberNC;
    
    private String successURL;
    
    private String errorURL;
    
    private String failedURL;
    
    private Double amount;
    
    public PaymentInfoDTO() {
    	
    }
    
	
	public PaymentInfoDTO(String merchantIssn, String userEmail, Long orderNumberId, StatusOfPayment isPaid,
			String paymentMethod, Date created, Date updated) {
		super();
		this.merchantIssn = merchantIssn;
		this.userEmail = userEmail;
		this.orderNumberId = orderNumberId;
		this.isPaid = isPaid;
		this.paymentMethod = paymentMethod;
		this.created = created;
		this.updated = updated;
	}

	

	public String getMerchantIssn() {
		return merchantIssn;
	}


	public void setMerchantIssn(String merchantIssn) {
		this.merchantIssn = merchantIssn;
	}


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public Date getUpdated() {
		return updated;
	}


	public void setUpdated(Date updated) {
		this.updated = updated;
	}


	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Long getOrderNumberId() {
		return orderNumberId;
	}

	public void setOrderNumberId(Long orderNumberId) {
		this.orderNumberId = orderNumberId;
	}


	public StatusOfPayment getIsPaid() {
		return isPaid;
	}


	public void setIsPaid(StatusOfPayment isPaid) {
		this.isPaid = isPaid;
	}


	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public String getOrderNumberNC() {
		return orderNumberNC;
	}


	public void setOrderNumberNC(String orderNumberNC) {
		this.orderNumberNC = orderNumberNC;
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


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}

	
}
