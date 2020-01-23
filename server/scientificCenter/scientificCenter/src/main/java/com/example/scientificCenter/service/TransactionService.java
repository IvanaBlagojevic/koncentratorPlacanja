package com.example.scientificCenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scientificCenter.domain.Transaction;
import com.example.scientificCenter.domain.User;
import com.example.scientificCenter.repository.TransactionRepository;
import com.example.scientificCenter.repository.UserRepository;



@Service
public class TransactionService {
	
	@Autowired
    private TransactionRepository repository;
	
	public List<Transaction> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
	public Transaction save(Transaction user) {
		return repository.save(user);
	}
	
}