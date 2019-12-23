package com.example.payPalService.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class UserPayPal implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String username;
	
	//@Convert(converter = CipherConverter.class)
    @Column(nullable = false)
    private String clientId;

	//@Convert(converter = CipherConverter.class)
    @Column(nullable = false)
    private String clientSecret;
    
    public UserPayPal() {}

	public UserPayPal(Long id, String username, String clientId, String clientSecret) {
		super();
		this.id = id;
		this.username = username;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
    
    
}
