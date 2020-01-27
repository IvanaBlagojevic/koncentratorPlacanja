package com.example.kpService.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SubscriptionPlan { //ova klasa predstavlja planove koje prodavac nudi na odabir

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String period;
	
	@Column(nullable = false)
	private int frequency;
	
	@Column(nullable = false)
	private double price;
	
	@Column
	private String planId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Merchant merchant;
	
	public SubscriptionPlan() {}
	
	public SubscriptionPlan(String period, int frequency, double price, String planId, Merchant merchant) {
		super();
		this.period = period;
		this.frequency = frequency;
		this.price = price;
		this.planId = planId;
		this.merchant = merchant;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
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

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

}
