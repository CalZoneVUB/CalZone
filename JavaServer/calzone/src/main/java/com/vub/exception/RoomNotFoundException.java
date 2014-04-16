package com.vub.exception;

public class RoomNotFoundException extends Exception {
	//Parameterless Constructor
	public RoomNotFoundException() {}

	//Constructor that accepts a message
	public RoomNotFoundException(String message)
	{
		super(message);
	}
}
