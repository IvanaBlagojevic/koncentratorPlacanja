package com.example.bankService.dto;

import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

public class MerchantDTO {
	
	private Long id;
	
	@NotNull
	@Size(min=2, max=20)
	private String merchantId;
	
	@NotNull
	@Size(min=4, max=20)
	private String merchantPassword;
	
	@NotNull
	@Size(min=4, max=20)
	private String merchantEmail;

	public MerchantDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}

	public String getMerchantEmail() {
		return merchantEmail;
	}

	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}

	
}

