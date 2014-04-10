package com.vub.exception;

public class CourseComponentNotFoundException extends Exception {
	//Parameterless Constructor
	public CourseComponentNotFoundException() {}

	//Constructor that accepts a message
	public CourseComponentNotFoundException(String message)
	{
		super(message);
	}
}
