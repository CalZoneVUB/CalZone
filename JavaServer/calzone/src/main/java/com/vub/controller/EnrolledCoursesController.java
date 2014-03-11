package com.vub.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vub.dao.CourseDao;
import com.vub.dao.UserDao;
import com.vub.model.Course;
import com.vub.model.Enrollment;
import com.vub.model.User;

//@RequestMapping("/EnrolledCourses")
@Controller
public class EnrolledCoursesController {

	// Serving Enrolled Courses Page
	@RequestMapping(value = "/EnrolledCourses", method = RequestMethod.GET)
	public String enrolledCoursesPage(ModelMap model) {
		ArrayList<Course> courseArrayList = new ArrayList<Course>();
		CourseDao courseDao = new CourseDao();
		courseArrayList = courseDao.getCourses();
		courseDao.closeDao();
		Collections.sort(courseArrayList);
		model.addAttribute("courseArrayList", courseArrayList);
		return "EnrolledCourses";
	}
	
	@RequestMapping(value = "/EnrolledCourses/remove/{courseId}", method = RequestMethod.POST)
	public String removeCourse(Model model, @PathVariable String courseId, Principal principal) {
		User user = new UserDao().findByUserName(principal.getName());
		ArrayList<Enrollment> listOfEnrollments = user.getListOfEnrollments();
		// TODO verwijderen houdt nog gn rekening met academic year
		for (int i = 0; i < listOfEnrollments.size(); i++) {
			if (listOfEnrollments.get(i).getCourse().getiD() == Integer.parseInt(courseId)
					&& listOfEnrollments.get(i).getAcademicYear() == 20132014) {
				listOfEnrollments.remove(i);
				break;
			}
		}
		user.setListOfEnrollments(listOfEnrollments);
		return "EnrolledCourses";
	}
	
	

}
