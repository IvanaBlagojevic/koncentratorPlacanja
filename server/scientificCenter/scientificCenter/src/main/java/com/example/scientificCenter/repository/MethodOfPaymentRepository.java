package com.example.scientificCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scientificCenter.domain.MethodOfPayment;
import com.example.scientificCenter.domain.ScientificArea;


@Repository
public interface MethodOfPaymentRepository extends JpaRepository<MethodOfPayment, Long> {

}

