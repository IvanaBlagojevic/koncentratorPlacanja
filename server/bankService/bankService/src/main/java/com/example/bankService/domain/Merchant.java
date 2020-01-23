package com.example.bankService.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.example.bankService.dto.MerchantDTO;

@Entity
public class Merchant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String merchantId;
	
	@Column//(nullable = false) //sa sada
	private String merchantIssn;
	
	@Column(nullable = false)
	private String merchantPassword;
	

	public Merchant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Merchant(MerchantDTO merchant) {
		// TODO Auto-generated constructor stub
		this.id = merchant.getId();
		this.merchantId= merchant.getMerchantId();
		this.merchantPassword= merchant.getMerchantPassword();
		this.merchantIssn = merchant.getMerchantIssn();
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

	public String getMerchantIssn() {
		return merchantIssn;
	}

	public void setMerchantIssn(String merchantIssn) {
		this.merchantIssn = merchantIssn;
	}


}
