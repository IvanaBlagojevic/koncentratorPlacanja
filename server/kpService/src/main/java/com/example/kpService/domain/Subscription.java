package com.example.kpService.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subscription { //ova klasa predstavlja finalnu preplatu

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String merchantUsername;
	
	@Column(nullable = false)
	private double priceAmount;
	
	@Column(nullable = false)
	private String planId;
	
	@Column(nullable = false)
	private boolean active;
	
	@Column
	private String agreementId;
	
	@Column
	private String subEmail;
	
	public Subscription() {
		
		
	}
	
	public Subscription(String merchantUsername, double priceAmount, String planId, boolean active,
			String agreementId, String subEmail) {
		super();
		this.merchantUsername = merchantUsername;
		this.priceAmount = priceAmount;
		this.planId = planId;
		this.active = active;
		this.agreementId = agreementId;
		this.subEmail = subEmail;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMerchantUsername() {
		return merchantUsername;
	}

	public void setMerchantUsername(String merchantUsername) {
		this.merchantUsername = merchantUsername;
	}

	public double getPriceAmount() {
		return priceAmount;
	}

	public void setPriceAmount(double priceAmount) {
		this.priceAmount = priceAmount;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getSubEmail() {
		return subEmail;
	}

	public void setSubEmail(String subEmail) {
		this.subEmail = subEmail;
	}
	
}
