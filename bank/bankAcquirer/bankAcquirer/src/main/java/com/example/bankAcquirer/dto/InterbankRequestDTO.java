package com.example.bankAcquirer.dto;

import java.util.Date;

public class InterbankRequestDTO {
	
	private Long acquirerOrderId;
	
	private Date acquirerTimestamp;
	
	private String acquirerAccount;
	
	private double amount;
	
	private String pan;
	
	private String securityCode;
	
	private String cardHolderName;
	
	private Date dateTillExpired;
	
	private Long paymentId;

	public InterbankRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getAcquirerAccount() {
		return acquirerAccount;
	}

	public void setAcquirerAccount(String acquirerAccount) {
		this.acquirerAccount = acquirerAccount;
	}

	public double getAmount() {
		return amount;
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

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
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

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	
	
}
