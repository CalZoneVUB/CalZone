package com.vub.exception;

public class CourseNotFoundException extends Exception {
	//Parameterless Constructor
	public CourseNotFoundException() {}

	//Constructor that accepts a message
	public CourseNotFoundException(String message)
	{
		super(message);
	}
}
