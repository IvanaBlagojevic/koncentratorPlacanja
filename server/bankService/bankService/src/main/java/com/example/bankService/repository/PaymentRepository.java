package com.example.bankService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankService.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>  {

}
