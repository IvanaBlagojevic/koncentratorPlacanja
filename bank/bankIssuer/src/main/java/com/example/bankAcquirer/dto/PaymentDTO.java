package com.example.bankAcquirer.dto;

import java.util.Date;
import java.util.Optional;

import com.example.bankAcquirer.domain.Payment;

public class PaymentDTO {
	private String merchantId;
    
    private String merchantPassword;
	
	private double amount;
	
	private String merchantOrderId;

	private Date merchantTimestamp;
	
	private String successUrl;

	private String failedUrl;

	private String errorUrl;
	
	private String paymentUrl;

    private String paymentId;

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
		this.successUrl = payment.get().getSuccessUrl();
		this.failedUrl = payment.get().getFailedUrl();
		this.errorUrl = payment.get().getErrorUrl();
		this.paymentId = payment.get().getPaymentId();
		this.paymentUrl = payment.get().getPaymentUrl();
		this.merchantPassword= payment.get().getMerchantPassword();
	}

	public PaymentDTO(String merchantId, String merchantPassword, double amount, String merchantOrderId,
			Date merchantTimestamp, String successUrl, String failedUrl, String errorUrl, String paymentUrl,
			String paymentId) {
		super();
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
		this.amount = amount;
		this.merchantOrderId = merchantOrderId;
		this.merchantTimestamp = merchantTimestamp;
		this.successUrl = successUrl;
		this.failedUrl = failedUrl;
		this.errorUrl = errorUrl;
		this.paymentUrl = paymentUrl;
		this.paymentId = paymentId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
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

	public String getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public Date getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(Date merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getFailedUrl() {
		return failedUrl;
	}

	public void setFailedUrl(String failedUrl) {
		this.failedUrl = failedUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}

	public String getPaymentUrl() {
		return paymentUrl;
	}

	public void setPaymentUrl(String paymentURL) {
		this.paymentUrl = paymentURL;
	}

	
	

}
