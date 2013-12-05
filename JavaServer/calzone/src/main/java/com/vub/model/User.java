package com.vub.model;

public class User {
	//private int id;
	private String userName;
	private String name;
	private String lastName;
	
	public User() {
		
	}
	public User(String userName, String name, String lastName) {
		this.userName = userName;
		this.name = name;
		this.lastName = lastName;
	}
	
	/*
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	*/
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", name=" + name
				+ ", lastName=" + lastName + "]";
	}
	
	
}
