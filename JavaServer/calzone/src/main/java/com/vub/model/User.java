package com.vub.model;

//import java.util.Date;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.vub.dao.UserDao;
import com.vub.model.UserType.UserTypeEnum;
import com.vub.validators.ValidEmail;
import com.vub.validators.ValidUserName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * 
 * @author Tim + Nicolas
 * 
 */
@Entity
@Table(name="Users")
public class User {
	@Id
	@Column(name="UserID")
	@GeneratedValue
	private int id;
	@NotBlank(message = "Cannot be empty")
	@ValidUserName(message = "Username already exists")
	@Column(name="UserName")
	private String userName;
	@NotBlank(message = "Cannot be empty")
	@Size(min = 8, message = "Pick a password greater then 8 characters")
	@Column(name="Password")
	private String password;
	@Column(name="Language")
	private String language;
	// USERTYPE ???????
	@ManyToOne
	@JoinColumn(name="UserTypeID")
	private UserType userType;
	@OneToOne
	@JoinColumn(name="PersonID")
	private Person person;
	@Column(name="Enabled")
	private boolean Enabled;
	
	public User() { // TODO constructor weglaten ?! en dus in de code de lege constructor gebruiken en setten.
		this.language="NL";
		this.userType = new UserType(UserTypeEnum.ROLE_STUDENT);
		Date date = new Date(2000, 1, 1);
		this.person.setBirthdate(date);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean isEnabled() {
		return Enabled;
	}

	public void setEnabled(boolean enabled) {
		Enabled = enabled;
	}


	@Override
	public boolean equals(Object obj) {
		return ((User) obj).getUserName().equals(this.getUserName());
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * this.getUserName().hashCode();
		return hash;
	}
	
}
