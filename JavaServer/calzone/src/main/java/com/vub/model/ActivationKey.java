package com.vub.model;
import java.sql.Date;

public class ActivationKey {
	private String keyString;
	private Date createdOn;
	private String userName;
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
	@Override
	public String toString() {
		return "ActivationKey [keyString=" + keyString + ", createdOn="
				+ createdOn + ", userName=" + userName + "]";
	}
}
