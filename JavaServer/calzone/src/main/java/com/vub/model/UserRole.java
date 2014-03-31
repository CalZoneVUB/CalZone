package com.vub.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="USER_TYPE")
public class UserRole {
	@Id
	@Column(name="UserRoleID")
	@GeneratedValue
	private int id;
	
	@Column(name="UserRole")
	private String userRole;

	@OneToMany(mappedBy="userRole", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<User> users;

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
	 * (NOTE: You should only use this when making a new user rolee)
	 * Since there is only one entry in the database per role, this means it changes the role of all users when persisted in the database
	 * @param userRole New role for this user
	 */
	public void setUserRole(UserRoleEnum userRole) {
		this.userRole = userRole.toString();
	}
		
	/**
	 * @return Returns a list of all users with this role
	 */
	public List<User> getUsers() {
		return users;
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