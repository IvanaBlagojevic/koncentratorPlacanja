package com.example.bankAcquirer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankAcquirer.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
