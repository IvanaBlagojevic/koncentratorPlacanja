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
    
    public PaymentInfoDTO() {
    	
    }
    

	public PaymentInfoDTO(String merchantIssn, String userEmail, Long orderNumberId, PaymentStatus isPaid,
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

	public PaymentInfo convertToDomain() {
		
		PaymentInfo method = new PaymentInfo();
		method.setMerchantIssn(this.merchantIssn);
		method.setOrderNumberId(this.orderNumberId);
		method.setPaid(PaymentStatus.CREATED);
		method.setPaymentMethod(this.paymentMethod);
		method.setUserEmail(this.userEmail);
		method.setCreated(this.created);
		
		return method;
	}
}
