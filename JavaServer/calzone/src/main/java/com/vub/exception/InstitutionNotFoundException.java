package com.vub.exception;

public class InstitutionNotFoundException extends Exception {
	//Parameterless Constructor
	public InstitutionNotFoundException() {}

	//Constructor that accepts a message
	public InstitutionNotFoundException(String message)
	{
		super(message);
	}
}
