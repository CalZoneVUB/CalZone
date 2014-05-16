package com.vub.controller;

import java.util.ArrayList;
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
import com.vub.scheduler.Scheduler;
import com.vub.scheduler.SchedulerSolver;
import com.vub.scheduler.constraints.ConstraintChecker;
import com.vub.scheduler.constraints.ConstraintViolation;
import com.vub.service.EntryService;
import com.vub.service.RoomService;
import com.vub.service.TrajectService;

@Controller
public class HelloController {

	@Autowired
	private TrajectService trajectService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private EntryService entryService;

	@RequestMapping(value = "/hello")
	public String sayHello(Model model) {
		model.addAttribute("greeting", "Hello World");

		boolean allowed = true;

		if (allowed == true) {
			Set<Entry> entries = entryService.getEntries();
			for (Entry e : entries) {
				if (!e.isFrozen()) {
					entryService.deleteEntry(e);
				}
			}

			List<Room> roomsList = new ArrayList<Room>();
			roomsList.addAll(roomService.getRooms());

			Set<Traject> trajects = new HashSet<Traject>();
			Traject traject = new Traject();
			traject = trajectService.findTrajectByIdInitializedFull(181); //177
			// 64 for computer science
			// 177 for test traject
			 System.out.println(traject);

			trajects.add(traject);

			for (Traject t : trajects) {
				 System.out.println(t);
				for (Course c : t.getCourses()) {
					 System.out.println(c);
					for (CourseComponent cc : c.getCourseComponents()) {
						 System.out.println(cc);
						for (User u : cc.getTeachers()) {
							System.out.println(u.getUsername());
						}
					}
				}
			}
			SchedulerSolver schedularSolver = new SchedulerSolver(2013, roomsList,
					trajects);
			Scheduler schedular = schedularSolver.run();
			
			ConstraintChecker checker = new ConstraintChecker(schedularSolver.getScoreDirector());
			List<ConstraintViolation> list = checker.getViolations();
			list.get(0).description();
			
			for (Entry e : schedular.getEntryList()) {
				entryService.updateEntry(e);
				System.out.println("Schedule: " + e);
			}
		}

		return "hello";
	}
}
