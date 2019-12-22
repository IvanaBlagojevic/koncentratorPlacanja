package com.example.bankAcquirer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankAcquirer.domain.Account;
import com.example.bankAcquirer.domain.BuyerInfo;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByAccountNumber(String pan);

}