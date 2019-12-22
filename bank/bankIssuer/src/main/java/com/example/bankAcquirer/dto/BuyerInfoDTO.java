package com.example.bankAcquirer.dto;

import java.util.Date;

import javax.persistence.Column;

public class BuyerInfoDTO {
	
	private String pan;
	
	private String securityCode;
	
	private String cardHolderName;
	
	private Date dateTillExpired;

	private String paymentId;
	
	public BuyerInfoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public String getPaymentId() {
		return paymentId;
	}



	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
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
	
	
	

}
