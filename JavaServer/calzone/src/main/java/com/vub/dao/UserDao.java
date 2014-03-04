package com.vub.dao;

//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.List;

//import javax.swing.tree.RowMapper;


import org.springframework.stereotype.Component;

import com.vub.db.DbTranslate;
import com.vub.model.User;

@Component("UserDao")
public class UserDao {
	// Database Access Object
	
	DbTranslate db = new DbTranslate();

	// Check if username is available
	public boolean checkIfUserNameAvailable(String username){
		return DbTranslate.checkIfUserNameAvailable(username);
	}
	
	// Check if email is available
	public boolean checkIfEmailAvailable(String email){
		return DbTranslate.checkIfEmailAvailable(email);
	}
	
	// Update User ( only updates password and language ! )
	public void updateUser(User user){
		DbTranslate.updateUser(user);
	}
	
	// List of all the users in database
	public List<User> getUsers() {
		List<User>  users = DbTranslate.selectAllUsers();
		return users;
	}
	
	// get User with username back from database
	public User findByUserName(String username) {
		User user = DbTranslate.selectUserByUsername(username);
		return user;
	}
	
	// get User with email back from database
	public User findByEmail(String email) {
		User user = DbTranslate.selectUserByEmail(email);
		return user;
	}
	
	// insert NotRegisteredUser in database
	 public void insertNotEnabledUser(User user){
	 	DbTranslate.insertNotEnabledUser(user);
	 }
	  
	 // upgrade NotRegisteredUser to User in database
	 public void upgradeNotEnabledUser(User user){
	 	DbTranslate.upgradeNotEnabledUser(user);
	 }
	
}
