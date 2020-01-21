package com.example.kpService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kpService.domain.MerchantSystem;

@Repository
public interface MerchantSystemRepository extends JpaRepository<MerchantSystem, Long> {

	MerchantSystem findBySystemName(String name);
	
}
