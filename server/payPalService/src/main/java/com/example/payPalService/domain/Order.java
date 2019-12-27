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
	
	//@Column(nullable = false)
    private String paymentId;
	
	//@Column(nullable = false)
    private double amount;
	
	//@Column(nullable = false)
	private OrderStatus status;
	
	//@Column(nullable = false)
	private Date creationDate;
	
	private Date updateDate;
	
	//@Column(nullable = false)
    private String callbackUrl; //podatak o tome kome treba vratiti informaciju nakon placanja, o uspehu/neuspehu
								//kada se razradi pun krug, ovde treba da stoji putanja do naucne centrale
	//@Column(nullable = false)
	private String merchant;
	
	public Order() {}

	public Order(String paymentId, double amount, OrderStatus status, Date creationDate, Date updateDate,
			String callbackUrl, String merchant) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.status = status;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.callbackUrl = callbackUrl;
		this.merchant = merchant;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	
	
}
