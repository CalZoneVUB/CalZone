package com.vub.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vub.model.Room;
import com.vub.service.RoomService;
import com.vub.validators.ClassroomValidator;

@Controller 
public class ClassroomsController {
	
	@RequestMapping(value = "/classrooms", method = RequestMethod.GET)
	public String mainPage(Model model) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		RoomService roomService = (RoomService) context.getBean("roomService");
		
		List<Room> classroomList = roomService.getRooms();
		classroomList.add(roomService.findRoomById(1));
		classroomList.add(roomService.findRoomById(2));

		model.addAttribute("room", new Room());
		model.addAttribute("editWindow", false);
		model.addAttribute("classroomList", classroomList);
		model.addAttribute("roomTypes", Room.RoomType.values());
		
		context.close();
		return "Classrooms"; 
	}
	
	@RequestMapping(value = "/classrooms" , method = RequestMethod.POST)
	public String processSumit(Model model, @ModelAttribute("room") Room room, BindingResult result) {
		ClassroomValidator validator = new ClassroomValidator();
		validator.validate(room, result);
		
		if (result.hasErrors()) // Errors in one of the required fields
			return "Classrooms";
		else {
			ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			RoomService roomService = (RoomService) context.getBean("roomService");
			roomService.persistRoom(room);
			context.close();
			return "redirect:/classrooms";
		}
	}
	
	@RequestMapping(value = "/classrooms/edit-{id}", method = RequestMethod.GET)
	public String editPagePost(Model model, @PathVariable int id) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		RoomService roomService = (RoomService) context.getBean("roomService");

		List<Room> classroomArrayList = roomService.getRooms();
		
		model.addAttribute("room", roomService.findRoomById(id));
		model.addAttribute("editWindow", true);
		model.addAttribute("classroomArrayList", classroomArrayList);
		model.addAttribute("roomTypes", Room.RoomType.values());
		context.close();
		return "Classrooms"; 
	}
	
	@RequestMapping(value = "/classrooms/edit-{id}", method = RequestMethod.POST)
	public String editPage(Model model, @ModelAttribute("room") Room room, BindingResult result) {
		
		if (result.hasErrors())// Errors in one of the required fields
			return "Classrooms";
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		RoomService roomService = (RoomService) context.getBean("roomService");
		System.out.println(room.toString());
		roomService.updateRoom(room);
		context.close();
		
		return "redirect:/classrooms"; 
	}
}