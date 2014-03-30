package com.vub.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/** 
 * 
 * @author Nicolas
 *
 */

@Entity
@Table(name="GENERATED_KEY")
public class Key {
	
	@Id
	@GeneratedValue
	@Column(name="keyID")
	private int id;
	
	@Column(name="KeyString")
	private String keyString;
	
	@Column(name="KeyPermission")
	private KeyPermissionEnum keyPermission;
	
	private int userID;
	
	/**
	 * Enumeration which contains every field
	 * @author Sam
	 *
	 */
	public static enum KeyPermissionEnum {
		Activation, PasswordReset
	}
	
	/**
	 * Returns the unique identifier for the key
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return Returns the keystring of this key (which is the actual key)
	 */
	public String getKeyString() {
		return keyString;
	}
	/**
	 * @param keyString Sets the key of this object
	 */
	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}
	/**
	 * @return Returns the permissions of this key (i.e. where it can be used)
	 */
	public KeyPermissionEnum getKeyPermission() {
		return keyPermission;
	}
	/**
	 * Sets in which scenario this key can be used
	 * @param keyPermission
	 */
	public void setKeyPermission(KeyPermissionEnum keyPermission) {
		this.keyPermission = keyPermission;
	}
	/**
	 * @return Returns the ID of the User who can use this key
	 */
	public int getUserID() {
		return userID;
	}
	/**
	 * Sets the only User who can use this key 
	 * @param id The ID of the user
	 */
	public void setUserID(int id) {
		this.userID = id;
	}
}

