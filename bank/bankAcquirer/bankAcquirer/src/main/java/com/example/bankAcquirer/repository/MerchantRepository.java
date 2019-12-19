package com.example.bankAcquirer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankAcquirer.domain.Merchant;


@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

	Merchant findByMerchantId(String merchant_id);

}
