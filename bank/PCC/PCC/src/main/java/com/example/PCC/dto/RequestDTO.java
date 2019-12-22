package com.example.PCC.dto;

import java.util.Date;

import javax.persistence.Column;

public class RequestDTO {
	
	private String acquirerOrderId;
	
	private Date acquirerTimestamp;

	private String acquirerAccount;
	
	private double amount;
	
	private String pan;
	
	private String cardHolderName;
	
	private Date dateTillExpired;

	private String securityCode;

	private Boolean status;

	public RequestDTO() {
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

	public String getAcquirerAccount() {
		return acquirerAccount;
	}

	public void setAcquirerAccount(String acquirerAccount) {
		this.acquirerAccount = acquirerAccount;
	}

	public double getAmount() {
		return amount;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public Date getDateTillExpired() {
		return dateTillExpired;
	}

	public void setDateTillExpired(Date dateTillExpired) {
		this.dateTillExpired = dateTillExpired;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	

}
