package com.vub.dao;

import org.springframework.stereotype.Component;

import com.vub.db.DbTranslate;
import com.vub.model.ActivationKey;

@Component("UserDao")
public class ActivationKeyDao { // TODO: CHANGE because now 1 table Keystrings
	
	
	DbTranslate db = new DbTranslate();
	
	// insert ActivationKey in database
	public void insertActivationKey(ActivationKey activationKey){
		db.insertActivationKey(activationKey);
	}
	
	// delete ActivationKey in database
	public void deleteActivationKey(ActivationKey activationKey){
		db.deleteActivationKey(activationKey);
	}
	
	// get ActivationKey with keyString back from database
	public ActivationKey findByKeyString(String keyString) {
		ActivationKey activationKey = db.selectUserByActivationKey(keyString);
		return activationKey;
	}
		
}
