package com.vub.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vub.exception.CourseNotFoundException;
import com.vub.exception.FloorNotFoundException;
import com.vub.exception.RoomNotFoundException;
import com.vub.model.Course;
import com.vub.model.Floor;
import com.vub.model.JsonResponse;
import com.vub.model.Room;
import com.vub.service.BuildingService;
import com.vub.service.CourseService;
import com.vub.service.FloorService;
import com.vub.service.RoomService;

/**
 * @author Sam
 */
@Controller
public class ApiClassrooms {

	@Autowired 
	RoomService roomService;
	
	@Autowired
	FloorService floorService;
	
	@Autowired
	BuildingService buildingService;

	/**
	 * Method which deals with POST-requests on /api/classroom/new
	 * @param jsonString JSON in string form which is to be parsed and processed
	 * @return Returns a JsonReponse which can contain an error (or not)
	 */
	@RequestMapping(value="/api/classroom/new", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse newClassroom(@RequestBody String jsonString) {
		System.out.println(jsonString);
		
		JsonResponse jsonResponse = new JsonResponse();
		
		JsonParser parser = new JsonParser();
		JsonObject jObject = (JsonObject)parser.parse(jsonString);
		
		Room room = new Room();
		for (Map.Entry<String, JsonElement> entry : jObject.entrySet())
			this.processAPIRequest(room, jsonResponse, entry.getKey(), entry.getValue().getAsString());
		
		roomService.createRoom(room);
			
		return jsonResponse;
	}

	/**
	 * API Request to delete a certain classroom from the database
	 * @param id ID of the classroom to delete (pathvariable)
	 * @return Returns a JsonResponse, which is returned as a result of the API call
	 */
	@RequestMapping(value="/api/classroom/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse deleteClassroom(@PathVariable int id) {		
		JsonResponse jsonResponse = new JsonResponse();

		try {
			Room room = roomService.findRoomById(id);
			roomService.deleteRoom(room);
		} catch (RoomNotFoundException ex) {
			jsonResponse.setStatus("error");
			jsonResponse.setMessage("<spring:message code=\"classrooms.roomnotfound.text\"/>");
		} 		

		jsonResponse.setStatus("success");
		jsonResponse.setMessage("OK");

		return jsonResponse;
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
	public JsonResponse editField(@RequestParam(value="value") String value, @RequestParam(value="name") String name, @RequestParam(value="pk") int pk) {
		
		JsonResponse json = new JsonResponse();
		json.setStatus("success");
		
		try {
			Room room = roomService.findRoomById(pk);
			json = processAPIRequest(room, json, name, value);
			roomService.updateRoom(room);
			
		} catch (RoomNotFoundException e) {
			// This shouldn't really happen...
			json.setStatus("error");
			json.setMessage("Selected room could not be found in the database");
			return json;
		}
		
		return json;
	}
	
	/**
	 * 
	 * @param value = new value put into field from user
	 * @param name = name of the value in the object
	 * @param pk = primary key of corresponding object in the database. Needed for operations using the corresponding service
	 * @return returns JsonResponse object with possible fags
	 * JsonResponse.setStatus("error") will cause a error in the popup. Corresponding message will be displayed
	 */
	@RequestMapping(value="/api/classrooms/add", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse addClassroom(@RequestParam(value="value") String value, @RequestParam(value="name") String name, @RequestParam(value="pk") int pk) {
		JsonResponse json = new JsonResponse();
		json.setStatus("success");
		
		Room room = new Room();
		
		return json;
	}
	
	/**
	 * Method which does the right thing with a given key and value which has been received via a post request
	 * @param room The room which can be used to input data
	 * @param response Response which can be edited (which will contain the status and/or an error message)
	 * @param key Key of the data which needs to be processed
	 * @param value Value attached to the key
	 * @return JsonResponse which can be sent back to the front-end
	 */
	public JsonResponse processAPIRequest(Room room, JsonResponse response, String key, String value) {
		
		switch(key.toLowerCase()) {
		case "name":
			room.setName(value);
			break;
		
		case "displayname":
			if(!value.equals(roomService.getRoomVUBNotation(room)) && !value.isEmpty())
				room.setDisplayName(value);
			break;

		case "capacity":
			int number = Integer.parseInt(value);
			if(number <= 0) {
				response.setMessage("<spring:message code=\"validation.largerthanzero\"/>");
				response.setStatus("error");
			}
			else
				room.setCapacity(number);
			break;
		
		case "roomtype":
			room.setType(Room.RoomType.valueOf(value));
			break;
			
		case "projectorequipped":
			room.setProjectorEquipped(Boolean.parseBoolean(value));
			break;
		case "smartboardequipped":
			room.setSmartBoardEquipped(Boolean.parseBoolean(value));
			break;
		case "recorderequipped":
			room.setRecorderEquipped(Boolean.parseBoolean(value));
			break;
		
		case "building":
			// included in floor
			break;
		case "floor":
			int pk = Integer.parseInt(value);
			try {
				Floor floor = floorService.findFloorById(pk);
				room.setFloor(floor);
			} catch (FloorNotFoundException ex) {
				// This shouldn't really happen...
				response.setStatus("error");
				response.setMessage("<spring:message code=\"classrooms.floornotfound\"/>");
			}
			
		default:
			break;
		}	
		return response;
	}
}