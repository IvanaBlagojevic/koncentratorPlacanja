package com.example.bankAcquirer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankAcquirer.domain.IssuerResponse;
import com.example.bankAcquirer.domain.Merchant;



@Repository
public interface IssuerResponseRepository extends JpaRepository<IssuerResponse, Long> {


}