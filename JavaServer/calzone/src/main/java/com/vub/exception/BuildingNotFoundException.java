package com.vub.exception;

public class BuildingNotFoundException extends Exception {
	//Parameterless Constructor
	public BuildingNotFoundException() {}

	//Constructor that accepts a message
	public BuildingNotFoundException(String message)
	{
		super(message);
	}
}
