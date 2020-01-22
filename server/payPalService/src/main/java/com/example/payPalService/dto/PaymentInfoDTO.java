package com.example.payPalService.dto;

import java.util.Date;

import com.example.payPalService.domain.OrderStatus;

public class PaymentInfoDTO {


    private Long id;
    
    private String merchantIssn;
    
    private String userEmail;
    
    private Long orderNumberId;
    
    private OrderStatus isPaid;
    
    private String paymentMethod;
    
    private Date created;

    private Date updated;
    
    public PaymentInfoDTO() {
    	
    }

	public PaymentInfoDTO(String merchantIssn, String userEmail, Long orderNumberId, OrderStatus isPaid,
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMerchantIssn() {
		return merchantIssn;
	}

	public void setMerchantIssn(String merchantIssn) {
		this.merchantIssn = merchantIssn;
	}

	public void setOrderNumberId(Long orderNumberId) {
		this.orderNumberId = orderNumberId;
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

	public void setOrderNumerId(Long orderNumberId) {
		this.orderNumberId = orderNumberId;
	}

	

	public OrderStatus getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(OrderStatus isPaid) {
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

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
    
    
}
