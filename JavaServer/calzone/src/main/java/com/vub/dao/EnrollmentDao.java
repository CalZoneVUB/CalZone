package com.vub.dao;

//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;




//import javax.swing.tree.RowMapper;
import org.springframework.stereotype.Component;

import com.vub.db.DbTranslate;
import com.vub.model.Enrollment;
import com.vub.model.User;

@Component("EnrollmentDao")
public class EnrollmentDao {
	// Database Access Object
	
	DbTranslate db = new DbTranslate();

	// Insert Enrollment
	 public void insertEnrollment(Enrollment enrollment){
		// TODO AcademicYear
	 	db.insertEnrollment(enrollment.getUserID(), enrollment.getCourse().getiD(), 20132014);
	 }

	 // Delete Enrollment
	 public void deleteEnrollment(Enrollment enrollment){
		// TODO AcademicYear
	 	db.deleteEnrollment(enrollment.getUserID(), enrollment.getCourse().getiD(), 20132014);
	 }
	 
		// Get Enrollments from user
	 public void getEnrollments(User user){
		// TODO AcademicYear
	 	db.selectEnrollmentsByUserID(user.getUserID(), 20132014);
	 }


	// List of all the Courses in database
	//public ArrayList<Course> getCourses() {
	//	ArrayList<Course>  courses = db.selectAllCourses();
	//	return courses;
	//}
	
	public void closeDao() {
		db.closeDbTranslate();
	} 
	 
}
