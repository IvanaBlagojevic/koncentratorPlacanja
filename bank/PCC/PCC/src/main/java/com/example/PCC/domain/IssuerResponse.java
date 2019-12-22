package com.example.PCC.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.PCC.dto.IssuerResponseDTO;

@Entity
public class IssuerResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String acquirerOrderId;
    
	@Column
	private Date acquirerTimestamp;
    
	@Column
	private String issuerOrderId;
    
	@Column
	private Date issuerTimestamp;
    
	@Column
	private String account;
    
	@Column
	private String transactionStatus;

	public IssuerResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IssuerResponse(IssuerResponseDTO dtoIP) {
		// TODO Auto-generated constructor stub
		this.acquirerOrderId = dtoIP.getAcquirerOrderId();
		this.acquirerTimestamp = dtoIP.getAcquirerTimestamp();
		this.issuerOrderId = dtoIP.getIssuerOrderId();
		this.issuerTimestamp = dtoIP.getIssuerTimestamp();
		this.account = dtoIP.getAccount();
		this.transactionStatus = dtoIP.getTransactionStatus();
	}

	public IssuerResponse(Long id, String acquirerOrderId, Date acquirerTimestamp, String issuerOrderId,
			Date issuerTimestamp, String account, String transactionStatus) {
		super();
		this.id = id;
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.issuerOrderId = issuerOrderId;
		this.issuerTimestamp = issuerTimestamp;
		this.account = account;
		this.transactionStatus = transactionStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
