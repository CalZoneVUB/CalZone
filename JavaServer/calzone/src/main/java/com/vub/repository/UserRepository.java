package com.vub.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vub.model.User;

/**
 * 
 * @author Sam
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	/**
	 * Find a user with a given username in the database
	 * @param username Username of the user which needs to be found
	 * @return User which has been found with the given username
	 */
	@Query("SELECT u FROM User u WHERE u.username = :username")
	public User findUserByUsername(String username);
}