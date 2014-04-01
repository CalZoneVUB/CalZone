package com.vub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vub.model.UserRole;

/**
 * 
 * @author Sam
 *
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	
	@Query(value="SELECT role FROM UserRole role WHERE role.userRole = :role")
	public UserRole findByRole(@Param("role") String role);
}