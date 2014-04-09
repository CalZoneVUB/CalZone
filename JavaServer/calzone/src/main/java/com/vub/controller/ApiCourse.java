package com.vub.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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
    public ArrayList<Select2Response> testPost() {		
	final Logger logger = LoggerFactory.getLogger(this.getClass());
   
	ArrayList<Select2Response> list = new ArrayList<Select2Response>();
	list.add(new Select2Response("1","Test 1"));
	list.add(new Select2Response("2","Test 2"));
	
    return list;
	}
}