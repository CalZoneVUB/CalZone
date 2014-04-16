package com.vub.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.internal.compiler.flow.FinallyFlowContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.vub.exception.CourseComponentNotFoundException;
import com.vub.exception.CourseNotFoundException;
import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.CourseComponent.CourseComponentTerm;
import com.vub.model.CourseComponent.CourseComponentType;
import com.vub.model.JsonResponse;
import com.vub.model.SelectResponseConverter;
import com.vub.model.XeditCheckbox;
import com.vub.service.CourseComponentService;
import com.vub.service.CourseService;

/**
 * @author Tim
 * API returning information recarding courses
 */
@Controller
public class ApiCourse {

	/**
	 * @return returns list  of courses in foramte (courseId, courseName)
	 */
	@RequestMapping(value="/api/course/all/formated", method = RequestMethod.GET)
	@ResponseBody
	public List<SelectResponse> testPost() {		
		//final Logger logger = LoggerFactory.getLogger(this.getClass());

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseService courseService = (CourseService) context.getBean("courseService");

		Set<Course> courseSet = courseService.getCourses();
		List<Course> courseArray = new ArrayList<Course>(courseSet);

		SelectResponseConverter converter = new SelectResponseConverter();
		List<SelectResponse> listSelectResponses = converter.classesToSelectResponse(courseArray);
		System.out.println(listSelectResponses);
		context.close();
		return listSelectResponses;
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
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseService courseService = (CourseService) context.getBean("courseService");
		CourseComponentService courseComponentService = (CourseComponentService) context.getBean("courseComponentService");

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
				case 0: courseComponent.setTerm(CourseComponentTerm.S1);
				case 1: courseComponent.setTerm(CourseComponentTerm.S2);
				case 2: courseComponent.setTerm(CourseComponentTerm.S3);
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

		context.close();
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
				case 2: courseComponent.setRoomSMARTBoardRequirement(true); break;
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
}