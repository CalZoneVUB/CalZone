package com.vub.exception;

public class FloorNotFoundException extends Exception {
	//Parameterless Constructor
	public FloorNotFoundException() {}

	//Constructor that accepts a message
	public FloorNotFoundException(String message)
	{
		super(message);
	}
}
