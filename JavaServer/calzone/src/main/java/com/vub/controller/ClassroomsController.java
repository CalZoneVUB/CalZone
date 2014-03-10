package com.vub.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.dao.RoomDao;
import com.vub.model.Globals;
import com.vub.model.Room;
import com.vub.validators.ClassroomValidator;

@Controller 
public class ClassroomsController {
	
	@RequestMapping(value = "/classrooms", method = RequestMethod.GET)
	public String initialize(Model model, HttpServletRequest request) {
		ArrayList<Room> classroomArrayList = new ArrayList<Room>();
		
		// TODO - REMOVE		
		// Populate a classroom, because the database does not contain any classrooms yet
		Room r1 = new Room();
		r1.setBuilding("F");
		r1.setFloor(4);
		r1.setName("110");
		r1.setProjectorEquipped(false);
		r1.setSmartBoardEquipped(false);
		r1.setRecorderEquipped(false);
		r1.setCapacity(20);
		classroomArrayList.add(r1);
		RoomDao roomDao = new RoomDao();
		ArrayList<Room> arrayListRooms = roomDao.getRooms();
		
		System.out.println("arrayListRooms: " + arrayListRooms);
		
		model.addAttribute("room", new Room());
		model.addAttribute("classroomArrayList", arrayListRooms);
		return "Classrooms"; 
	}
	
	@RequestMapping(value = "/classrooms" , method = RequestMethod.POST)
	public String processSumit(Model model, @ModelAttribute("room") Room room, BindingResult result) {
		ClassroomValidator validator = new ClassroomValidator();
		validator.validate(room, result);
		
		System.out.println(room.toString());
		if (result.hasErrors()) { // Errors in one of the required fields
			List<ObjectError> errors = result.getAllErrors();
			if(Globals.DEBUG == 1)
				System.out.println("-- Errors exist in one or more required fields --");
			for (ObjectError error : errors)
				if (Globals.DEBUG == 1)
					System.out.println(error);
			return "Classrooms";
		}
		else {
			return "Classrooms";
		}
	}
}