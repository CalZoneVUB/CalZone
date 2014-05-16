package com.vub.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintValidator;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.exception.CourseNotFoundException;
import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.Entry;
import com.vub.model.JsonResponse;
import com.vub.model.Room;
import com.vub.model.SelectResponseConverter;
import com.vub.model.Traject;
import com.vub.model.User;
import com.vub.scheduler.Scheduler;
import com.vub.scheduler.SchedulerInitializer;
import com.vub.scheduler.SchedulerScoreCalculator;
import com.vub.scheduler.SchedulerSolver;
import com.vub.scheduler.constraints.ConstraintChecker;
import com.vub.scheduler.constraints.ConstraintViolation;
import com.vub.service.CourseComponentService;
import com.vub.service.CourseService;
import com.vub.service.EntryService;
import com.vub.service.RoomService;
import com.vub.service.TrajectService;



/**
 * 
 * @author Tim
 *
 */
@Controller
public class ApiTraject {
	@Autowired
	TrajectService trajectService;

	@Autowired
	EntryService entryService;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	CourseComponentService componentService;

	@RequestMapping(value="/api/traject/schedule/{id}")
	@ResponseBody
	public JsonResponse schedulerTraject(@PathVariable int id) {
		JsonResponse jsonResponse = new JsonResponse();
		
		//Removing all not frozen entrys from the traject
		Set<Entry> entries = trajectService.getAllEntries(trajectService.findTrajectById(id));
		for (Entry e : entries) {
			if (!e.isFrozen()) {
				System.out.println("delete");
				entryService.deleteEntry(e);
			}
		}
		
		List<Room> roomsList = new ArrayList<Room>();
		roomsList.addAll(roomService.getRooms());

		Set<Traject> trajects = new HashSet<Traject>();
		Traject traject = new Traject();
		traject = trajectService.findTrajectByIdInitializedFull(id); 
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
		
		SchedulerSolver schedularSolver = new SchedulerSolver(2013, roomsList,trajects);
		Scheduler schedular = schedularSolver.run();
		ConstraintChecker checker = new ConstraintChecker(schedularSolver.getScoreDirector());
		List<ConstraintViolation> list = checker.getViolations();
		
		//Saving all entrys
		for (Entry e : schedular.getEntryList()) {
			entryService.updateEntry(e);
			System.out.println("Schedule: " + e);
		}
		
		jsonResponse.setStatus(JsonResponse.SUCCESS);
		jsonResponse.setMessage(list);
		return jsonResponse;
	}
	
	@RequestMapping(value="/api/traject/freeze/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse freeze(@PathVariable int id) {		
		JsonResponse jsonResponse = new JsonResponse();
		Traject traject = trajectService.findTrajectById(id);
		if (traject != null) {
			traject.setFrozen(true);
			trajectService.updateTraject(traject);
			jsonResponse.setStatus(JsonResponse.SUCCESS);
			return jsonResponse;
		} else {
			jsonResponse.setStatus(JsonResponse.ERROR);
			return jsonResponse;
		}
	}

	@RequestMapping(value="/api/traject/constraints/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse getContraints(@PathVariable int id) {
		JsonResponse jsonResponse = new JsonResponse();

		try {
			List<Room> roomsList = new ArrayList<Room>();
			roomsList.addAll(roomService.getRooms());

			Set<Traject> trajects = new HashSet<Traject>();
			Traject traject = new Traject();
			traject = trajectService.findTrajectByIdInitializedFull(id); 
			trajects.add(traject);

			//Initialize the object in a lazy way till teachers.
			//This needs to be done because object is detached inside Scheduler
			for (Traject t : trajects) {
				for (Course c : t.getCourses()) {
					for (CourseComponent cc : c.getCourseComponents()) {
						for (User u : cc.getTeachers()) {
							u.getId();
						}
					}
				}
			}

			List<Entry> entrys = new ArrayList<Entry>();
			entrys.addAll(trajectService.getAllEntries(traject));
			
			for (Entry e : entrys) {
				e.getCourseComponent();
			}

			SchedulerInitializer schedulerInitializer = new SchedulerInitializer();
						
			Scheduler scheduler = new Scheduler(schedulerInitializer.createSlotsOfYear(2013), roomsList, entrys, trajects);
			SchedulerScoreCalculator schedulerScoreCalculator = new SchedulerScoreCalculator(scheduler);
			ConstraintChecker checker = new ConstraintChecker(schedulerScoreCalculator.getScoreDirector());
			List<ConstraintViolation> constraintViolations = checker.getViolations();

			jsonResponse.setStatus(JsonResponse.SUCCESS);
			ObjectMapper objectMapper = new ObjectMapper();
			List<String> strings = new ArrayList<String>();
			for (ConstraintViolation cv : constraintViolations) {
				if (!cv.description().equals("")) {
					strings.add(cv.description());
				}
			}
			jsonResponse.setMessage(strings);
			
			//return objectMapper.writeValueAsString(strings);
			return jsonResponse;
		} catch (Exception e) {
			jsonResponse.setStatus(JsonResponse.ERROR);
			return jsonResponse;
		}
	}


	/**
	 * @return returns list  of trajects in formated (trajectId, trajectName)
	 */
	@RequestMapping(value="/api/traject/all/formated", method = RequestMethod.GET)
	@ResponseBody
	public List<SelectResponse> testPost() {		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		TrajectService trajectService = (TrajectService) context.getBean("trajectService");

		Set<Traject> trajectSet = trajectService.getTrajects();
		List<Traject> trajectArray = new ArrayList<Traject>(trajectSet);

		SelectResponseConverter converter = new SelectResponseConverter();
		List<SelectResponse> listSelectResponses = converter.trajectsToSelectResponse(trajectArray);
		System.out.println(listSelectResponses);
		context.close();
		return listSelectResponses;
	}

