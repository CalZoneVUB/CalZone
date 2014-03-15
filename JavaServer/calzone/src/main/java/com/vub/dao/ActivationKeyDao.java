package com.vub.dao;

import org.springframework.stereotype.Component;

import com.vub.db.DbTranslate;
import com.vub.model.ActivationKey;
import com.vub.model.PasswordKey;

@Component("ActivationKeyDao")
public class ActivationKeyDao { // TODO: CHANGE because now 1 table Keystrings
	
	
	DbTranslate db = new DbTranslate();
	String keyType = "Activation";
	
	// insert ActivationKey in database
	public void insertActivationKey(ActivationKey activationKey){
		db.insertKey(activationKey.getKeyString(), activationKey.getCreatedOn(), activationKey.getUserName(), keyType);
	}
	
	// delete ActivationKey in database
	public void deleteActivationKey(ActivationKey activationKey){
		db.deleteKey(activationKey.getKeyString());
	}
	
	// get ActivationKey with keyString back from database
	public ActivationKey findByKeyString(String keyString) {
		return db.selectActivationKeyByKeyString(keyString);
	}
	
	public ActivationKey findByEmail(String email) {
		return db.selectActivationKeyByEmail(email);
	}
		
}
