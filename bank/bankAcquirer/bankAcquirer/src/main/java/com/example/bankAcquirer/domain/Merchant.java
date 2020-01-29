package com.example.bankAcquirer.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.bankAcquirer.dto.MerchantDTO;


@Entity
public class Merchant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String merchantId;
	
	@Column(nullable = false)
	private String merchantPassword;
	
	@OneToOne
	private Account account;

	public Merchant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Merchant(MerchantDTO merchant) {
		// TODO Auto-generated constructor stub
		this.id = merchant.getId();
		this.merchantId= merchant.getMerchantId();
		this.merchantPassword= merchant.getMerchantPassword();
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	

}
