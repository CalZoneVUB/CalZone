package com.vub.model;


import java.sql.Date;

import org.springframework.beans.factory.annotation.Value;

public class User {
	private String userName;
	private String password;
	private String language;
	private String userTypeName;
	private String lastName;
	private String firstName;
	private String email;
	private Date birthdate;
	
	public User() {
		//setUserName("Calzone");
		//setPassword("monkey123");
		setLanguage("NL");
		setUserTypeName("Extern");
		//setFirstName("Bob");
		//setLastName("Alice");
		//setEmail("Bob@gmail.com");
		setBirthdate(Date.valueOf("2000-01-01"));
	}
	
	public User(String userName) {
		setUserName(userName);
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
		return "User [userName=" + userName
				+ ", password=" + password + ", language=" + language
				+ ", userTypeName=" + userTypeName
				+ ", lastName=" + lastName + ", firstName=" + firstName
				+ ", email=" + email + ", birthdate=" + birthdate + "]";
	}
	

}
