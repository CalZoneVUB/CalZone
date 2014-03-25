package com.vub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vub.model.User;

/**
 * 
 * @author Sam
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}