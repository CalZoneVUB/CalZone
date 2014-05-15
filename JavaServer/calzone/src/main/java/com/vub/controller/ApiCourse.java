package com.vub.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vub.exception.CourseComponentNotFoundException;
import com.vub.exception.CourseNotFoundException;
import com.vub.exception.UserNotFoundException;
import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.CourseComponent.CourseComponentTerm;
import com.vub.model.CourseComponent.CourseComponentType;
import com.vub.model.CourseData;
import com.vub.model.JsonResponse;
import com.vub.model.SelectResponseConverter;
import com.vub.model.User;
import com.vub.model.XeditCheckbox;
import com.vub.scheduler.SchedulerInitializer;
import com.vub.service.CourseComponentService;
import com.vub.service.CourseService;
import com.vub.service.UserService;

/**
 * @author Tim
 * API returning information recarding courses
 */
@Controller
public class ApiCourse {

	@Autowired
	CourseService courseService;

	@Autowired
	UserService userService;
	
	@Autowired
	CourseComponentService courseComponentService;

	/**
	 * @return returns list  of courses in foramte (courseId, courseName)
	 */
	@RequestMapping(value="/api/course/all/formated", method = RequestMethod.GET)
	@ResponseBody
	public List<SelectResponse> testPost() {		
		//final Logger logger = LoggerFactory.getLogger(this.getClass());

		Set<Course> courseSet = courseService.getCourses();
		List<Course> courseArray = new ArrayList<Course>(courseSet);

		SelectResponseConverter converter = new SelectResponseConverter();
		List<SelectResponse> listSelectResponses = converter.classesToSelectResponse(courseArray);
		System.out.println(listSelectResponses);

		return listSelectResponses;
	}

	@RequestMapping(value="/api/course/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse deleteCourse(@PathVariable int id) {		
		final Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("Deliting Coruse with id: " + id);

		JsonResponse jsonResponse = new JsonResponse();

		try {
			Course course = courseService.findCourseByIdInitialized(id);
			courseService.deleteCourse(course);
		} catch (CourseNotFoundException e) {
			e.printStackTrace();
			jsonResponse.setStatus("error");
			jsonResponse.setMessage("Course not found with id: " + id);
		} 		

		jsonResponse.setStatus("success");
		jsonResponse.setMessage("OK");

		return jsonResponse;
	}

