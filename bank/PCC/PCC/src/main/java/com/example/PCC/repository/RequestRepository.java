package com.example.PCC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PCC.domain.Bank;
import com.example.PCC.domain.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {


}
