package com.example.kpService.dto;

import com.example.kpService.domain.SubscriptionPeriod;

public class SubscriptionSCenterDTO {


	private SubscriptionPeriod type;
	
	private int frequency;
	
	private double price;
	
	private boolean active;
	
	private String userEmail;
	
	private String  journalIssn;
	
	public SubscriptionSCenterDTO() {}
	
	public SubscriptionSCenterDTO(SubscriptionPeriod type, int frequency, double price, boolean active,
			String userEmail, String journalIssn) {
		super();
		this.type = type;
		this.frequency = frequency;
		this.price = price;
		this.active = active;
		this.userEmail = userEmail;
		this.journalIssn = journalIssn;
	}



	public SubscriptionPeriod getType() {
		return type;
	}

	public void setType(SubscriptionPeriod type) {
		this.type = type;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getJournalIssn() {
		return journalIssn;
	}

	public void setJournalIssn(String journalIssn) {
		this.journalIssn = journalIssn;
	}

}
