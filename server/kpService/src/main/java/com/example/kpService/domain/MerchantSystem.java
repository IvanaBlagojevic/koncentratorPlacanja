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
	
	@Column(nullable = false)
	private String frontUrl;
	
	@Column(nullable = false)
	private String backUrl;
	
	@OneToMany(mappedBy="system",fetch = FetchType.LAZY)
	private List<Merchant> merchants = new ArrayList<Merchant>();
	
	//ovde se moze dodati upload sertifikata
	
	public MerchantSystem() {}

	public MerchantSystem(Long id, String systemName, String frontUrl, String backUrl, List<Merchant> merchants) {
		super();
		this.id = id;
		this.systemName = systemName;
		this.frontUrl = frontUrl;
		this.backUrl = backUrl;
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

	public String getFrontUrl() {
		return frontUrl;
	}

	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public List<Merchant> getMerchants() {
		return merchants;
	}

	public void setMerchants(List<Merchant> merchants) {
		this.merchants = merchants;
	}
	
}
