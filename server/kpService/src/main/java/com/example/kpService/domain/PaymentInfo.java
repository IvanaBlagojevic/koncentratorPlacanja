package com.example.kpService.domain;

import java.util.Date;

import javax.persistence.*;

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
    @Column(nullable = false)
    private Long orderNumberId;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus isPaid;
    
    //mora biti naziv mikroservisa da bi bilo generickije
    @Column(nullable = false)
    private String paymentMethod;
    
    @Column
    private Date created;

    @Column
    private Date updated;
    
    public PaymentInfo() {
    	
    }

	public PaymentInfo(String merchantIssn, String userEmail, Long orderNumberId, PaymentStatus isPaid,
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
    
	
    
}
