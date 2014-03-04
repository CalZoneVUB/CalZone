package com.vub.datadump;

//import java.security.spec.PSSParameterSpec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

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
	public ArrayList<Professor> loadProfessor(int crouseId) {
		ArrayList<Professor> listProfessor = new ArrayList<Professor>();
		Set<Professor> setProfessor = new HashSet<Professor>();
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
				user.setPassword(gen.nextSessionId(256)); // Generating random password. User will need to reset this password.
				
				Professor professor = new Professor(user);
				professor.setiD(rs.getInt(1));
				//professor.setiD(iD);
				setProfessor.add(professor);
				//listProfessor.add(professor);
			}
			//setProfessor.addAll(listProfessor);
			//listProfessor.clear();
			listProfessor.addAll(setProfessor);
			return listProfessor;
			
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Assistant> loadAssistant(int crouseId) {
		ArrayList<Assistant> listAssistant = new ArrayList<Assistant>();
		Set<Assistant> setAssistant = new HashSet<Assistant>();
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
				
				Assistant assistant = new Assistant(user);
				
				listAssistant.add(assistant);
			}
			setAssistant.addAll(listAssistant); //Deleting all duplicates by storing the ArrayList in a set.
			listAssistant.clear();
			listAssistant.addAll(setAssistant);
			//Setting random passsword for all users
			for (Assistant assistant : listAssistant) {
				
			}
			return listAssistant;
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
}
	