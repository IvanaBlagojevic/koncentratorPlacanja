package com.example.bitcoinService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bitcoinService.domain.BitcoinUser;

@Repository
public interface BitcoinUserRepository extends JpaRepository<BitcoinUser, Long> {

	BitcoinUser findOneByUsername(String username);

}
