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
	
	@Column(nullable = false)
	private String merchantPassword;
	
	@OneToMany(mappedBy = "merchant")
    protected List<Payment> payments = new ArrayList<Payment>();

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

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	
	

}
