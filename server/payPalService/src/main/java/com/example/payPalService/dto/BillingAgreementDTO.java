package com.example.payPalService.dto;

public class BillingAgreementDTO {


	private String planId;
	
	private String merchantUsername;
	
	public BillingAgreementDTO() {}
	
	

	public BillingAgreementDTO(String planId, String merchantUsername) {
		super();
		this.planId = planId;
		this.merchantUsername = merchantUsername;
	}



	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getMerchantUsername() {
		return merchantUsername;
	}

	public void setMerchantUsername(String merchantUsername) {
		this.merchantUsername = merchantUsername;
	}
	
}
