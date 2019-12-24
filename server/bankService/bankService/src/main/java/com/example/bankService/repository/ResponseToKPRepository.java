package com.example.bankService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankService.domain.ResponseToKP;
 

@Repository
public interface ResponseToKPRepository extends JpaRepository<ResponseToKP, Long> {


}