package com.vub.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.exception.CannotActivateUserException;
import com.vub.exception.UserNotFoundException;
import com.vub.model.Key;
import com.vub.model.Person;
import com.vub.model.User;
import com.vub.model.UserRole;
import com.vub.repository.UserRepository;
import com.vub.repository.UserRoleRepository;

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
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	/**
	 * Create (persist) a user in the database and returns the user ( with updated ID, ... ).
	 * @param user The user one wishes to persist
	 */
	@Transactional
	public User createUser(User user) {
		return userRepository.save(user);
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
	 * @throws CannotActivateUserException Throws exception when the key is not of the activation type (and thus cannot be used)
	 */
	public void activateUser(User user, Key key) throws CannotActivateUserException {
		// First, check if the key is of the right permission
		if(key.getKeyPermission() != Key.KeyPermissionEnum.Activation)
			throw new CannotActivateUserException("Provided key is not of type " + Key.KeyPermissionEnum.Activation.toString() + " but instead of type " + key.getKeyPermission().toString());
		else
			user.setEnabled(true);
	}
	
	/**
	 * Finds the user with a given ID
	 * @param id ID of the user you want to find
	 * @return Returns the User with the given ID
	 * @throws UserNotFoundException When the user with the given ID cannot be found
	 */
	@Transactional
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
	@Transactional
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
	@Transactional
	public User findUserByEmail(String email) throws UserNotFoundException {
		User u = userRepository.findUserByEmail(email);
		if(u == null)
			throw new UserNotFoundException("Could not find user with e-mail address " + email);
		else return u;
	}
	
	/**
	 * Assign a new role to a specified user. Creates a new UserRole in database.
	 * Saves the role to the database, but not the user (because you might want to delay saving the user until later, 
	 * to prevent having to save the same user multiple times)
	 * @param user User to assign a new role to
	 * @param role New role to assign to the user.
	 */
	@Transactional
	public void assignUserRole(User user, UserRole.UserRoleEnum role) {
		UserRole userRole = new UserRole();
		userRole.setUserRole(role);
		user.setUserRole(userRoleRepository.save(userRole));
	}
	
	@Transactional
	public Set<User> getAllUsers() {
		Set<User> result = new HashSet<User>();
		result.addAll(userRepository.findAll());
		return result;
	}
}
