package com.example.bankAcquirer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankAcquirer.domain.Account;
import com.example.bankAcquirer.domain.Card;


@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

	Card findByPan(String pan);

}