package com.vub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.model.JsonResponse;

@Controller
public class ApiProfile {
	/**
	 * 
	 * @param value = new value put into field from user
	 * @param name = name of the value in the object
	 * @param pk = primary key of corresponding object in the database. Needed for operations using the corresponding service
	 * @return returns JsonResponse object with possible fags
	 * JsonResponse.setStatus("error") will cause a error in the popup. Corresponding message will be displayed
	 */
	@RequestMapping(value="/api/profile", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse testPost(@RequestParam(value="value") String value, @RequestParam(value="name") String name,@RequestParam(value="pk") int pk) {		System.out.println("Received params value: " + value + " and name: " + name);
    JsonResponse json = new JsonResponse();
    json.setMessage("Try again");
    json.setStatus("success"); //json.setStatus("error");
    return json;
	}
}