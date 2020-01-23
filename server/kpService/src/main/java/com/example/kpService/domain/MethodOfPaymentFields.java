package com.example.kpService.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.kpService.dto.MethodOfPaymentFieldsDTO;

import javax.persistence.Enumerated;

@Entity
public class MethodOfPaymentFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String code;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String type;
	
	public MethodOfPaymentFields() {}

	@Override
	public String toString() {
		return "MethodOfPaymentFields [id=" + id + ", name=" + name + ", type=" + type + "]";
	}

	public MethodOfPaymentFields(Long id, String code, String name, String type) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.type = type;
	}

	public MethodOfPaymentFields(MethodOfPaymentFieldsDTO methodOfPaymentFieldsDTO) {
		// TODO Auto-generated constructor stub
		//this.id = methodOfPaymentFieldsDTO.getId();
		this.code = methodOfPaymentFieldsDTO.getCode();
		this.name = methodOfPaymentFieldsDTO.getName();
		this.type = methodOfPaymentFieldsDTO.getType();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
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
