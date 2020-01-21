package com.example.kpService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kpService.domain.Merchant;


@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

	Merchant findOneByUsername(String username);
}
