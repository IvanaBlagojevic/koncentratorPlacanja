package com.example.kpService.dto;

public class SubscriptionPlanParamsDTO {

	private Long id;
	
	private String merchantUsername;
	
	private String subPeriod;
	
	private int frequency;
	
	private double subPrice;
	
	private String planId;
	
	public SubscriptionPlanParamsDTO() {}
	
	
	public SubscriptionPlanParamsDTO(Long id, String merchantUsername, String subPeriod, int frequency, double subPrice,
			String planId) {
		super();
		this.id = id;
		this.merchantUsername = merchantUsername;
		this.subPeriod = subPeriod;
		this.frequency = frequency;
		this.subPrice = subPrice;
		this.planId = planId;
	}

	public SubscriptionPlanParamsDTO(String merchantUsername, String subPeriod, int frequency, double subPrice,
			String planId) {
		super();
		this.id = id;
		this.merchantUsername = merchantUsername;
		this.subPeriod = subPeriod;
		this.frequency = frequency;
		this.subPrice = subPrice;
		this.planId = planId;
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

	public String getSubPeriod() {
		return subPeriod;
	}

	public void setSubPeriod(String subPeriod) {
		this.subPeriod = subPeriod;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public double getSubPrice() {
		return subPrice;
	}

	public void setSubPrice(double subPrice) {
		this.subPrice = subPrice;
	}

	public String getPlanId() {
		return planId;
	}


	public void setPlanId(String planId) {
		this.planId = planId;
	}

}
