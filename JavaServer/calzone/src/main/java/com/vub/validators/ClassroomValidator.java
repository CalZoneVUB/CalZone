package com.vub.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

import com.vub.model.Room;

public class ClassroomValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Room.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Room room = (Room) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "building", "classrooms.buildingEmpty.text");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "floor", "classrooms.floorEmpty.text");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "classrooms.nameEmpty.text");
	}

}
