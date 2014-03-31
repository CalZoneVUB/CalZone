package com.vub.exception;

public class CannotActivateUserException extends Exception {
	//Parameterless Constructor
	public CannotActivateUserException() {}

	//Constructor that accepts a message
	public CannotActivateUserException(String message)
	{
		super(message);
	}
}
