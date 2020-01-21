package com.example.kpService.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Enumerated;

@Entity
public class MethodOfPaymentFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private MethodOfPaymentFieldName code;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String type;
	
	public MethodOfPaymentFields() {}

	@Override
	public String toString() {
		return "MethodOfPaymentFields [id=" + id + ", name=" + name + ", type=" + type + "]";
	}

	public MethodOfPaymentFields(Long id, MethodOfPaymentFieldName code, String name, String type) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MethodOfPaymentFieldName getCode() {
		return code;
	}

	public void setCode(MethodOfPaymentFieldName code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
