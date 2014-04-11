package com.vub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.exception.KeyNotFoundException;
import com.vub.exception.UserNotFoundException;
import com.vub.model.Key;
import com.vub.model.SessionIdentifierGenerator;
import com.vub.model.User;
import com.vub.repository.KeyRepository;

/**
 * This class provides general functionality regarding keys. 
 * This includes CRUD operations, generating keys, etc.
 * 
 * @author Sam
 *
 */
@Service("keyService")
public class KeyService {
	@Autowired
	KeyRepository keyRepository;
	
	/**
	 * Create (persist) a key in the database
	 * @param key
	 */
	@Transactional
	public Key createKey(Key key) {
		return keyRepository.save(key);
	}
	/**
	 * Find a key in the database
	 * @param key The unique keystring which identifies the key
	 * @return Key object which is the resulted key
	 * @throws KeyNotFoundException When the key could not be found in the database
	 */
	@Transactional
	public Key findKey(String key) throws KeyNotFoundException {
		Key k = keyRepository.findKeyByKeyString(key);
		if(k == null)
			throw new KeyNotFoundException("Could not find key " + key + " in database");
		return keyRepository.findKeyByKeyString(key);
	}
	/**
	 * Returns a list of all Key objects currently assigned to a certain user
	 * @param user User to find the keys for
	 * @return Returns a list of Keys
	 */
	@Transactional
	public List<Key> findKeysAssignedToUser(User user) {
		return keyRepository.findKeysAssignedToUser(user.getId());
	}
	/**
	 * Find a user in the database, given a key string-representation
	 * @param keyString Key-string that is a valid key in the database
	 * @return Returns the user who is associated with the key.
	 * @throws KeyNotFoundException Key could not be found in the database (and as a result, the associated user couldn't either)
	 * @throws UserNotFoundException When the User attached to the Key cannot be found
	 */
	@Transactional
	public User findUserByKey(String keyString) throws KeyNotFoundException, UserNotFoundException {
		Key key = this.findKey(keyString);
		System.out.println("##### IS KEY NULL? " + (key == null));
		if(key == null)
			throw new KeyNotFoundException("No key found in the database");
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		User u;
		try {
			u = userService.findUserByID(key.getUserID());
		} catch (UserNotFoundException ex) {
			throw ex;
		} finally {
			context.close();
		}
		return u;
	}
	/**
	 * Delete a key from the database
	 * @param key Key which needs deleting
	 */
	@Transactional
	public void deleteKey(Key key) {
		keyRepository.delete(key);
	}
	
	/**
	 * Delete a key-string from the database.
	 * The associated key object will first be looked up in the database before deleting.
	 * @param key Keystring to be deleted from the database
	 */
	@Transactional
	public void deleteKey(String key) {
		try {
			keyRepository.delete(this.findKey(key));
		} catch (KeyNotFoundException e) {
			return;
		}
	}
	
	/**
	 * Generate an activation key based on the provided user (and save the key to the database immediately)
	 * @param user User to generate the activation key for
	 * @return The key which has been saved to the database
	 */
	@Transactional
	public Key generateActivationKey(User user) {
		Key key = new Key();
		key.setKeyString(this.generateKey(64));
		key.setKeyPermission(Key.KeyPermissionEnum.Activation);
		key.setUserID(user.getId());
		return this.createKey(key);
	}
	
	/**
	 * Gernerate a passwordforgot key and immediately save it to the database
	 * @param user User to generate the key for
	 * @Return The key which has been saved to the database
	 */
	@Transactional
	public Key generatePasswordForgotKey(User user) {
		Key key = new Key();
		key.setKeyString(this.generateKey(64));
		key.setKeyPermission(Key.KeyPermissionEnum.PasswordReset);
		key.setUserID(user.getId());
		return this.createKey(key);
	}
	
	/** 
	 * Procedure to generate a new of a given length
	 * @param length Length of the key
	 * @return Returns a key in string format
	 */
	private String generateKey(int length) {
		SessionIdentifierGenerator gen = new SessionIdentifierGenerator();
		return gen.nextSessionId(128);
	}
}
