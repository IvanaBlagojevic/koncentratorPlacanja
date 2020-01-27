package com.example.scientificCenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.scientificCenter.domain.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	 Subscription findByJournalIdAndUserId(Long jId, Long uId);
	 
	 List<Subscription> findAllByJournalId(Long id);
	 
}
