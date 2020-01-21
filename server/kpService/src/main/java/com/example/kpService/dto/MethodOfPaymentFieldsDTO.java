package com.example.kpService.dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.kpService.domain.MethodOfPaymentFieldName;

public class MethodOfPaymentFieldsDTO {

	
	private Long id;
	
	private MethodOfPaymentFieldName code;
	
	private String name;
	
	private String type;
	
	private String value;
	
	public MethodOfPaymentFieldsDTO() {}
	
	

	public MethodOfPaymentFieldsDTO(Long id, MethodOfPaymentFieldName code, String name, String type, String value) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.type = type;
		this.value = value;
	}

	public MethodOfPaymentFieldsDTO(MethodOfPaymentFieldName code, String name, String type, String value) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.type = type;
		this.value = value;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
