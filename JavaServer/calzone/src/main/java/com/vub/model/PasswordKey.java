package com.vub.model;
import java.sql.Date;

public class PasswordKey {
	private String keyString;
	private Date createdOn;
	private String userName;
	
	@Override
	public String toString() {
		return "PasswordKey [keyString=" + keyString + ", createdOn="
				+ createdOn + ", userName=" + userName + "]";
	}

	public String getKeyString() {
		return keyString;
	}

	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public PasswordKey(String userName) {
		Date date = new Date(System.currentTimeMillis());
		SessionIdentifierGenerator gen = new SessionIdentifierGenerator();
		setKeyString(gen.nextSessionId(128));
		setUserName(userName);
		setCreatedOn(date);
	}
	
	public PasswordKey(String userName, Date createdOn, String keyString) {
		this.setUserName(userName);
		this.setCreatedOn(createdOn);
		this.setKeyString(keyString);
	}

	public PasswordKey() {
		
	}
	
}
