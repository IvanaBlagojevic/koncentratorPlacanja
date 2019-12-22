package com.example.bankService.domain;

import java.util.Date;

import javax.persistence.*;

import com.example.bankService.dto.TransactionDTO;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String merchantOrderId;

    @Column(nullable = false)
    private String acquirerOrderId;

    @Column(nullable = false)
    private Date acquirerTimestamp;

    @Column(nullable = false)
    private Long paymentId;

    @Column(nullable = false)
    private StatusOfPayment status;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(TransactionDTO transaction) {
		// TODO Auto-generated constructor stub
		this.merchantOrderId = transaction.getMerchantOrderId();
		this.acquirerOrderId = transaction.getAcquirerOrderId();
		this.acquirerTimestamp = transaction.getAcquirerTimestamp();
		this.paymentId = transaction.getPaymentId();
		this.status = transaction.getStatus();
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public String getAcquirerOrderId() {
		return acquirerOrderId;
	}

	public void setAcquirerOrderId(String acquirerOrderId) {
		this.acquirerOrderId = acquirerOrderId;
	}

	public Date getAcquirerTimestamp() {
		return acquirerTimestamp;
	}

	public void setAcquirerTimestamp(Date acquirerTimestamp) {
		this.acquirerTimestamp = acquirerTimestamp;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public StatusOfPayment getStatus() {
		return status;
	}

	public void setStatus(StatusOfPayment status) {
		this.status = status;
	}
    
    
	
}
