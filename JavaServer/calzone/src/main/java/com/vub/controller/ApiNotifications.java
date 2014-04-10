package com.vub.controller;


import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.vub.model.JsonResponse;
import com.vub.model.NotifiactionType;
import com.vub.model.Notification;
import com.vub.model.User;


@Controller
public class ApiNotifications {

	/**
	 * Returns all notifications corresponding to the {id}
	 * @return ArrayList<Notifiasciton>
	 */
	@RequestMapping(value="/api/notifications/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Notification> testGet() {		
	
	final Logger logger = LoggerFactory.getLogger(getClass());	
	//TODO implement function
	//TODO check if id corresponds to logged in user 
	
	User user = new User();
	user.setUsername("TimboNA");
	ArrayList<Notification>  notList = new ArrayList<Notification>();
	Notification not = new Notification(user, NotifiactionType.Time);
	String[] list = {"Alogritme I","10:00","12:00"};
	not.setMessage(list);
	//not.setUser(user);
	//not.setType(NotifiactionType.Time);
	//not.newMessage(NotifiactionType.Time , "Alogoritme I", "10:00" , "12:00");
	//not.setMessage("Algo I changed from 10:00 to 12:00");
	notList.add(not);
	Notification not2 = new Notification(user, NotifiactionType.System);
	String[] list2 = {"Servers will be down 12/12/2015"};
	not2.setMessage(list2);
	//not2.setUser(user);
	//not2.setType(NotifiactionType.System);
	//not2.setMessage("Servers will be down 12/12/2015");
	//not2.newMessage(NotifiactionType.System, "Servers will be down 12/12/2015");
	notList.add(not2);
	
    return notList;
	}
	
	/**
	 * Removes all notifications corresponding to the user id
	 * @return Json
	 */
	@RequestMapping(value="/api/notifications/remove/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse testGetRemove() {		
	
	final Logger logger = LoggerFactory.getLogger(getClass());	
	
	logger.info("TODO remove notifications from DB");
	//TODO remove all notifications form user
	//TODO check if id corresponds to request of user
	
	JsonResponse json = new JsonResponse();
	json.setStatus("success");
	return json;

	}
	
	/**
	 * 
	 * @return json containing value of the amount of notifications of the user.
	 */
	@RequestMapping(value="/api/notifications/amount/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String testGetAmount() {		
	
	final Logger logger = LoggerFactory.getLogger(getClass());	
	
	logger.info("TODO remove notifications from DB");
	//TODO count amount of notifications
	//TODO check if id correspodns to request of user
	JsonResponse json = new JsonResponse();
	Gson gson = new Gson();
	gson.toJson("{\"value\": 2}");
	logger.info(gson.toString());
    //json.setMessage("Try again");
    json.setStatus("success"); //json.setStatus("error");
    json.setMessage("{\"value\": 2}");
	return "{\"value\": 2}";
	}
}