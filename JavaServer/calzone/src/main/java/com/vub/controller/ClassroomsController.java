package com.vub.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.exception.RoomNotFoundException;
import com.vub.model.JsonResponse;
import com.vub.model.Room;
import com.vub.service.RoomService;
import com.vub.validators.ClassroomValidator;

@Controller 
public class ClassroomsController {
	final Logger logger = LoggerFactory.getLogger(this.getClass());	

	@RequestMapping(value = "/classrooms", method = RequestMethod.GET)
	public String mainPage(Model model) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		RoomService roomService = (RoomService) context.getBean("roomService");

		// TODO - Two arraylists are passed to the model, while we can get the room name from the model. Fix this in the JSP, maybe?
		List<Room> classroomList = roomService.getRooms();
		List<String> classroomNamesList = new ArrayList<String>();
		for(int i=0; i<classroomList.size(); i++)
			classroomNamesList.add(roomService.getRoomVUBNotation(classroomList.get(i)));

		model.addAttribute("room", new Room());
		model.addAttribute("editWindow", false);
		model.addAttribute("classroomList", classroomList);
		model.addAttribute("classroomNamesList", classroomNamesList);
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
			roomService.createRoom(room);
			context.close();
			return "redirect:/classrooms";
		}
	}

	/**
	 * 
	 * @param value = new value put into field from user
	 * @param name = name of the value in the object
	 * @param pk = primary key of corresponding object in the database. Needed for operations using the corresponding service
	 * @return returns JsonResponse object with possible fags
	 * JsonResponse.setStatus("error") will cause a error in the popup. Corresponding message will be displayed
	 */
	@RequestMapping(value="/api/classrooms", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse testPost(@RequestParam(value="value") String value, @RequestParam(value="name") String name, @RequestParam(value="pk") int pk) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		RoomService roomService = (RoomService) context.getBean("roomService");
		
		logger.info("Received value: " + value + " and name: " + name + "and pk: " + pk);
		
		JsonResponse json = new JsonResponse();
		json.setStatus("success");
		
		Room room;
		try {
			room = roomService.findRoomById(pk);
		} catch (RoomNotFoundException e) {
			context.close();
			// This shouldn't really happen...
			return json;
		}
		
		switch(name) {
		case "displayName":
			if(!value.equals(roomService.getRoomVUBNotation(room)) && !value.isEmpty())
				room.setDisplayName(value);
			break;

		case "capacity":
			int number = Integer.parseInt(value);
			if(number <= 0) {
				json.setMessage("<spring:message code=\"validation.largerthanzero\"/>");
				json.setStatus("error");
			}
			else
				room.setCapacity(number);
			break;
		
		case "roomType":
			room.setType(Room.RoomType.valueOf(value));
			break;
			
		// Booleans (as defined by the javascript query) receive value "1 = true", "2 = false"
		case "projectorEquipped":
			room.setProjectorEquipped(Boolean.parseBoolean(value));
			break;
		case "smartBoardEquipped":
			room.setSmartBoardEquipped(Boolean.parseBoolean(value));
			break;
		case "recorderEquipped":
			room.setRecorderEquipped(Boolean.parseBoolean(value));
			break;
			
		default:
			break;
		}

		roomService.updateRoom(room);
		context.close();
		return json;
	}
}