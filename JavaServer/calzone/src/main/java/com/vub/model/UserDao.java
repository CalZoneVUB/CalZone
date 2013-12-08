package com.vub.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.tree.RowMapper;
import org.springframework.stereotype.Component;

import com.vub.db.DbTranslate;

@Component("UserDao")
public class UserDao {
	// Database Access Object
	
	DbTranslate db = new DbTranslate();

	// Check if username is available
	public boolean checkIfUserNameAvailable(String username){
		return db.checkIfUserNameAvailable(username);
	}
	
	// Update User ( only updates password and language ! )
	public void updateUser(User user){
		db.updateUser(user);
	}
	
	// List of all the users in database
	public List<User> getUsers() {
		List<User>  users = db.selectAllUsers();
		return users;
	}
	
	// get User with username back from database
	public User findByUserName(String username) {
		User user = db.selectUserByUsername(username);
		return user;
	}
	
	// get User with email back from database
	public User findByEmail(String email) {
		User user = db.selectUserByEmail(email);
		return user;
	}
	
	// insert NotRegisteredUser in database
	public void insertNotRegisteredUser(User user){
		db.insertNotRegisteredUser(user);
	}
	
	// upgrade NotRegisteredUser to User in database
	public void upgradeNotRegisteredUser(User user){
		db.upgradeNotRegisteredUser(user);
	}
	
}
