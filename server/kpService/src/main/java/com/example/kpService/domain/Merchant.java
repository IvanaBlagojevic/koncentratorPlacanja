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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Merchant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	

	@Column(nullable = false)
	private String username;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private MerchantSystem system;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<MethodOfPayment> paymentMethods;
	
	@OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY)
	private List<SubscriptionPlan> subscriptions = new ArrayList<SubscriptionPlan>();
	
	public Merchant() {}

	public Merchant(Long id, String name, String username, MerchantSystem system, List<MethodOfPayment> paymentMethods
			) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.system = system;
		this.paymentMethods = paymentMethods;
//		this.subscriptions = subscriptions;
	}

	public Merchant(String name, String username, MerchantSystem system, List<MethodOfPayment> paymentMethods) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.system = system;
		this.paymentMethods = paymentMethods;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public MerchantSystem getSystem() {
		return system;
	}

	public void setSystem(MerchantSystem system) {
		this.system = system;
	}

	public List<MethodOfPayment> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<MethodOfPayment> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public List<SubscriptionPlan> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<SubscriptionPlan> subscriptions) {
		this.subscriptions = subscriptions;
	}
	

}
