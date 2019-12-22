package com.example.bankAcquirer.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String accountAccepter;

	@Column
	private String accountPayer;

	@OneToOne
	private Payment payment;

	@Column
	private Date timestamp;
	
	@Column
	private Double amount;
	

	@Column
	private String status; // success, failed, error

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountAccepter() {
		return accountAccepter;
	}

	public void setAccountAccepter(String accountAccepter) {
		this.accountAccepter = accountAccepter;
	}

	public String getAccountPayer() {
		return accountPayer;
	}

	public void setAccountPayer(String accountPayer) {
		this.accountPayer = accountPayer;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	
}
