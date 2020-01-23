package com.example.bankAcquirer.dto;

import java.util.Date;
import java.util.Optional;

import com.example.bankAcquirer.domain.Payment;

public class PaymentDTO {
	
	private String merchantId;
    
    private String merchantPassword;
	
	private double amount;
	
	private Long merchantOrderId;

	private Date merchantTimestamp;
	
	private String successURL;

	private String failedURL;

	private String errorURL;
	
	private String paymentUrl;

    private Long paymentId;

	public PaymentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentDTO(Optional<Payment> payment) {
		// TODO Auto-generated constructor stub
		this.merchantId = payment.get().getMerchantId();
		this.amount = payment.get().getAmount();
		this.merchantOrderId = payment.get().getMerchantOrderId();
		this.merchantTimestamp = payment.get().getMerchantTimestamp();
		this.successURL = payment.get().getSuccessUrl();
		this.failedURL = payment.get().getFailedUrl();
		this.errorURL = payment.get().getErrorUrl();
		this.paymentId = payment.get().getPaymentId();
		this.paymentUrl = payment.get().getPaymentUrl();
		this.merchantPassword= payment.get().getMerchantPassword();
	}

	public PaymentDTO(String merchantId, String merchantPassword, double amount, Long merchantOrderId,
			Date merchantTimestamp, String successUrl, String failedUrl, String errorUrl, String paymentUrl,
			Long paymentId) {
		super();
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
		this.amount = amount;
		this.merchantOrderId = merchantOrderId;
		this.merchantTimestamp = merchantTimestamp;
		this.successURL = successUrl;
		this.failedURL = failedUrl;
		this.errorURL = errorUrl;
		this.paymentUrl = paymentUrl;
		this.paymentId = paymentId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Long getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(Long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public Date getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(Date merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}

	

	public String getSuccessURL() {
		return successURL;
	}

	public void setSuccessURL(String successURL) {
		this.successURL = successURL;
	}

	public String getFailedURL() {
		return failedURL;
	}

	public void setFailedURL(String failedURL) {
		this.failedURL = failedURL;
	}

	public String getErrorURL() {
		return errorURL;
	}

	public void setErrorURL(String errorURL) {
		this.errorURL = errorURL;
	}

	public String getPaymentUrl() {
		return paymentUrl;
	}

	public void setPaymentUrl(String paymentURL) {
		this.paymentUrl = paymentURL;
	}

	
	

}
