package com.example.kpService.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.kpService.domain.MethodOfPayment;

public class MethodOfPaymentDTO {

    private Long id;

    private String name;

    private String url;

	public MethodOfPaymentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MethodOfPaymentDTO(Long id, String name, String url) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MethodOfPayment convertToDomain() {
		
		MethodOfPayment method = new MethodOfPayment();
		method.setName(this.name);
		method.setUrl(this.url);
		
		return method;
	}

	
}
