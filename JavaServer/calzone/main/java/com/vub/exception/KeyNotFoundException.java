package com.vub.exception;

public class KeyNotFoundException extends Exception {
	//Parameterless Constructor
	public KeyNotFoundException() {}

	//Constructor that accepts a message
	public KeyNotFoundException(String message)
	{
		super(message);
	}
}
