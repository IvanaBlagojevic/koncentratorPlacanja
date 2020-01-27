package com.example.kpService.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class MerchantSystemDTO {

	private Long id;
	
	private String systemName;
	
	private String frontUrl;
	
	private String backUrl;
	
	public MerchantSystemDTO( ) {}
	
	
	
	public MerchantSystemDTO(Long id, String systemName, String frontUrl, String backUrl) {
		super();
		this.id = id;
		this.systemName = systemName;
		this.frontUrl = frontUrl;
		this.backUrl = backUrl;
	}



	public MerchantSystemDTO(String systemName, String frontUrl, String backUrl) {
		super();
		this.systemName = systemName;
		this.frontUrl = frontUrl;
		this.backUrl = backUrl;
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
	
	
}
