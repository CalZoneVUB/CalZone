package com.vub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.model.Room;



@Controller
@RequestMapping("api/calendar")
public class ApiCalendar {
	
	/**
	 * This function is used by the calendar to serve json to be displayed. Only possible to fetch data for each week
	 * 
	 * @param type: Indicating the type of the request to the api. Options are course,user,room
	 * @param id: id in the database of the type. Example: user with id 33
	 * @param week: indication witch week of the calendar year to server
	 * @return returns a list of {@link #<Entry>} back in json format
	 */
	@RequestMapping(value = "{type}/{id}/{week}", method = RequestMethod.GET)
    @ResponseBody
    public Room test(@PathVariable String type, @PathVariable int id, @PathVariable int week) {
		Room room = new Room();
		room.setCapacity(100);
		room.setProjectorEquipped(true);
		room.setRecorderEquipped(true);	
        return room;
    }
	
	/**
	 * Will be deleted. No function for POST request
	 * @param room
	 * @return
	 */
	@RequestMapping(value = "{type}/{id}/{week}", method = RequestMethod.POST)
    @ResponseBody
    public Room testPost(@RequestBody Room room) {
		System.out.println(room);
        return room;
    }
}