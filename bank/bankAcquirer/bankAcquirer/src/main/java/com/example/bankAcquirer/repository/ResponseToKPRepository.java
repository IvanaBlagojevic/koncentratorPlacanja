package com.example.bankAcquirer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankAcquirer.domain.Payment;
import com.example.bankAcquirer.domain.ResponseToKP;


@Repository
public interface ResponseToKPRepository extends JpaRepository<ResponseToKP, Long> {


}
