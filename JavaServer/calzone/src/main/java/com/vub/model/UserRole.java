package com.vub.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/** 
 * 
 * @author Nicolas
 *
 */

@Entity
@Table(name="USER_TYPE")
public class UserRole {
	@Id
	@Column(name="UserRoleID")
	@GeneratedValue
	private int id;
	
	@Column(name="UserRole")
	private String userRole;

	/**
	 * Enumeration which provides all of the roles present in the system
	 * @author Sam
	 *
	 */
	public static enum UserRoleEnum {
		ROLE_STUDENT, ROLE_ASSISTANT, ROLE_PROFESSOR, ROLE_ADMIN
	}
	
	/**
	 * @return Returns the specific role for this class
	 */
	public UserRoleEnum getUserRole() {
		return UserRoleEnum.valueOf(userRole);
	}
	/**
	 * Set a new role for this instance of UserRole 
	 * @param userRole New role for this user
	 */
	public void setUserRole(UserRoleEnum userRole) {
		this.userRole = userRole.toString();
	}
		
	/**
	 * @return Returns the ID of the role
	 */
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", userRole=" + userRole + "]";
	}
	
	
}