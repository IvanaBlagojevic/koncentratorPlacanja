package com.example.bankService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankService.domain.Merchant;
import com.example.bankService.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