	/**
	 * @param value : value input by the user
	 * @param name : name given by the id in javascript
	 * @param pk : primary key of the object in the database
	 * @return returns Json success or error message
	 * @throws CourseNotFoundException 
	 * @throws CourseComponentNotFoundException 
	 */
	@RequestMapping(value="/api/course/edit", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse postEdit(@RequestParam(value="value") String value, @RequestParam(value="name") String name,@RequestParam(value="pk") int pk) throws CourseNotFoundException, CourseComponentNotFoundException {		

		final Logger logger = LoggerFactory.getLogger(this.getClass());	
		logger.info("Received params value: " + value + " and name: " + name + "and pk: " + pk);

		JsonResponse json = new JsonResponse();
		

		try {
			if (name.equals("courseName")) {
				Course course = courseService.findCourseById(pk);
				course.setCourseName(value);
				courseService.updateCourse(course);
			} else if (name.equals("courseDataDescription")) {
				Course course = courseService.findCourseByIdInitialized(pk);
				course.getCourseData().setDescription(value);
				courseService.updateCourse(course);
			} else if (name.equals("courseDataReexaminationPossible")) {
				Course course = courseService.findCourseByIdInitialized(pk);
				switch (Integer.parseInt(value)) {
				case 0: course.getCourseData().setReexaminationPossible(false); break;
				case 1: course.getCourseData().setReexaminationPossible(true); break;
				}
				courseService.updateCourse(course);
			} else if (name.equals("courseDataECTS")) {
				Course course = courseService.findCourseByIdInitialized(pk);
				course.getCourseData().setECTS(Integer.parseInt(value));
				courseService.updateCourse(course);
			} else if (name.equals("courseDataLanguage")) {
				Course course = courseService.findCourseByIdInitialized(pk);
				course.getCourseData().setLanguage(value);
				courseService.updateCourse(course);
			} else if (name.equals("courseDataDescirption")) {
				Course course = courseService.findCourseByIdInitialized(pk);
				course.getCourseData().setDescription(value);
				courseService.updateCourse(course);
			} else if (name.equals("courseDataGrading")) {
				Course course = courseService.findCourseByIdInitialized(pk);
				course.getCourseData().setGrading(value);
				courseService.updateCourse(course);
			} else if (name.equals("courseComponentType")) {
				CourseComponent courseComponent = courseComponentService.findCourseComponentByIdInitialized(pk);
				switch (Integer.parseInt(value)) {
				case 0: courseComponent.setType(CourseComponentType.HOC); break;
				case 1: courseComponent.setType(CourseComponentType.WPO); break;
				case 2: courseComponent.setType(CourseComponentType.EXM); break;
				case 3: courseComponent.setType(CourseComponentType.ZLF); break;
				}
				courseComponentService.updateCourseComponent(courseComponent);
			} else if (name.equals("courseComponentContactHours")) {
				CourseComponent courseComponent = courseComponentService.findCourseComponentByIdInitialized(pk);
				courseComponent.setContactHours(Integer.parseInt(value));
				courseComponentService.updateCourseComponent(courseComponent);
			} else if (name.equals("courseComponentTerm")) {
				CourseComponent courseComponent = courseComponentService.findCourseComponentByIdInitialized(pk);
				switch (Integer.parseInt(value)) {
				case 0: courseComponent.setTerm(CourseComponentTerm.S1); break;
				case 1: courseComponent.setTerm(CourseComponentTerm.S2); break;
				case 2: courseComponent.setTerm(CourseComponentTerm.S3); break;
				}
				courseComponentService.updateCourseComponent(courseComponent);
			} else if (name.equals("courseComponentDuration")) {
				CourseComponent courseComponent = courseComponentService.findCourseComponentByIdInitialized(pk);
				courseComponent.setDuration(Integer.parseInt(value));
				courseComponentService.updateCourseComponent(courseComponent);
			} else if (name.equals("courseComponentRoomCapacityRequirement")) {
				CourseComponent courseComponent = courseComponentService.findCourseComponentByIdInitialized(pk);
				courseComponent.setRoomCapacityRequirement(Integer.parseInt(value));
				courseComponentService.updateCourseComponent(courseComponent);
			} else {
				System.out.println("Nothing received");
			}

			System.out.println("Received params value: " + value + " and name: " + name + " and pk: " + pk);

			json.setMessage("Try again");
			json.setStatus("success"); 

		} catch (CourseComponentNotFoundException e) {
			json.setMessage("Try again");
			json.setStatus("error"); 
			e.printStackTrace();
		}  catch (CourseNotFoundException e) {
			json.setMessage("Try again");
			json.setStatus("error"); 
			e.printStackTrace();
		} 

		return json;
	}

	@RequestMapping(value="/api/course/edit/checkbox", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse postEditCheckbox(@RequestBody String string) {		

		JsonResponse json = new JsonResponse();

		try {
			Gson gson = new Gson();
			XeditCheckbox xedit = gson.fromJson(string, XeditCheckbox.class);

			ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			CourseComponentService courseComponentService = (CourseComponentService) context.getBean("courseComponentService");
			CourseComponent courseComponent = courseComponentService.findCourseComponentByIdInitialized(xedit.getId());

			for (int i = 0; i < xedit.getValue().length; i++) {
				switch (Integer.parseInt(xedit.getValue()[i])){
				case 0: courseComponent.setRoomProjectorRequirement(true); break;
				case 1: courseComponent.setRoomRecorderRequirement(true); break;
				case 2: courseComponent.setRoomSmartBoardRequirement(true); break;
				}
			}
			json.setMessage("Try again");
			json.setStatus("success");

			courseComponentService.updateCourseComponent(courseComponent);
			context.close();
		} catch (CourseComponentNotFoundException e) {
			json.setMessage("Try again");
			json.setStatus("error"); //json.setStatus("error");
			e.printStackTrace();
		} 

		return json;
	}

	@RequestMapping(value="/api/course/new", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse testPost(@RequestBody String string) {
		System.out.println(string);

		JsonResponse jsonResponse = new JsonResponse();
		JsonParser parser = new JsonParser();
		JsonObject json = (JsonObject) parser.parse(string);
		Course course = new Course();

		int courseComponentCount = ((json.entrySet().size()) - 7) /  9; //Returns the amount of coursecomponent are in the request
		System.out.println("Amount CourseComponent: " + courseComponentCount);

		course.setCourseName(json.get("new_courseName").getAsString());
		CourseData courseData = new CourseData();
		course.setCourseData(courseData);
		course.getCourseData().setECTS(json.get("new_ECTS").getAsInt());
		course.getCourseData().setStudyTime(json.get("new_studyTime").getAsInt());
		switch ((json.get("new_reexamination").getAsInt())) {
		case 0:
			course.getCourseData().setReexaminationPossible(false); break;
		case 1:
			course.getCourseData().setReexaminationPossible(true); break;
		}
		switch (json.get("new_language").getAsInt()) {
		case 0:
			course.getCourseData().setLanguage("BE_NL"); break;
		case 1:
			course.getCourseData().setLanguage("UK_ENG"); break;
		}
		course.getCourseData().setLearningGoals(json.get("new_results").getAsString());
		course.getCourseData().setGrading(json.get("new_grading").getAsString());
		List<CourseComponent> components = new ArrayList<CourseComponent>();
		if (courseComponentCount > 0 ) {
			for (int i = 1; i < courseComponentCount+1; i++) {
				CourseComponent cc = new CourseComponent();
				switch (json.get("new_courseComponent" + i).getAsInt()) {
				case 0:
					cc.setType(CourseComponentType.HOC); break;
				case 1:
					cc.setType(CourseComponentType.WPO); break;
				case 2:
					cc.setType(CourseComponentType.EXM); break;
				case 3: 
					cc.setType(CourseComponentType.ZLF); break;
				}
				switch (json.get("new_semester" + i).getAsInt()) {
				case 0:
					cc.setTerm(CourseComponentTerm.EX); break;
				case 1:
					cc.setTerm(CourseComponentTerm.S1); break;
				case 2:
					cc.setTerm(CourseComponentTerm.S2); break;
				case 3: 
					cc.setTerm(CourseComponentTerm.S3); break;
				}
				cc.setContactHours(json.get("new_contactHours" + i).getAsInt());
				cc.setStartingDate(SchedulerInitializer.createSlotsOfWeek(2014, json.get("new_startDate" + i).getAsInt()).get(0));
				cc.setEndingDate(SchedulerInitializer.createSlotsOfWeek(2014, json.get("new_endDate" + i).getAsInt()).get(0));
				cc.setDuration(json.get("new_duration" + i).getAsInt());
				cc.setRoomCapacityRequirement(json.get("new_roomCapacity" + i).getAsInt());
				Set<User> teachers = new HashSet<User>();
				try {
					teachers.add(userService.findUserById(json.get("new_teacher" + i).getAsInt()));
				} catch (UserNotFoundException e) {
					e.printStackTrace();
				}
				cc.setTeachers(teachers);
				//TODO include projector 
				components.add(cc);
				System.out.println("CC");
				cc.setCourse(course);

			}
		}

		System.out.println(components);
		//course.setCourseComponents(components);
		jsonResponse.setMessage(course.toString());
		jsonResponse.setStatus(JsonResponse.SUCCESS);

		courseService.updateCourse(course);
		for (CourseComponent c : components) {
			courseComponentService.updateCourseComponent(c);
		}


		return jsonResponse;
	}

}