package com.example.bankService.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import com.example.bankService.domain.ResponseToKP;
import com.sun.istack.NotNull;


public class ResponseToKPDTO {
	
	
	@NotNull
	private Long merchantOrderId;
	
	@NotNull
	private Long acquirerOrderId;
	
	@NotNull
	private Date acquirerTimestamp;
	
	@NotNull
	private Long paymentId;
	
	@NotNull
	@Size(min=2, max=20)
	private String status;
	
	@NotNull
	@Size(min=4, max=20)
	private String url;
	
	

	public ResponseToKPDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getMerchantOrderId() {
		return merchantOrderId;
	}



	public void setMerchantOrderId(Long merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}



	public Long getAcquirerOrderId() {
		return acquirerOrderId;
	}



	public void setAcquirerOrderId(Long acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}



	public Date getAcquirerTimestamp() {
		return acquirerTimestamp;
	}



	public void setAcquirerTimestamp(Date acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
	}



	public Long getPaymentId() {
		return paymentId;
	}



	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}

	
	
	



	

	
}

