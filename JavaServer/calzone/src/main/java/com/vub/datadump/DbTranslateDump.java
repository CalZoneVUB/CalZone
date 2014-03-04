package com.vub.datadump;

//import java.security.spec.PSSParameterSpec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import com.vub.dao.UserDao;
import com.vub.model.Assistant;
import com.vub.model.ComponentType;
import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.Professor;
import com.vub.model.SessionIdentifierGenerator;
import com.vub.model.User;

public class DbTranslateDump {
	
	private static DbLinkDump DbLink;
	private static ResultSet rs = null;

	// When the object is created, open the connection with the db.

	public DbTranslateDump() {
		DbLinkDump.openConnection();
	}
	
	public ArrayList<Course> loadCourseId() {
		ArrayList<Course> listCourses = new ArrayList<Course>();	
		String sql = "SELECT `Studiedeel`, `Omschrijving`"
					+"FROM Cource_Id";
		
		
		rs = DbLinkDump.executeSqlQuery(sql);
		
		try {
			while (rs.next()) {
				Course course = new Course();
				course.setiD(rs.getInt(1));
				course.setDescription(rs.getString(2));
				listCourses.add(course);
			}
			return listCourses;
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<CourseComponent> loadCourseComponent(int studiedeel) {
		ArrayList<CourseComponent> listComponent = new ArrayList<CourseComponent>();
		String sql = "SELECT `Ingangsdatum`, `Onderdeel`, `Uren` "
				+"FROM Course_Offers "
				+"WHERE `Studiedeel`= '" + studiedeel + "'";
				
		rs = DbLinkDump.executeSqlQuery(sql);
		
		try {
			while (rs.next()) {
				CourseComponent courseComponent = new CourseComponent();
				Calendar cal = Calendar.getInstance();
				cal.setTime(rs.getDate(1));		
				courseComponent.setAcademicYear(cal.get(Calendar.YEAR)); 
				courseComponent.setComponentType(ComponentType.valueOf(rs.getString(2)));
				courseComponent.setContactHours(rs.getInt(3));
				
				if (courseComponent.getAcademicYear() == 2013) {
					listComponent.add(courseComponent);
				}
				
			}
			return listComponent;
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//Returns all the professors linked to a course
	public ArrayList<User> loadProfessor(int crouseId) {
		ArrayList<User> listProfessor = new ArrayList<User>();
		Set<User> setProfessor = new HashSet<User>();
		SessionIdentifierGenerator gen = new SessionIdentifierGenerator();
		String sql = "SELECT Course_Intructor.ID, Instructor_Name.Achternaam, Instructor_Name.Voornaam "
					+"FROM Course_Intructor "
					+"JOIN Instructor_Name ON Course_Intructor.ID = Instructor_Name.ID "
					+"WHERE Studiedeel= '" + crouseId + "' AND Course_Intructor.Titularis = 'Y'";
				
		rs = DbLinkDump.executeSqlQuery(sql);
		
		try {
			while (rs.next()) {
				User user = new User();
				// Initializing User object with data form the database. 
				user.setFirstName(rs.getString(3));
				user.setLastName(rs.getString(2));
				user.setUserName(rs.getString(2).replace(" ","").toLowerCase() + "." + rs.getString(3).replace(" ","").toLowerCase());
				user.setEmail(rs.getString(2).toLowerCase().replace(" ","") + "." + rs.getString(3).toLowerCase().replace(" ","") + ".thisisatest@vub.ac.be");				
				user.setUserTypeName("ROLE_PROFESSOR");
				
				UserDao userDao = new UserDao();
				if (userDao.checkIfUserNameAvailable(user.getUserName())) {
					userDao.insertNotEnabledUser(user);
					userDao.upgradeNotEnabledUser(user);
					setProfessor.add(user);
				} else {
					setProfessor.add(userDao.findByUserName(user.getUserName()));
				}
			}
			listProfessor.addAll(setProfessor);
			return listProfessor;
			
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<User> loadAssistant(int crouseId) {
		ArrayList<User> listAssistant = new ArrayList<User>();
		Set<User> setAssistant = new HashSet<User>();
		SessionIdentifierGenerator gen = new SessionIdentifierGenerator();
		String sql = "SELECT Course_Intructor.ID, Instructor_Name.Achternaam, Instructor_Name.Voornaam "
					+"FROM Course_Intructor "
					+"JOIN Instructor_Name ON Course_Intructor.ID = Instructor_Name.ID "
					+"WHERE Studiedeel= '" + crouseId + "' AND Course_Intructor.Titularis = 'N'";
				
		rs = DbLinkDump.executeSqlQuery(sql);
		
		try {
			while (rs.next()) {
				User user = new User();
				// Initializing User object with data form the database. 
				user.setFirstName(rs.getString(3));
				user.setLastName(rs.getString(2));
				user.setUserName(rs.getString(2).replace(" ","").toLowerCase() + "." + rs.getString(3).replace(" ","").toLowerCase());
				user.setEmail(rs.getString(2).toLowerCase().replace(" ","") + "." + rs.getString(3).toLowerCase().replace(" ","") + ".thisisatest@vub.ac.be");
				user.setPassword(gen.nextSessionId(256)); // Generating random password. User will need to reset this password.
				user.setUserTypeName("ROLE_ASSISTANT");

				UserDao userDao = new UserDao();
				if (userDao.checkIfUserNameAvailable(user.getUserName())) {
					userDao.insertNotEnabledUser(user);
					userDao.upgradeNotEnabledUser(user);
					setAssistant.add(user);
				} else {
					setAssistant.add(userDao.findByUserName(user.getUserName()));
				}
				listAssistant.add(user);
			}
			
			listAssistant.addAll(setAssistant);

			return listAssistant;
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
}
	