package com.example.authService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.authService.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByEmail(String email);
	
}
