package com.vub.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.model.Entry;
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
	 * @throws ParseException 
	 */
	@RequestMapping(value = "{type}/{id}/{week}", method = RequestMethod.GET)
    @ResponseBody
    public Room test(@PathVariable String type, @PathVariable int id, @PathVariable int week) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		
		Room room = new Room();
		room.setCapacity(100);
		room.setProjectorEquipped(true);
		room.setRecorderEquipped(true);	
		
		Entry entry = new Entry();
		Date d1 = sdf.parse("04-04-2014 14:00:00");
		entry.setStartDate(d1);
		Date d2 = sdf.parse("04-04-2014 16:00:00");
		entry.setEndDate(d2);

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