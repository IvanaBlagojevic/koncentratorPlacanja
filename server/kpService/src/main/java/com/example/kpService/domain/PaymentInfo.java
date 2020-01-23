package com.example.kpService.domain;

import java.util.Date;

import javax.persistence.*;

import com.example.kpService.dto.PaymentInfoDTO;

@Entity
public class PaymentInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String merchantIssn;
    
    @Column
    private String userEmail;
    
   // @Column(nullable = false)
   // private Long idNC;
   
    
    //from mc
    @Column
    private Long orderNumberId;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus isPaid;
    
    //mora biti naziv mikroservisa da bi bilo generickije
    @Column
    private String paymentMethod;
    
    @Column
    private Date created;

    @Column
    private Date updated;
    
    @Column(nullable = false)
    private String orderNumberNC;
    
    @Column(nullable = false)
    private String successURL;
    
    @Column(nullable = false)
    private String errorURL;
    
    @Column(nullable = false)
    private String failedURL;
    
    @Column(nullable = false)
    private Double amount;
    
    public PaymentInfo() {
    	
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





	public PaymentInfoDTO convertToDTO() {
		// TODO Auto-generated method stub
		PaymentInfoDTO method = new PaymentInfoDTO();
		method.setMerchantIssn(this.merchantIssn);
		method.setOrderNumberId(this.orderNumberId);
		method.setPaid(this.isPaid);
		method.setPaymentMethod(this.paymentMethod);
		method.setUserEmail(this.userEmail);
		method.setCreated(this.created);
		method.setUserEmail(this.userEmail);
		method.setAmount(this.amount);
		method.setErrorURL(this.errorURL);
		method.setSuccessURL(this.successURL);
		method.setFailedURL(this.failedURL);
		return method;
	}

	

    
	
    
}
