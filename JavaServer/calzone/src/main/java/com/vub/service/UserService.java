package com.vub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.vub.exception.UserNotFoundException;
import com.vub.model.User;
import com.vub.repository.UserRepository;

/**
 * This class provides services regarding users. These include CRUD operations, but also hashing the password, activating, etc.
 * 
 * @author Sam
 *
 */
@Service("userService")
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	/**
	 * Create (persist) a user in the database.
	 * @param user The user one wishes to persist
	 */
	@Transactional
	public void createUser(User user) {
		userRepository.save(user);
	}
	/**
	 * Update a user object in the database
	 * @param user The user which needs updating
	 */
	@Transactional
	public void updateUser(User user) {
		userRepository.save(user);
	}
	
	/**
	 * Delete a user from the database
	 * @param user
	 */
	@Transactional
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	/**
	 * Hash the password field in the User object
	 * Does not save the changes to the database.
	 * @param user User who needs his password hashed
	 */
	public void hashPassword(User user) {
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
		user.setPassword(encoder.encodePassword(user.getPassword(), null));
	}
	/**
	 * Change the state of the user to activated.
	 * Does not save the changes to the database.
	 * @param user User one wishes to activate
	 */
	public void activateUser(User user) {
		user.setEnabled(true);
	}
	
	/**
	 * Finds the user with a given ID
	 * @param id ID of the user you want to find
	 * @return Returns the User with the given ID
	 * @throws UserNotFoundException When the user with the given ID cannot be found
	 */
	public User findUserByID(int id) throws UserNotFoundException {
		User u = userRepository.findOne(id);
		if(u == null)
			throw new UserNotFoundException("could not find user with id " + id);
		else
			return u;
	}
	/**
	 * Find a user with a given username
	 * @param username Username of the user which needs to be found
	 * @return Returns the user with the associated username
	 * @throws UserNotFoundException When the user with the given username cannot be found
	 */
	public User findUserByUsername(String username) throws UserNotFoundException {
		User u = userRepository.findUserByUsername(username);
		if(u == null)
			throw new UserNotFoundException("could not find user with username " + username);
		return u;
	}
	/**
	 * Find a user with a given e-mail address	
	 * @param email The e-mail address which belongs to the user you want to find
	 * @return Returns the user
	 * @throws UserNotFoundException When the user with the given e-mail cannot be found
	 */
	public User findUserByEmail(String email) throws UserNotFoundException {
		User u = userRepository.findUserByEmail(email);
		if(u == null)
			throw new UserNotFoundException("Could not find user with e-mail address " + email);
		else return u;
	}
}
