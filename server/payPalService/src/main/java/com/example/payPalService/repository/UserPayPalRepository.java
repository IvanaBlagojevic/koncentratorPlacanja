package com.example.payPalService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.payPalService.domain.UserPayPal;

public interface UserPayPalRepository extends JpaRepository<UserPayPal, Long> {

	Optional<UserPayPal> findByUsername(String username);
}
