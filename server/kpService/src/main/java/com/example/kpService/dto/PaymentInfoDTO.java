package com.example.kpService.dto;

import java.util.Date;

import javax.persistence.Column;

import com.example.kpService.domain.MethodOfPayment;
import com.example.kpService.domain.PaymentInfo;
import com.example.kpService.domain.PaymentStatus;

public class PaymentInfoDTO {

    private String merchantIssn;
    
    private String userEmail;
    
    private Long orderNumberId;
    
    private PaymentStatus isPaid;
    
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
    

	public PaymentInfoDTO(String merchantIssn, String userEmail, Long orderNumberId, PaymentStatus isPaid,
			String paymentMethod, Date created, Date updated, Double amount) {
		super();
		this.merchantIssn = merchantIssn;
		this.userEmail = userEmail;
		this.orderNumberId = orderNumberId;
		this.isPaid = isPaid;
		this.paymentMethod = paymentMethod;
		this.created = created;
		this.updated = updated;
		this.amount = amount;
	}


	
	public String getMerchantIssn() {
		return merchantIssn;
	}


	public void setMerchantIssn(String merchantIssn) {
		this.merchantIssn = merchantIssn;
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

	public PaymentStatus isPaid() {
		return isPaid;
	}

	public void setPaid(PaymentStatus isPaid) {
		this.isPaid = isPaid;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public PaymentStatus getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(PaymentStatus isPaid) {
		this.isPaid = isPaid;
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

	
	
	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public PaymentInfo convertToDomain() {
		
		PaymentInfo method = new PaymentInfo();
		method.setMerchantIssn(this.merchantIssn);
		method.setOrderNumberId(this.orderNumberId);
		method.setPaid(PaymentStatus.CREATED);
		method.setPaymentMethod(this.paymentMethod);
		method.setUserEmail(this.userEmail);
		method.setCreated(this.created);
		method.setAmount(this.amount);
		method.setOrderNumberNC(this.orderNumberNC);
		return method;
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
	
	
}
