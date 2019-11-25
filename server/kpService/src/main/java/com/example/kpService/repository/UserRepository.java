package com.example.kpService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kpService.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByName(String name);
}
