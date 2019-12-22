package com.example.bankAcquirer.dto;

import java.util.Date;

public class IssuerResponseDTO {

	private String acquirerOrderId;
    
	private Date acquirerTimestamp;
    
	private String issuerOrderId;
    
	private Date issuerTimestamp;
    
	private String account;
    
	private String transactionStatus;

	public IssuerResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAcquirerOrderId() {
		return acquirerOrderId;
	}

	public void setAcquirerOrderId(String acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}

	public Date getAcquirerTimestamp() {
		return acquirerTimestamp;
	}

	public void setAcquirerTimestamp(Date acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
	}

	public String getIssuerOrderId() {
		return issuerOrderId;
	}

	public void setIssuerOrderId(String issuerOrderId) {
		this.issuerOrderId = issuerOrderId;
	}

	public Date getIssuerTimestamp() {
		return issuerTimestamp;
	}

	public void setIssuerTimestamp(Date issuerTimestamp) {
		this.issuerTimestamp = issuerTimestamp;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
    
	
	
}