	@RequestMapping(value="api/traject/course/new", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse trajectCourseAdd(@RequestParam(value="value") String value, @RequestParam(value="name") String name,@RequestParam(value="pk") int pk) {		

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseService courseService = (CourseService) context.getBean("courseService");
		TrajectService trajectService = (TrajectService) context.getBean("trajectService");
		JsonResponse json = new JsonResponse();

		try {
			Traject traject = trajectService.findTrajectByIdInitialized(pk);
			Course course = courseService.findCourseByIdInitialized(Integer.parseInt(value));
			Set<Course> courses = traject.getCourses();
			courses.add(course);
			traject.setCourses(courses);

			trajectService.updateTraject(traject);

			context.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			json.setStatus(JsonResponse.ERROR);
			return json;
		} catch (CourseNotFoundException e) {
			e.printStackTrace();
			json.setStatus(JsonResponse.ERROR);
			return json;		
		}
		json.setStatus(JsonResponse.SUCCESS);

		return json;
	}
	@RequestMapping(value="/api/traject/new", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse newCourse(@RequestBody String string) {		
		final Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info(string);

		//Opening courseService
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseService courseService = (CourseService) context.getBean("courseService");
		TrajectService trajectService = (TrajectService) context.getBean("trajectService");

		JsonResponse json = new JsonResponse();

		try {
			//Extracting all values form the string format key=value&key=value
			ArrayList<String> arrayList2 = new ArrayList<String>();
			String[] parts = string.split("&");
			for (int i=0;i<parts.length;i++) {
				int index = parts[i].lastIndexOf("=");
				arrayList2.add(parts[i].substring(index+1));
			}

			logger.info(arrayList2.toString());

			//Ceating traject object
			Traject traject = new Traject();
			traject.setTrajectName(arrayList2.get(0).replace("+", " "));
			traject.setStartingYear(Integer.parseInt(arrayList2.get(1)));

			//Getting courses associated with the id form the request
			Set<Course> setCourses = new HashSet<Course>(0);
			for (int i=2;i<arrayList2.size();i++) {
				Course course = new Course();
				course = courseService.findCourseById(Integer.parseInt(arrayList2.get(i)));
				setCourses.add(course);
			}

			//Adding trajecty to the database
			traject.setCourses(setCourses);
			trajectService.createTraject(traject);

			//Returning positive message to front-end
			json.setStatus("success");
			json.setMessage("All went good");

		} catch (Exception e){
			//something went worng and returning the error to the front-end
			json.setStatus("error");
			json.setMessage("Something went worng: "+ e);
			logger.debug(e.toString());
		} finally {
			context.close();	
		}

		return json;
	}

	@RequestMapping(value="/api/traject/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse deleteTraject(@PathVariable int id) {		
		final Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("Deliting Coruse with id: " + id);

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		TrajectService trajectService = (TrajectService) context.getBean("trajectService");
		JsonResponse jsonResponse = new JsonResponse();

		try {
			Traject traject = trajectService.findTrajectById(id);
			trajectService.deleteTraject(traject);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setStatus("error");
			jsonResponse.setMessage("Traject not found with id: " + id);
		} 		

		jsonResponse.setStatus("success");
		jsonResponse.setMessage("OK");

		context.close();
		return jsonResponse;
	}

	@RequestMapping(value="api/traject/edit", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse trajectEdit(@RequestParam(value="value") String value, @RequestParam(value="name") String name,@RequestParam(value="pk") int pk) {		
		JsonResponse jsonResponse = new JsonResponse();
		
		Traject traject = trajectService.findTrajectById(pk);
		if (name.equals("courseName")) {
			traject.setTrajectName(value);
			jsonResponse.setStatus(JsonResponse.SUCCESS);
		} else if (name.equals("courseYear")) {
			traject.setYear(Integer.parseInt(value));
			jsonResponse.setStatus(JsonResponse.SUCCESS);
		} else {
			jsonResponse.setStatus(JsonResponse.ERROR);
		}

		trajectService.updateTraject(traject);
		return jsonResponse;
	}


	@RequestMapping(value="api/traject/delete/course/{courseId}/{trajectId}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse deleteTrajectCourse(@PathVariable int courseId, @PathVariable int trajectId ) {		
		final Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("Deleting Course of Traject with id: " + trajectId);

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		TrajectService trajectService = (TrajectService) context.getBean("trajectService");
		JsonResponse jsonResponse = new JsonResponse();

		try {
			Traject traject = trajectService.findTrajectByIdInitialized(trajectId);
			Set<Course> courses = traject.getCourses();
			Course toDelete = new Course();
			for (Course course : courses) {
				if (course.getId() == courseId) {
					toDelete = course;
				}
			}
			if (toDelete != null) {
				courses.remove(toDelete);
				traject.setCourses(courses);
				System.out.println(traject);
				trajectService.updateTraject(traject);
			}
			//trajectService.deleteTraject(traject);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setStatus("error");
			jsonResponse.setMessage("Traject not found with id: " + trajectId);
		} 		

		jsonResponse.setStatus("success");
		jsonResponse.setMessage("OK");

		context.close();
		return jsonResponse;
	}
}