package com.example.payPalService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.payPalService.domain.Order;
import com.example.payPalService.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRep;
	
	public Order saveOrder(Order order) {
		
		return orderRep.save(order);
	}
	
	public Optional<Order> getByPaymentId(String id){
		
		return orderRep.findByPaymentId(id);
	}
	
	public Optional<Order> getById(Long id){
		
		return orderRep.findById(id);
	}
}
