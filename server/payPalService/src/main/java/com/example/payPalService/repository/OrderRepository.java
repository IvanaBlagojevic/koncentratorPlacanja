package com.example.payPalService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.payPalService.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Optional<Order> findByPaymentId(String id);
	
	Optional<Order> findById(Long id);

	Order findOneByPaymentId(String oid);
	
}
