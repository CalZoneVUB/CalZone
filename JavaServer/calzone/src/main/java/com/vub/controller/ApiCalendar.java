package com.vub.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.Entry;
import com.vub.model.Room;
import com.vub.model.Traject;
import com.vub.service.EntryService;
import com.vub.service.TrajectService;



@Controller
@RequestMapping("api/calendar")
public class ApiCalendar {
	
	
	@Autowired
	EntryService entryService;
	
	@Autowired
	TrajectService trajectService;

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
    public ArrayList<Entry>  test(@PathVariable String type, @PathVariable int id, @PathVariable int week, Principal principal) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		
		ArrayList<Entry> list = new ArrayList<Entry>();
		
		Traject trajact = trajectService.findTrajectById(177);
		for (Course c: trajact.getCourses()) {
			for(CourseComponent cc: c.getCourseComponents()) {
				for (Entry e: cc.getEntries()) {
					list.add(e);
				}
			}
		}
        
		return list;
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