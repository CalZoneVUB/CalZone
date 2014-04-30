package com.vub.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vub.model.SelectResponseConverter;
import com.vub.model.User;
import com.vub.model.UserRole;
import com.vub.model.UserRole.UserRoleEnum;
import com.vub.service.UserService;

/**
 * @author Tim
 * API returning information recarding courses
 */
@Controller
public class ApiTeachers {
	
	@Autowired
	UserService userService;

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

	
}