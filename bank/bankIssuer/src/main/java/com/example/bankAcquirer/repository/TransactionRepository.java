package com.example.bankAcquirer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankAcquirer.domain.Transaction;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
