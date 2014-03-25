package com.vub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.exception.KeyNotFoundException;
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
	public void createKey(Key key) {
		keyRepository.save(key);
	}
	/**
	 * Find a key in the database
	 * @param key The unique keystring which identifies the key
	 * @return Key object which is the resulted key
	 */
	public Key findKey(String key) {
		return keyRepository.findOne(key);
	}
	
	/**
	 * Find a user in the database, given a key string-representation
	 * @param keyString Key-string that is a valid key in the database
	 * @return Returns the user who is associated with the key.
	 * @throws KeyNotFoundException Key could not be found in the database (and as a result, the associated user couldn't either)
	 */
	@Transactional
	public User findUserByKey(String keyString) throws KeyNotFoundException {
		Key key = this.findKey(keyString);
		if(key == null)
			throw new KeyNotFoundException("No key found in the database");
		return key.getUser();
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
		keyRepository.delete(this.findKey(key));
	}
	/**
	 * Generate an activation key based on the provided user.	
	 * @param user User to generate the activation key for
	 * @return Returns the key which has been generated
	 */
	@Transactional
	public Key generateActivationKey(User user) {
		SessionIdentifierGenerator gen = new SessionIdentifierGenerator();
		Key key = new Key();
		key.setKey(gen.nextSessionId());
		key.setKeyPermission(Key.KeyPermissionEnum.Activation);
		key.setUser(user);
		return key;
	}
}
