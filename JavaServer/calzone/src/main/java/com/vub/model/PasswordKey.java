package com.vub.model;
import java.sql.Date;

public class PasswordKey {
	private String keyString;
	private Date createdOn;
	private String identifier;
	
	@Override
	public String toString() {
		return "PasswordKey [keyString=" + keyString + ", createdOn="
				+ createdOn + ", identifier=" + identifier + "]";
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

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public PasswordKey(String identiefier) {
		Date date = new Date(System.currentTimeMillis());
		SessionIdentifierGenerator gen = new SessionIdentifierGenerator();
		setKeyString(gen.nextSessionId(128));
		setIdentifier(identiefier);
		setCreatedOn(date);
	}
	
}
