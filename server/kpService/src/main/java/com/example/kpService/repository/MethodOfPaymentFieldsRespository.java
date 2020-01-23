package com.example.kpService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kpService.domain.MethodOfPaymentFields;



@Repository
public interface MethodOfPaymentFieldsRespository extends JpaRepository<MethodOfPaymentFields, Long>{
	
	

}



