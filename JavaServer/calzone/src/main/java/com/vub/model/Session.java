package com.vub.model;

public class Session {
	private String sessionKey;
	private String userName;
	
	
	public Session(User user) {
		SessionIdentifierGenerator sessionGen = new SessionIdentifierGenerator();
		sessionKey = sessionGen.nextSessionId();
		userName = user.getUserName();
	}
	
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
