package com.example.authService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.authService.domain.UserRole;
import com.example.authService.domain.UserRoleName;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	@Query(value="select s from UserRole s where s.name = ?1")
	UserRole findRoleByName(UserRoleName name);
}
