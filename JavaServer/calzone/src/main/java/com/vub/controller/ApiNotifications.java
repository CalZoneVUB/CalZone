package com.vub.controller;


import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.vub.exception.UserNotFoundException;
import com.vub.model.JsonResponse;
import com.vub.model.NotificationComparator;
import com.vub.model.NotificationType;
import com.vub.model.Notification;
import com.vub.model.User;
import com.vub.service.NotificationService;
import com.vub.service.UserService;


@Controller
public class ApiNotifications {


	@Autowired
	NotificationService notificationService;

	@Autowired
	UserService userService;
	/**
	 * Returns all notifications corresponding to the {id}
	 * @return ArrayList<Notifiasciton>
	 */
	@RequestMapping(value="/api/notifications", method = RequestMethod.GET)
	@ResponseBody
	public Set<Notification> testGet(Principal principal) {		

		try {
			User user = userService.findUserByUsername(principal.getName());
			Set<Notification> notifications = user.getNotifications();
			List<Notification> list = new ArrayList<Notification>();
			list.addAll(notifications);
			Collections.sort(list, new NotificationComparator());
			return notifications;
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Removes all notifications corresponding to the user id
	 * @return Json
	 */
	@RequestMapping(value="/api/notifications/remove", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse testGetRemove(Principal principal) {		

		JsonResponse jsonResponse = new JsonResponse();
		
		try {
			User user = userService.findUserByUsername(principal.getName());
			Set<Notification> notifications = user.getNotifications();
			for (Notification n: notifications) {
				notificationService.deleteNotification(n);
			}
			jsonResponse.setStatus(JsonResponse.SUCCESS);
			return jsonResponse;
		} catch (UserNotFoundException e) {
			jsonResponse.setStatus(JsonResponse.ERROR);
			jsonResponse.setStatus(e.toString());
			e.printStackTrace();
			return jsonResponse;
		}
	}

	/**
	 * 
	 * @return json containing value of the amount of notifications of the user.
	 */
	@RequestMapping(value="/api/notifications/amount", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse testGetAmount(Principal principal) {		

		JsonResponse jsonResponse = new JsonResponse();
		
		try {
			User user = userService.findUserByUsername(principal.getName());
			Set<Notification> notifications = user.getNotifications();

			jsonResponse.setStatus(JsonResponse.SUCCESS);
			jsonResponse.setMessage(Integer.toString(notifications.size()));
			return jsonResponse;
		} catch (UserNotFoundException e) {
			jsonResponse.setStatus(JsonResponse.ERROR);
			jsonResponse.setStatus(e.toString());
			e.printStackTrace();
			return jsonResponse;
		} 
	}
}