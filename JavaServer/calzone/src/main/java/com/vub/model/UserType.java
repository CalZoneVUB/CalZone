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
@Table(name="UserTypes")
public class UserType {
	
	public static enum UserTypeEnum {
		ROLE_STUDENT, ROLE_ASSISTANT, ROLE_PROFESSOR, ROLE_ADMIN
	}
	
	@Id
	@Column(name="UserTypeID")
	@GeneratedValue
	private int id;
	@Column(name="UserTypeName")
	private UserTypeEnum userTypeName;
	@Column(name="Permission")
	private int permission;
	
	public UserType() {
		
	}
	
	public UserType(UserTypeEnum userTypeName) {
		this.userTypeName = userTypeName;
	}
	
	public String getUserTypeName() {
		return userTypeName.toString();
	}
	public void setUserTypeName(UserTypeEnum userTypeName) {
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

