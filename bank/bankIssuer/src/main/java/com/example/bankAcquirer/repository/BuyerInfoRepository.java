package com.example.bankAcquirer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankAcquirer.domain.BuyerInfo;
import com.example.bankAcquirer.domain.Merchant;

@Repository
public interface BuyerInfoRepository extends JpaRepository<BuyerInfo, Long> {

}