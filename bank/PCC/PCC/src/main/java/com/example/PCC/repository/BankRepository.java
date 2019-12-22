package com.example.PCC.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.PCC.domain.Bank;


@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

	Bank findByBin(String bin);


}
