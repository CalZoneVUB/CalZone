package com.vub.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vub.model.Entry;
import com.vub.model.Room;
import com.vub.model.Traject;
import com.vub.repository.EntryRepository;
import com.vub.repository.TrajectRepository;
import com.vub.scheduler.Schedular;
import com.vub.scheduler.SchedularSolver;
import com.vub.scheduler.SchedulerInitializer;
import com.vub.service.EntryService;
import com.vub.service.RoomService;
import com.vub.service.TrajectService;

@Controller 
public class HelloController {
	

	@RequestMapping(value = "/hello")
	public String sayHello(Model model) {
		model.addAttribute("greeting", "Hello World");
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//solution.setEntityManagerFactoryBeanName("entityManagerFactory");
	TrajectService trajectService = (TrajectService) context.getBean("trajectService");
	RoomService roomService = (RoomService) context.getBean("roomService");
	EntryService entryService = (EntryService) context.getBean("entryService");
//		
//		Traject traject = trajectService.findTrajectById(178);
//		context.close();
//		traject.getCourses().iterator().next().getCourseName();
		List<Room> roomsList = new ArrayList<Room>();
		roomsList.addAll(roomService.getRooms());
	
		Set<Traject> trajects = new HashSet<Traject>();
		Traject traject = new Traject();
		traject = trajectService.findTrajectByIdInitializedFull(178);
		
		trajects.add(traject);
		
		List<Date> dateSlots = SchedulerInitializer.createSlotsOfWeek(2014, 4);
		dateSlots.addAll(SchedulerInitializer.createSlotsOfWeek(2014, 5));
		dateSlots.addAll(SchedulerInitializer.createSlotsOfWeek(2014, 6));

		SchedularSolver schedularSolver = new SchedularSolver(dateSlots, roomsList, trajects);
		Schedular schedular = schedularSolver.run();
		
		for (Entry e: schedular.getEntryList()) {
			entryService.updateEntry(e);
			System.out.println("Schedule: "  + e);
		}
		context.close();
		
		return "hello";
	}
}
