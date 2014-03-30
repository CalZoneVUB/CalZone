package com.vub.exception;

public class UserNotFoundException extends Exception {
	//Parameterless Constructor
	public UserNotFoundException() {}

	//Constructor that accepts a message
	public UserNotFoundException(String message)
	{
		super(message);
	}
}
