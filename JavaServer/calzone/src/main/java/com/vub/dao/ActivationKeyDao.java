package com.vub.dao;

import org.springframework.stereotype.Component;

import com.vub.db.DbTranslate;
import com.vub.model.ActivationKey;

@Component("UserDao")
public class ActivationKeyDao {
	
	
	DbTranslate db = new DbTranslate();
	
	// insert ActivationKey in database
	public void insertActivationKey(ActivationKey activationKey){
		DbTranslate.insertActivationKey(activationKey);
	}
	
	// delete ActivationKey in database
	public void deleteActivationKey(ActivationKey activationKey){
		DbTranslate.deleteActivationKey(activationKey);
	}
	
	// get ActivationKey with keyString back from database
	public ActivationKey findByKeyString(String keyString) {
		ActivationKey activationKey = DbTranslate.selectUserByActivationKey(keyString);
		return activationKey;
	}
		
}
