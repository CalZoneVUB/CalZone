package com.vub.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.exception.UserNotFoundException;
import com.vub.model.CourseComponent;
import com.vub.model.SelectResponseConverter;
import com.vub.model.TeacherLecturePreference;
import com.vub.model.User;
import com.vub.model.UserRole.UserRoleEnum;
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
			//User user = userService.findUserById(242);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.getSerializationConfig().setSerializationView(Views.TeacherFilter.class);
			objectMapper.configure(SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION, false);

			Set<CourseComponent> components = user.getTeachingCourseComponents();
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