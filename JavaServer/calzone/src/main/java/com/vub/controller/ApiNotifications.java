package com.vub.controller;


import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.vub.model.User;
import com.vub.model.Notification;

@Controller
public class ApiNotifications {

	@RequestMapping(value="/api/notifications/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Notification> testGet() {		
	
	final Logger logger = LoggerFactory.getLogger(getClass());	
	
	User user = new User();
	user.setUsername("TimboNA");
	ArrayList<Notification>  notList = new ArrayList<Notification>();
	Notification not = new Notification();
	not.setUser(user);
	not.setType("TimeChange");
	not.setMessage("Algo I changed from 10:00 to 12:00");
	notList.add(not);
	Notification not2 = new Notification();
	not2.setUser(user);
	not2.setType("TimeChange");
	not2.setMessage("Kansrekening I changed from 10:00 to 12:00");
	notList.add(not2);
	
    return notList;
	}
}