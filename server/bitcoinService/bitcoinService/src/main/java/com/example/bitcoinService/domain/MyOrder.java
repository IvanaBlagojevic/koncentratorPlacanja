package com.example.bitcoinService.domain;

import java.util.Date;

import javax.persistence.*;

@Table(name="myOrder")
@Entity
public class MyOrder {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String paymentId;

    @Column(nullable = false)
    private String usernameIssn;

    @Column(nullable = false)
    private Date created;

    @Column
    private Date updated;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatusEnum status;
    
    //@Column(nullable = false)
    //private String callbackUrl;
    
    @Column(nullable = false)
    private String randomUniqueID;

    public MyOrder() {}

	public MyOrder (String paymentId, String usernameIssn, Date created, Date updated, double amount, String currency,
			OrderStatusEnum status, String randomUniqueID) {
		this.paymentId = paymentId;
		System.out.println("username order" + usernameIssn);
		this.usernameIssn = usernameIssn;
		this.created = created;
		this.updated = updated;
		this.amount = amount;
		this.currency = currency;
		System.out.println("status order" + status);
		this.status = status;
		this.randomUniqueID = randomUniqueID;
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

	public String getUsernameIssn() {
		return usernameIssn;
	}

	public void setUsernameIssn(String usernameIssn) {
		this.usernameIssn = usernameIssn;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public OrderStatusEnum getStatus() {
		return status;
	}

	public void setStatus(OrderStatusEnum status) {
		this.status = status;
	}

	public String getRandomUniqueID() {
		return randomUniqueID;
	}

	public void setRandomUniqueID(String randomUniqueID) {
		this.randomUniqueID = randomUniqueID;
	}

  
    
}
