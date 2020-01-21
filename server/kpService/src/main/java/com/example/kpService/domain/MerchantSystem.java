package com.example.kpService.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MerchantSystem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String systemName;
	
	@OneToMany(mappedBy="system",fetch = FetchType.LAZY)
	private List<Merchant> merchants = new ArrayList<Merchant>();
	
	//ovde se moze dodati upload sertifikata
	
	public MerchantSystem() {}

	public MerchantSystem(Long id, String systemName, List<Merchant> merchants) {
		super();
		this.id = id;
		this.systemName = systemName;
		this.merchants = merchants;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public List<Merchant> getMerchants() {
		return merchants;
	}

	public void setMerchants(List<Merchant> merchants) {
		this.merchants = merchants;
	}
	
}
