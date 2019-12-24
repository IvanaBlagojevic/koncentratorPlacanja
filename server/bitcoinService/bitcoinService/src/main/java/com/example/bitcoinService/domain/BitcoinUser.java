package com.example.bitcoinService.domain;

import javax.persistence.*;

@Entity
public class BitcoinUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	//sifrovati
	@Column(nullable = false, unique = true)
	private String token;
	
	public BitcoinUser() {}

	public BitcoinUser(String username, String token) {
		super();
		this.username = username;
		this.token = token;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
