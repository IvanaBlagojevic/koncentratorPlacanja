package com.example.payPalService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.payPalService.domain.PlanForBilling;

@Repository
public interface PlanForBillingRepository extends JpaRepository<PlanForBilling, Long> {

}
