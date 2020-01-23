package com.example.scientificCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scientificCenter.domain.ScientificArea;
import com.example.scientificCenter.domain.Transaction;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}