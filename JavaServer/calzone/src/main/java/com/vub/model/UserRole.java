package com.vub.model;

import java.util.List;

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
@Table(name="USER_TYPE")
public class UserRole {
	@Id
	@Column(name="UserRoleID")
	@GeneratedValue
	private int id;
	
	@Column(name="UserRole")
	private String userRole;
	
	@OneToMany(mappedBy="userRole")
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
	public UserRoleEnum getRole() {
		return UserRoleEnum.valueOf(userRole);
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
}