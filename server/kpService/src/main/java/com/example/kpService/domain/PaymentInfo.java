package com.example.kpService.domain;

import javax.persistence.*;

@Entity
public class PaymentInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String merchantEmail;
    
    @Column(nullable = false)
    private String userEmail;
    
   // @Column(nullable = false)
   // private Long idNC;
    
    //from mc
    @Column(nullable = false)
    private Long orderNumberId;
    
    @Column(nullable = false)
    private boolean isPaid;
    
    @Column(nullable = false)
    private String paymentMethod;
    
    public PaymentInfo() {
    	
    }
    
	public PaymentInfo(Long id, String merchantEmail, String userEmail, Long orderNumberId, boolean isPaid,
			String paymentMethod) {
		super();
		this.id = id;
		this.merchantEmail = merchantEmail;
		this.userEmail = userEmail;
		this.orderNumberId = orderNumberId;
		this.isPaid = isPaid;
		this.paymentMethod = paymentMethod;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMerchantEmail() {
		return merchantEmail;
	}

	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
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

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
    
    
}
