package com.example.payPalService.domain;

import javax.persistence.Entity;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "order_table")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String paymentId;
	
	@Column(nullable = false)
    private String username;
	
	@Column(nullable = false)
    private double amount;
	
	@Column(nullable = false)
	private PaymentStatus status;
	
	@Column(nullable = false)
	private OrderState state;
	
	@Column(nullable = false)
	private Date creationDate;
	
	private Date completeDate;
	
	@Column(nullable = false)
    private String callbackUrl; //podatak o tome kome treba vratiti informaciju nakon placanja, o uspehu/neuspehu
								//kada se razradi pun krug, ovde treba da stoji putanja do prodavca
	
	public Order() {}
	
	public Order(String paymentId, String username, double amount, PaymentStatus status,
										OrderState state, Date creationDate, Date completeDate, String callbackUrl) {
									super();
									this.paymentId = paymentId;
									this.username = username;
									this.amount = amount;
									this.status = status;
									this.state = state;
									this.creationDate = creationDate;
									this.completeDate = completeDate;
									this.callbackUrl = callbackUrl;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}


	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}
	
}
