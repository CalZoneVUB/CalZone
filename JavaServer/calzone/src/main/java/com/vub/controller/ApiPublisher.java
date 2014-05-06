package com.vub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vub.model.JsonResponse;
import com.vub.service.NotificationService;

/**
 * @author Sam
 */
@Controller
public class ApiPublisher {

	@Autowired
	NotificationService notificationService;
	
	@RequestMapping(value="api/publish/systemmessage", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse publishSytemMessage(@RequestBody String jsonString) {
		JsonParser parser = new JsonParser();
		JsonObject jObject = (JsonObject)parser.parse(jsonString);
		notificationService.PublishSystemNotification(jObject.get("message").getAsString());
		
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setStatus("success");
		return jsonResponse;
	}
}