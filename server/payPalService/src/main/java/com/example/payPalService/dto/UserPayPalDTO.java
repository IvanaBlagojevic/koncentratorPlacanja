package com.example.payPalService.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.payPalService.domain.UserPayPal;

public class UserPayPalDTO {

	
    private Long id;
	private String username;
	private String clientId;
	private String clientSecret;
	
	public UserPayPalDTO() {}
	
	public UserPayPalDTO(UserPayPal user)
	{
		this.id = user.getId();
		this.username = user.getUsername();
		this.clientId = user.getClientId();
		this.clientSecret = user.getClientSecret();
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
