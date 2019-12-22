package com.example.PCC.domain;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.PCC.dto.RequestDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String acquirerOrderId;
	
	@Column
	private Date acquirerTimestamp;
	
	@Column
	private String acquirerAccount;
	
	@Column
	private double amount;
	
	@Column
	private String pan;
	
	@Column
	private String securityCode;
	
	@Column
	private String cardHolderName;
	
	@Column
	private Date dateTillExpired;
	
	@Column
	private Boolean status;

	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Request(Long id, String acquirerOrderId, Date acquirerTimestamp, String acquirerAccount, double amount,
			String pan, String securityCode, String cardHolderName, Date dateTillExpired, Boolean status) {
		super();
		this.id = id;
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.acquirerAccount = acquirerAccount;
		this.amount = amount;
		this.pan = pan;
		this.securityCode = securityCode;
		this.cardHolderName = cardHolderName;
		this.dateTillExpired = dateTillExpired;
		this.status = status;
	}



	public Request(RequestDTO dtoIP) {
		// TODO Auto-generated constructor stub
		this.acquirerOrderId = dtoIP.getAcquirerOrderId();
		this.acquirerTimestamp = dtoIP.getAcquirerTimestamp();
		this.acquirerAccount = dtoIP.getAcquirerAccount();
		this.amount = dtoIP.getAmount();
		this.pan = dtoIP.getPan();
		this.securityCode = dtoIP.getSecurityCode();
		this.cardHolderName = dtoIP.getCardHolderName();
		this.dateTillExpired = dtoIP.getDateTillExpired();
		this.status = dtoIP.getStatus();
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

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
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
