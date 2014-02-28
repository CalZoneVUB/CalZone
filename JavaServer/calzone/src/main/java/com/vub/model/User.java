package com.vub.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Value;

import com.vub.validators.ValidEmail;
import com.vub.validators.ValidUserName;
import com.vub.model.Globals;

public class User {
	@NotBlank(message="Cannot be empty")
	@ValidUserName(message = "Username already exists")
	private String userName;
	@NotBlank(message="Cannot be empty")
	@Size(min=8, max=42, message = "Pick a password between 8-42 characters")
	private String password;
	private String language;
	private String userTypeName;
	@NotBlank(message="Cannot be empty")
	private String lastName;
	@NotBlank(message="Cannot be empty")
	private String firstName;
	@NotBlank(message="Cannot be empty")
	@Email(message = "Not a real email adress")
	@ValidEmail(message = "Email already exist.")
	private String email;
	private Date birthdate;

	public User() {
		// setUserName("Calzone");
		// setPassword("monkey123");
		setLanguage("NL");
		setUserTypeName("ROLE_STUDENT");
		// setFirstName("Bob");
		// setLastName("Alice");
		// setEmail("Bob@gmail.com");
		setBirthdate(new Date(2000, 1, 1));
	}

	public User(String userName) {
		setUserName(userName);
		if (Globals.DEBUG == 1) 
			System.out.println("Created User with: " + userName);
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

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password
				+ ", language=" + language + ", userTypeName=" + userTypeName
				+ ", lastName=" + lastName + ", firstName=" + firstName
				+ ", email=" + email + ", birthdate=" + birthdate + "]";
	}

}
