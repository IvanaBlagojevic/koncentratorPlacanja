package com.example.kpService.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class MethodOfPayment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String path;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "payment_fields",
    	joinColumns = @JoinColumn(name = "method_id", referencedColumnName = "id"),
    	inverseJoinColumns = @JoinColumn(name = "field_id", referencedColumnName = "id"))
    private List<MethodOfPaymentFields>  fields;

	public MethodOfPayment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MethodOfPayment(Long id, String name, String path, List<MethodOfPaymentFields> fields) {
		super();
		this.id = id;
		this.name = name;
		this.path = path;
		this.fields = fields;
	}
	
	public MethodOfPayment(String name, String path, List<MethodOfPaymentFields> fields) {
		super();
		this.id = id;
		this.name = name;
		this.path = path;
		this.fields = fields;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<MethodOfPaymentFields> getFields() {
		return fields;
	}

	public void setFields(List<MethodOfPaymentFields> fields) {
		this.fields = fields;
	}
	
}
