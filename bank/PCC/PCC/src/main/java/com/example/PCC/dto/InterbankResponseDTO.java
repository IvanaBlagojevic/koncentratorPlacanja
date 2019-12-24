package com.example.PCC.dto;

import java.util.Date;

public class InterbankResponseDTO {
	
	private String accountNumber;//broj od issuera
	 
	private String status;
	
	private Long acquirerOrderId;
    
	private Date acquirerTimestamp;
	 
	private String issuerOrderId;
    
	private Date issuerTimestamp; 

	public InterbankResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	




	public InterbankResponseDTO(String accountNumber, String status, Long acquirerOrderId, Date acquirerTimestamp,
			String issuerOrderId, Date issuerTimestamp) {
		super();
		this.accountNumber = accountNumber;
		this.status = status;
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.issuerOrderId = issuerOrderId;
		this.issuerTimestamp = issuerTimestamp;
	}










	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	    
	 
}
