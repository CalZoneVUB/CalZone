package com.vub.model;

import org.springframework.stereotype.Controller;

import com.vub.db.DbTranslate;

@Controller("SessionDao")
public class SessionDao {
	// Database Access Object
	
	DbTranslate db = new DbTranslate();

	// get User with username back from database
	public Session findBySessionKey(String sessionKey) {
		Session session = db.selectSessionBySessionKey(sessionKey);
		return session;
	}
	
	// delete Session in database
	public void deleteSession(Session session){
	 	db.deleteSession(session);
	}
	
	// delete all Sessions from User in database
	public void deleteAllSessions(Session session){
	 	db.deleteAllSessions(session);
	}
	
	// insert Session in database
	 public void insertSession(Session session){
	 	db.insertSession(session);
	 }
	
}
