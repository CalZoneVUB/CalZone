package com.vub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.exception.KeyNotFoundException;
import com.vub.model.Key;
import com.vub.model.SessionIdentifierGenerator;
import com.vub.model.User;
import com.vub.repository.KeyRepository;

@Service("keyService")
public class KeyService {
	@Autowired
	KeyRepository keyRepository;
	
	@Transactional
	public void createKey(Key key) {
		keyRepository.save(key);
	}
	
	public Key findKey(String key) {
		return keyRepository.findOne(key);
	}
	
	@Transactional
	public User findUserByKey(String keyString) throws KeyNotFoundException {
		Key key = keyRepository.findOne(keyString);
		if(key == null)
			throw new KeyNotFoundException("No key found in the database");
		return key.getUser();
	}
	
	@Transactional
	public void deleteKey(Key key) {
		keyRepository.delete(key);
	}
	
	@Transactional
	public void deleteKey(String key) {
		keyRepository.delete(this.findKey(key));
	}
	
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
