package com.example.payPalService.dto;

import java.util.Date;

import com.example.payPalService.domain.BillingPlanFrequency;
import com.example.payPalService.domain.BillingPlanType;
import com.example.payPalService.domain.CurrencyEnum;


public class BillingPlanDTO {

	private String merchantUsername;
 	
 	private Long subId;

    private String type;

    private int frequency;

    private double amount;

    private String callbackPlanUrl;

    private String callbackAgreementUrl;
    
    
    public BillingPlanDTO() {
    	
    }
	
	public BillingPlanDTO(String merchantUsername, Long subId, String type, int frequency, double amount,
			String callbackPlanUrl, String callbackAgreementUrl) {
		super();
		this.merchantUsername = merchantUsername;
		this.subId = subId;
		this.type = type;
		this.frequency = frequency;
		this.amount = amount;
		this.callbackPlanUrl = callbackPlanUrl;
		this.callbackAgreementUrl = callbackAgreementUrl;
	}



	public String getMerchantUsername() {
		return merchantUsername;
	}

	public void setMerchantUsername(String merchantUsername) {
		this.merchantUsername = merchantUsername;
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCallbackPlanUrl() {
		return callbackPlanUrl;
	}

	public void setCallbackPlanUrl(String callbackPlanUrl) {
		this.callbackPlanUrl = callbackPlanUrl;
	}

	public String getCallbackAgreementUrl() {
		return callbackAgreementUrl;
	}

	public void setCallbackAgreementUrl(String callbackAgreementUrl) {
		this.callbackAgreementUrl = callbackAgreementUrl;
	}
	
}
