package com.example.scientificCentralService.domain;

import javax.persistence.*;

@Entity
public class Test {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	private String merchantEmail;
	    
	@Column(nullable = false)
	private String userEmail;

    @Column(nullable = false)
    private String fleg;
    
    public Test() {
    	
    }

	public Test(Long id, String merchantEmail, String userEmail, String fleg) {
		super();
		this.id = id;
		this.merchantEmail = merchantEmail;
		this.userEmail = userEmail;
		this.fleg = fleg;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMerchantEmail() {
		return merchantEmail;
	}

	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getFleg() {
		return fleg;
	}

	public void setFleg(String fleg) {
		this.fleg = fleg;
	}

	
    
    
}
