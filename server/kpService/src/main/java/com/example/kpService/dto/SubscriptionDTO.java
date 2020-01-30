package com.example.kpService.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SubscriptionDTO {

	private Long id;
	
	private String merchantUsername;
	
	private String type;
	
	private double priceAmount;
	
	private String planId;
	
	private boolean active;
	
	private String agreementId;
	
	private String subEmail;
	
	public SubscriptionDTO() {}
	
	public SubscriptionDTO(Long id, String merchantUsername, String type, double priceAmount, String planId,
			boolean active, String agreementId, String subEmail) {
		super();
		this.id = id;
		this.merchantUsername = merchantUsername;
		this.type = type;
		this.priceAmount = priceAmount;
		this.planId = planId;
		this.active = active;
		this.agreementId = agreementId;
		this.subEmail = subEmail;
	}

	public SubscriptionDTO(String merchantUsername, String type, double priceAmount, String planId,
			boolean active, String agreementId, String subEmail) {
		super();
		this.id = id;
		this.merchantUsername = merchantUsername;
		this.type = type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPriceAmount() {
		return priceAmount;
	}

	public void setPriceAmount(double priceAmount) {
		this.priceAmount = priceAmount;
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

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getSubEmail() {
		return subEmail;
	}

	public void setSubEmail(String subEmail) {
		this.subEmail = subEmail;
	}
	
	
}
