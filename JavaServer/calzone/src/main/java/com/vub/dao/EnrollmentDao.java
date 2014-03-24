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
	 public void insertEnrollment(User user, Enrollment enrollment){
		// TODO AcademicYear
	 	db.insertEnrollment(user.getUserID(), enrollment.getCourse().getiD(), 20132014);
	 }

	 // Delete Enrollment
	 public void deleteEnrollment(User user, Enrollment enrollment){
		// TODO AcademicYear
	 	db.deleteEnrollment(user.getUserID(), enrollment.getCourse().getiD(), 20132014);
	 }
	 
		// Get Enrollments from user
	 public ArrayList<Enrollment> getEnrollments(User user){
		// TODO AcademicYear
	 	return db.selectEnrollmentsByUserID(user.getUserID(), 20132014);
	 }
	
	public void closeDao() {
		db.closeDbTranslate();
	} 
	 
}
