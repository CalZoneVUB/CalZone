package com.vub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.model.JsonResponse;

@Controller
public class ApiAddCourse {
	/**
	 * 
	 * @param value = new value put into field from user
	 * @param name = name of the value in the object
	 * @param pk = primary key of corresponding object in the database. Needed for operations using the corresponding service
	 * @return returns JsonResponse object with possible fags
	 * JsonResponse.setStatus("error") will cause a error in the popup. Corresponding message will be displayed
	 */
	@RequestMapping(value="/api/AddCourse", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse testPost(@RequestParam(value="value") String value, @RequestParam(value="name") String name,@RequestParam(value="pk") int pk) {		
	
	final Logger logger = LoggerFactory.getLogger(getClass());	
	
	logger.info("Received params value: " + value + " and name: " + name + "and pk: " + pk);
    JsonResponse json = new JsonResponse();
    json.setMessage("Try again");
    json.setStatus("success"); //json.setStatus("error");
    
    //TODO Change this to working with the user/person services
    
    return json;
	}
}