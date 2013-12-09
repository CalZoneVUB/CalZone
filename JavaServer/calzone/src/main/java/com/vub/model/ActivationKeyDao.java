package com.vub.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.tree.RowMapper;

import org.springframework.stereotype.Component;

import com.vub.db.DbTranslate;

@Component("UserDao")
public class ActivationKeyDao {
	
	
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
