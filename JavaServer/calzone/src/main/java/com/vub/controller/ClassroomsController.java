package com.vub.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.dao.RoomDao;
import com.vub.model.Globals;
import com.vub.model.Room;
import com.vub.model.RoomType;
import com.vub.validators.ClassroomValidator;

@Controller 
public class ClassroomsController {
	
	@RequestMapping(value = "/classrooms", method = RequestMethod.GET)
	public String mainPage(Model model) {
		RoomDao roomDao = new RoomDao();
		ArrayList<Room> classroomArrayList = roomDao.getRooms();
		
		model.addAttribute("room", new Room());
		model.addAttribute("editWindow", false);
		model.addAttribute("classroomArrayList", classroomArrayList);
		model.addAttribute("roomTypes", RoomType.values());
		roomDao.closeDao();
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
		} else {
			RoomDao roomDao = new RoomDao();
			roomDao.insertRoom(room);
			System.out.println(room.getType());
			roomDao.closeDao();
			return "redirect:/classrooms";
		}
	}
	
	@RequestMapping(value = "/classrooms/edit-{id}", method = RequestMethod.GET)
	public String editPagePost(Model model, @PathVariable int id) {
		RoomDao roomDao = new RoomDao();
		ArrayList<Room> classroomArrayList = roomDao.getRooms();
		
		model.addAttribute("room", getRoom(id, classroomArrayList));
		model.addAttribute("editWindow", true);
		model.addAttribute("classroomArrayList", classroomArrayList);
		model.addAttribute("roomTypes", RoomType.values());
		roomDao.closeDao();
		return "Classrooms"; 
	}
	
	@RequestMapping(value = "/classrooms/edit-{id}", method = RequestMethod.POST)
	public String editPage(Model model, @ModelAttribute("room") Room room, BindingResult result) {
		editRoom(room);
		return "redirect:/classrooms"; 
	}
	
	// TODO - it is more efficient to let the database handle the selection procedure
	private Room getRoom(int id, ArrayList<Room> classroomArrayList) {
		for(Room r : classroomArrayList) {
			if(r.getRoomId() == id)
				return r;
		}
		return null;
	}
	
	// TODO -- This is where rooms get edited!
	private void editRoom(Room room) {
		RoomDao roomDao = new RoomDao();
		Room r = getRoom(room.getRoomId(), roomDao.getRooms());
		if(r == null)
			roomDao.insertRoom(room);
		else {
			r.setBuilding(room.getBuilding());
			r.setFloor(room.getFloor());
			r.setName(room.getName());
			r.setCapacity(room.getCapacity());
			r.setProjectorEquipped(room.isProjectorEquipped());
			r.setRecorderEquipped(room.isRecorderEquipped());
			r.setSmartBoardEquipped(room.isSmartBoardEquipped());
			roomDao.insertRoom(r);
		}
		roomDao.closeDao();
	}
}