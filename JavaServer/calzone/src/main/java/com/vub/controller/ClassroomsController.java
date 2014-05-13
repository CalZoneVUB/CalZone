package com.vub.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vub.exception.RoomNotFoundException;
import com.vub.model.Building;
import com.vub.model.Floor;
import com.vub.model.JsonResponse;
import com.vub.model.Room;
import com.vub.service.BuildingService;
import com.vub.service.FloorService;
import com.vub.service.RoomService;
import com.vub.validators.ClassroomValidator;


/**
 * @author Tim
 * Controller to display / add / edit classrooms in the database
 */
@Controller 
public class ClassroomsController {
	final Logger logger = LoggerFactory.getLogger(this.getClass());	

	@RequestMapping(value = "/classrooms", method = RequestMethod.GET)
	public String mainPage(Model model) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		RoomService roomService = (RoomService) context.getBean("roomService");

		// TODO - Two arraylists are passed to the model, while we can get the room name from the model. Fix this in the JSP, maybe?
		Set<Room> classroomSet = roomService.getRooms();
		List<Room> classroomList = new ArrayList<Room>(classroomSet);
		
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

	@RequestMapping(value = "/classrooms/create", method = RequestMethod.GET)
	public String createClassroomPage(Model model) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		FloorService floorService = (FloorService) context.getBean("floorService");
		BuildingService buildingService = (BuildingService) context.getBean("buildingService");
		
		JsonArray buildingDataSource = new JsonArray();
		JsonObject floorDataSource = new JsonObject();
		
		Set<Building> buidingSet = buildingService.getAllBuildings();
		List<Building> buildings = new ArrayList<Building>(buidingSet);
		
		// Loop over all buildings in the database
		for(int i = 0; i<buildings.size();i++) {
			// Get the current building
			Building b = buildings.get(i);
			JsonObject jObject = new JsonObject();
			jObject.addProperty("value", b.getId());
			jObject.addProperty("text", b.getName());
			
			buildingDataSource.add(jObject);
			
			JsonArray floorArr = new JsonArray();
			
			// Construct the floors
			Set<Floor> floorsSet = floorService.getFloorsFromBuilding(b.getName(), "VUB");
			List<Floor> floors = new ArrayList<Floor>(floorsSet);

			for(int j = 0; j<floors.size();j++) {
				Floor f = floors.get(j);
				JsonObject floorObj = new JsonObject();
				floorObj.addProperty("value", f.getId());
				floorObj.addProperty("text", f.getFloor());
				floorArr.add(floorObj);
			}
			
			floorDataSource.add(String.valueOf(b.getId()), floorArr);
		}
		
		model.addAttribute("buildingSource", buildingDataSource.toString());
		model.addAttribute("floorSource", floorDataSource.toString());
		context.close();
		return "AddClassroom"; 
	}
	
	/**
	 * @param model : Model of the /classroom controller
	 * @param room : Room object as ModelAttirbute
	 * @param result : BindingResult of the post request
	 * @return : returns back to same page with erros or redirect to /classrooms 	 
	 */
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
}
