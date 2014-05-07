package com.vub.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.Entry;
import com.vub.model.ProfileSlot;
import com.vub.model.ProfileSlot.Badge;
import com.vub.model.ProfileSlot.Color;
import com.vub.model.Room;
import com.vub.model.Traject;
import com.vub.model.User;
import com.vub.scheduler.Schedular;
import com.vub.scheduler.SchedularSolver;
import com.vub.scheduler.SchedulerInitializer;
import com.vub.service.EntryService;
import com.vub.service.RoomService;
import com.vub.service.TrajectService;

@Controller 
public class MessagesController {

	@Autowired
	private TrajectService trajectService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private EntryService entryService;

	@RequestMapping(value = "/messages")
	public String sayHello(Model model) {
		
		List<ProfileSlot> profileSlots = new ArrayList<ProfileSlot>();
		ProfileSlot profileSlot = new ProfileSlot();
		profileSlot.setBadge(Badge.time);
		List<String> str = new ArrayList<String>();
		str.add("List");
		str.add("List 2");
		profileSlot.setTitle("Title");
		profileSlot.setDescriptions(str);
		profileSlots.add(profileSlot);
		profileSlot.setColor(null);
		ProfileSlot profileSlot2 = new ProfileSlot();
		profileSlot2.setBadge(null);
		profileSlot2.setColor(Color.success);
		profileSlot2.setIconText("10:00");
		profileSlot2.setDescriptions(str);
		profileSlot2.setTitle("Title 2");
		profileSlots.add(profileSlot2);
		profileSlots.add(profileSlot);
		profileSlots.add(profileSlot2);
		
		model.addAttribute("profileSlots", profileSlots);
		
		return "messages";
	}
}
