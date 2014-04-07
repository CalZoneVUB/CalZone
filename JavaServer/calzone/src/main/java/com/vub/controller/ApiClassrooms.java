package com.vub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.exception.RoomNotFoundException;
import com.vub.model.JsonResponse;
import com.vub.model.Room;
import com.vub.service.RoomService;

@Controller
public class ApiClassrooms {
	final Logger logger = LoggerFactory.getLogger(this.getClass());	
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

		JsonResponse json = new JsonResponse();
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
			if(number <= 0)
				json.setMessage("<spring:message code=\"validation.largerthanzero\"/>");
			else
				room.setCapacity(number);
			break;
		default:
			break;
		}
		
		roomService.updateRoom(room);
		logger.info("Received value: " + value + " and name: " + name + "and pk: " + pk);
		json.setStatus("success"); //json.setStatus("error");
		context.close();
		return json;
	}
}