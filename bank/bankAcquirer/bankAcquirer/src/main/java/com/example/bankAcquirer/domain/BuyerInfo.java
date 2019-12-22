package com.example.bankAcquirer.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.bankAcquirer.dto.BuyerInfoDTO;

@Entity
public class BuyerInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String pan;
	
	@Column(nullable = false)
	private String securityCode;
	
	@Column(nullable = false)
	private String cardHolderName;
	
	@Column(nullable = false)
	private Date dateTillExpired;

	@OneToOne
    protected Payment payment = new Payment();
	
	public BuyerInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public BuyerInfo(Long id, String pan, String securityCode, String cardHolderName, Date dateTillExpired) {
		super();
		this.id = id;
		this.pan = pan;
		this.securityCode = securityCode;
		this.cardHolderName = cardHolderName;
		this.dateTillExpired = dateTillExpired;
	}



	public BuyerInfo(BuyerInfoDTO dto) {
		// TODO Auto-generated constructor stub
		this.pan = dto.getPan();
		this.securityCode = dto.getSecurityCode();
		this.cardHolderName = dto.getCardHolderName();
		this.dateTillExpired = dto.getDateTillExpired();
	}
	
	

	public Payment getPayment() {
		return payment;
	}



	public void setPayment(Payment payment) {
		this.payment = payment;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
