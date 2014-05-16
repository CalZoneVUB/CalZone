package com.vub.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vub.exception.UserNotFoundException;
import com.vub.model.CourseComponent;
import com.vub.model.TeacherLecturePreference;
import com.vub.model.User;
import com.vub.service.UserService;
import com.vub.utility.Views;


/**
 * 
 * @author Tim
 *
 */
@Controller 
public class CalendarScheduleController {


	@Autowired
	UserService userService;

	@RequestMapping(value = "/calendarschedule", method = RequestMethod.GET)
	public String calendarSchedularPage(Model model, Principal principal) throws UserNotFoundException {

		User user = userService.findUserByUsername(principal.getName());
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
		
		model.addAttribute("courseComponents",components2);
		return "CalendarSchedular";
	}
}