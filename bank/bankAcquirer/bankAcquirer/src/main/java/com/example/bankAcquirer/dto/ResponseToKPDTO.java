package com.example.bankAcquirer.dto;

import java.util.Date;

import javax.persistence.Column;

import com.example.bankAcquirer.domain.ResponseToKP;

public class ResponseToKPDTO {
	
	private Long merchantOrderId;
	
	private Long acquirerOrderId;
    
	private Date acquirerTimestamp;
	
	private Long paymentId;
	
	private String status;
	
	private String url;
	

	public ResponseToKPDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public ResponseToKPDTO(Long merchantOrderId, Long acquirerOrderId, Date acquirerTimestamp, Long paymentId,
			String status, String url) {
		super();
		this.merchantOrderId = merchantOrderId;
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.paymentId = paymentId;
		this.status = status;
		this.url = url;
	}



	public ResponseToKPDTO(ResponseToKP response) {
		// TODO Auto-generated constructor stub
		this.merchantOrderId = response.getMerchantOrderId();
		this.acquirerOrderId = response.getAcquirerOrderId();
		this.acquirerTimestamp = response.getAcquirerTimestamp();
		this.paymentId = response.getPaymentId();
		this.status = response.getStatus();
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
