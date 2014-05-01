package com.vub.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vub.exception.FloorNotFoundException;
import com.vub.exception.RoomNotFoundException;
import com.vub.model.Floor;
import com.vub.model.JsonResponse;
import com.vub.model.Room;
import com.vub.service.BuildingService;
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