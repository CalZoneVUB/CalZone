package com.vub.dao;


import com.vub.db.DbTranslate;
import com.vub.model.PasswordKey;

public class PasswordKeyDao {
	
	DbTranslate db = new DbTranslate();
	String keyType = "password";
	
	public void insert(PasswordKey passwordKey) {
		db.insertKey(passwordKey.getKeyString(), passwordKey.getCreatedOn(), passwordKey.getUserName(), keyType);
	}
	
	public void delete(PasswordKey passwordKey) {
		db.deleteKey(passwordKey.getKeyString());
	}
	
	public PasswordKey findByKeyString(String KeyString) {
		return db.selectPasswordKeyByKeyString(KeyString);
	}
	
	public PasswordKey findByEmail(String email) {
		return db.selectPasswordKeyByEmail(email);
	}
	
}