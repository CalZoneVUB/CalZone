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
	
	/**
	 * Find a user with a given e-mail address in the database
	 * @param email The e-mail address that belongs to a user
	 * @return Returns the User with the specified e-mail address
	 */
	// Select the user from User U (inner joined on the Person object in User, where the person's email field equals the e-mail from the query)
	@Query("SELECT u FROM User u INNER JOIN u.person p WHERE p.email = :email")
	public User findUserByEmail(String email);
}