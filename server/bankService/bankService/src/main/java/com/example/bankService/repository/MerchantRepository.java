package com.example.bankService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankService.domain.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

	Merchant findByMerchantEmail(String merchantEmail);

}
