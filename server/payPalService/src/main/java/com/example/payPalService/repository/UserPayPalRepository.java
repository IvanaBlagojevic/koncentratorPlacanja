package com.example.payPalService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.payPalService.domain.UserPayPal;

public interface UserPayPalRepository extends JpaRepository<UserPayPal, Long> {

	UserPayPal findByUsername(String username);
}
