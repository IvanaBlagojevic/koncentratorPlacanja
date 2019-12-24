package com.example.bankAcquirer.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.example.bankAcquirer.dto.PaymentDTO;


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
	private Long paymentId;
	
	@Column
	private Boolean status; // true - success, false - abortion

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Payment(PaymentDTO payment) {
		// TODO Auto-generated constructor stub
		this.amount = payment.getAmount();
		this.merchantOrderId = payment.getMerchantOrderId();
		this.merchantTimestamp = payment.getMerchantTimestamp();
		this.successUrl = payment.getSuccessUrl();
		this.failedUrl = payment.getFailedUrl();
		this.errorUrl = payment.getErrorUrl();
		this.paymentId = payment.getPaymentId();
		this.paymentUrl = payment.getPaymentUrl();
		this.merchantId= payment.getMerchantId();
		this.merchantPassword= payment.getMerchantPassword();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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




	 

}
