package com.vub.dao;

//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;




//import javax.swing.tree.RowMapper;
import org.springframework.stereotype.Component;

import com.vub.db.DbTranslate;
import com.vub.model.Course;

@Component("CourseDao")
public class CourseDao {
	// Database Access Object
	
	DbTranslate db = new DbTranslate();

	// Insert Course
	 public void insertCourse(Course course){
		// TODO AcademicYear
	 	//db.insertCourse(course, 20132014);
	 }
	 
	// get Course with CourseID back from database
	/*public Course findByCourseID(int courseID) {
		//Course course = db.selectCourseByCourseID(courseID);
		return course;
	}*/
	 
	// List of all the Courses in database
	/*public ArrayList<Course> getCourses() {
		ArrayList<Course>  courses = db.selectAllCourses();
		return courses;
	}*/
	
	public void closeDao() {
		db.closeDbTranslate();
	} 
	 
}
