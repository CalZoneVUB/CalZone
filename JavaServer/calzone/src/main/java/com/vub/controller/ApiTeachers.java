package com.vub.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.exception.UserNotFoundException;
import com.vub.model.CourseComponent;
import com.vub.model.SelectResponseConverter;
import com.vub.model.TeacherLecturePreference;
import com.vub.model.TeacherLecturePreferenceJson;
import com.vub.model.TeacherUnavailability;
import com.vub.model.TeacherUnavailabilityJson;
import com.vub.model.User;
import com.vub.model.UserRole.UserRoleEnum;
import com.vub.service.CourseComponentService;
import com.vub.service.CourseService;
import com.vub.service.UserService;
import com.vub.utility.Views;

/**
 * @author Tim
 * API returning information recarding courses
 */
@Controller
public class ApiTeachers {

	@Autowired
	UserService userService;
	
	@Autowired
	CourseComponentService courseComponentService;

	@Autowired
	CourseService courseService;
	
	

	/**
	 * @return returns list  of courses in format (courseId, courseName)
	 */
	@RequestMapping(value="/api/teacher/formated", method = RequestMethod.GET)
	@ResponseBody
	public List<SelectResponse> testPost() {		
		//final Logger logger = LoggerFactory.getLogger(this.getClass());
		Set<User> users = userService.getAllUsers();
		Set<User> teachers = new HashSet<User>();
		for (User u: users) {
			if(u.getUserRole().getUserRole() == UserRoleEnum.ROLE_ASSISTANT || u.getUserRole().getUserRole() == UserRoleEnum.ROLE_PROFESSOR) {
				teachers.add(u);
			}
		}

		SelectResponseConverter converter = new SelectResponseConverter();
		List<SelectResponse> listSelectResponses = converter.usersToSelectResponse(teachers);

		return listSelectResponses;
	}
	
	@RequestMapping(value = "/api/teacher/pref/component" , method = RequestMethod.POST)
	@ResponseBody
	public void postPreferences(@RequestBody TeacherLecturePreferenceJson teacherLecturePreferenceJson, Principal principal) {
		
		TeacherLecturePreference teacherLecturePreference = new TeacherLecturePreference();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("u");
			Date date = new Date();
			date.setTime(teacherLecturePreferenceJson.getStartingHour());
			teacherLecturePreference.setDayOfWeek(Integer.parseInt(sdf.format(date)));
			sdf = new SimpleDateFormat("k");
			date.setTime(teacherLecturePreferenceJson.getStartingHour());
			teacherLecturePreference.setStartingHour(Integer.parseInt(sdf.format(date)));
			date.setTime(teacherLecturePreferenceJson.getEndingHour());
			teacherLecturePreference.setEndingHour(Integer.parseInt(sdf.format(date)));
			teacherLecturePreference.setCourseComponent(courseComponentService.findCourseComponentByIdInitialized(teacherLecturePreferenceJson.getCourseComponentId()));
			User teacher = userService.findUserByUsername(principal.getName());
			//User teacher = userService.findUserById(242);
			teacherLecturePreference.setTeacher(teacher);
			teacher.getTeacherLecturePreferences().add(teacherLecturePreference);
			
			userService.updateUser(teacher);
		} catch (Exception e) {
			System.out.println("Fail:" + teacherLecturePreference.toString());
		}
		
	}

	@RequestMapping(value = "/api/teacher/pref/not" , method = RequestMethod.POST)
	@ResponseBody
	public void postPreferences(@RequestBody TeacherUnavailabilityJson teacherUnavailabilityJson, Principal principal) {
		
		TeacherUnavailability teacherUnavailability = new TeacherUnavailability();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("u"); //Gettng the number of the week format
			SimpleDateFormat sdfHour = new SimpleDateFormat("H");
			Date date = new Date();
			date.setTime(teacherUnavailabilityJson.getStartingHour());
			teacherUnavailability.setDayOfWeek(Integer.parseInt(sdf.format(date)));
			teacherUnavailability.setStartingHour(Integer.parseInt(sdfHour.format(date)));
			date.setTime(teacherUnavailabilityJson.getEndingHour());
			teacherUnavailability.setEndingHour(Integer.parseInt(sdfHour.format(date)));
			User teacher = userService.findUserByUsername(principal.getName());
			teacherUnavailability.setTeacher(teacher);
			teacher.getTeacherUnavailabilities().add(teacherUnavailability);
			userService.updateUser(teacher);
		} catch (Exception e) {
			
		}	
	}
	
	/**
	 * Returns set van teacher lecture preferences of the logged in professor
	 * @param principal
	 * @return
	 */
	@RequestMapping(value="/api/teacher/coursecomponents/prefs", method = RequestMethod.GET)
	@ResponseBody
	public String teacherCompps(Principal principal) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.getSerializationConfig().setSerializationView(Views.Prefs.class);
			objectMapper.configure(SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION, false);
			User user = userService.findUserByUsername(principal.getName());
			return objectMapper.writeValueAsString(user.getTeacherLecturePreferences());
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/api/teacher/coursecomponents/block", method = RequestMethod.GET)
	@ResponseBody
	public String teacherBlock(Principal principal) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.getSerializationConfig().setSerializationView(Views.Prefs.class);
			objectMapper.configure(SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION, false);
			User user = userService.findUserByUsername(principal.getName());
			return objectMapper.writeValueAsString(user.getTeacherUnavailabilities());
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Returns list of coursecomponents to still schedule by the teacher
	 * @param principal
	 * @return
	 */
	@RequestMapping(value="/api/teacher/coursecomponents", method = RequestMethod.GET)
	@ResponseBody
	public String getComponents(Principal principal) {		
		try {
			User user = userService.findUserByUsername(principal.getName());
			System.out.println("Teahcer Name: " + user.getUsername());
			//User user = userService.findUserById(242);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.getSerializationConfig().setSerializationView(Views.TeacherFilter.class);
			objectMapper.configure(SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION, false);

			Set<CourseComponent> components = user.getTeachingCourseComponents();
			System.out.println(components);
			Set<TeacherLecturePreference> preferences = user.getTeacherLecturePreferences();
			Set<CourseComponent> components2 = new HashSet<CourseComponent>();

			for (CourseComponent cc : components) {
				boolean test = false;
				for (TeacherLecturePreference tp: preferences) {
					if (cc.getId() == tp.getCourseComponent().getId()) {
						test = true;
					}
				}
				if (!test) {
					components2.add(cc);
				}
			}
			System.out.println(components2);

			return objectMapper.writeValueAsString(components2);
			
		} catch (UserNotFoundException e) {
			return null;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}




}