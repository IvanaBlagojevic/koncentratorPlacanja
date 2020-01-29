package com.example.bankService.domain;

import java.util.Date;

import javax.persistence.*;

import com.example.bankService.dto.PaymentDTO;

@Entity
public class Payment {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
    private String merchantId;
	
	@Column(nullable = false)
    private String merchantPassword;
	
	@Column(nullable = false)
	private double amount;
	
	@Column(nullable = false)
	private Long merchantOrderId;

	@Column(nullable = false)
	private Date merchantTimestamp;
	
	@Column(nullable = false)
	private String successUrl;

	@Column(nullable = false)
	private String failedUrl;

	@Column(nullable = false)
	private String errorUrl;
	
	@Column(nullable = false)
	private String paymentUrl;
	
	@Column(nullable = false)
	private String paymentId;
	
	@Enumerated(EnumType.STRING)
	@Column
	private StatusOfPayment status; // true - success, false - abortion

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(PaymentDTO paymentResponse) {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
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

	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public StatusOfPayment getStatus() {
		return status;
	}

	public void setStatus(StatusOfPayment status) {
		this.status = status;
	}

	
}