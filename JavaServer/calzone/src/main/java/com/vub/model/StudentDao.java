/**
 * @author youri
 */
package com.vub.model;

//import java.util.List;

import org.springframework.stereotype.Component;

import com.vub.db.DbTranslate;

@Component("StudentDao")
public class StudentDao {
	// Database Access Object
	DbTranslate db = new DbTranslate();
	/* TODO: add the necessary methods to db package!

	// List of all the students in database
		public List<Student> getStudents() {
			List<Student>  students = db.selectAllStudents();
			return students;
		}
		
		public boolean isStudent(String userName){
			//get usertype of the user and check whether it's a student
			UserDao userDao = new UserDao();
			User foundUser = userDao.findByUserName(userName);
			return foundUser.getUserTypeName() == UserType.Student;
		}
		public List<Course> getCurrentCourses(String userName) {
			//check if userName is related to student
			//take the courses list from the Subscription of the current academic year
			if(isStudent(userName)){
				//CODE HERE
			}
			 
		}
	*/
}
