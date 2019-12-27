package com.example.bankService.dto;


public class PaymentInfoDTO {
	
	private String merchantEmail;
    
    private String userEmail;
    
    private Long orderNumerId;
    
    private boolean isPaid;
    
    private String paymentMethod;
    
    public PaymentInfoDTO() {
    	
    }
    
	public PaymentInfoDTO( String merchantEmail, String userEmail, Long orderNumerId, boolean isPaid,
			String paymentMethod) {
		this.merchantEmail = merchantEmail;
		this.userEmail = userEmail;
		this.orderNumerId = orderNumerId;
		this.isPaid = isPaid;
		this.paymentMethod = paymentMethod;
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

	public Long getOrderNumerId() {
		return orderNumerId;
	}

	public void setOrderNumerId(Long orderNumerId) {
		this.orderNumerId = orderNumerId;
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
