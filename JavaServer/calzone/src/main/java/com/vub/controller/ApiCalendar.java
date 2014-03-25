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
	
	@RequestMapping(value = "{type}/{id}/{week}", method = RequestMethod.GET)
    @ResponseBody
    public Room test(@PathVariable String type, @PathVariable int id, @PathVariable int week) {
		Room room = new Room();
		room.setCapacity(100);
		room.setProjectorEquipped(true);
		room.setRecorderEquipped(true);	
        return room;
    }
	
	@RequestMapping(value = "{type}/{id}/{week}", method = RequestMethod.POST)
    @ResponseBody
    public Room testPost(@RequestBody Room room) {
		System.out.println(room);
        return room;
    }

}
