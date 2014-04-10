package com.vub.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiCourse {
	/**
	 * 
	 * @param value = new value put into field from user
	 * @param name = name of the value in the object
	 * @param pk = primary key of corresponding object in the database. Needed for operations using the corresponding service
	 * @return returns JsonResponse object with possible fags
	 * JsonResponse.setStatus("error") will cause a error in the popup. Corresponding message will be displayed
	 */
	@RequestMapping(value="/api/course/all/formated", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<SelectResponse> testPost() {		
	final Logger logger = LoggerFactory.getLogger(this.getClass());
   
	ArrayList<SelectResponse> list = new ArrayList<SelectResponse>();
	list.add(new SelectResponse("1","Test 1"));
	list.add(new SelectResponse("2","Test 2"));
	System.out.println(list);
    return list;
    
    
	}
}