package com.vub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** 
 * 
 * @author Nicolas
 *
 */

@Entity
@Table(name="KeyStrings")
public class KeyString {
	// TODO toch een KeyStringID geven?! 
	@Column(name="UserTypeName")
	private String userTypeName;
	@Column(name="Permission")
	private int permission;
	
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	public int getId() {
		return id;
	}
	
}


/*
public enum UserType {
	ROLE_STUDENT, ROLE_ASSISTANT, ROLE_PROFESSOR, ROLE_ADMIN
}
*/

