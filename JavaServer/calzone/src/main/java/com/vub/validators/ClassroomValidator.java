package com.vub.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.vub.model.Room;

public class ClassroomValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Room.class.equals(clazz);
	}

	public void validate(Object obj, Errors errors) {
		Room room = (Room) obj;
		
	}

}
