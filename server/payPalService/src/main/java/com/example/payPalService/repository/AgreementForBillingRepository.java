package com.example.payPalService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.payPalService.domain.AgreementForBilling;

@Repository
public interface AgreementForBillingRepository extends JpaRepository<AgreementForBilling, Long> {

	
}
