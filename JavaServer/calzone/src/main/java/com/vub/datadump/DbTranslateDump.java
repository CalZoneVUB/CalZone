package com.vub.datadump;

//import java.security.spec.PSSParameterSpec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vub.exception.UserNotFoundException;
import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.CourseComponent.CourseComponentTerm;
import com.vub.model.CourseData;
import com.vub.model.Faculty;
import com.vub.model.Institution;
import com.vub.model.Person;
import com.vub.model.Program;
import com.vub.model.Program.Carreer;
import com.vub.model.SessionIdentifierGenerator;
import com.vub.model.Traject;
import com.vub.model.User;
import com.vub.model.UserRole;
import com.vub.service.CourseService;
import com.vub.service.FacultyService;
import com.vub.service.ProgramService;
import com.vub.service.TrajectService;
import com.vub.service.UserService;

public class DbTranslateDump {

	private static DbLinkDump DbLink;
	private static ResultSet rs = null;

	// When the object is created, open the connection with the db.

	public DbTranslateDump() {
		DbLinkDump.openConnection();
	}
	
	public ArrayList<Faculty> loadFaculties(Institution institution){
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		FacultyService facultyService = (FacultyService) context.getBean("facultyService");

		ArrayList<Faculty> listFaculties = new ArrayList<Faculty>();
		String sql = "SELECT DISTINCT `Faculty`"
					+ " FROM Trajects";

		rs = DbLinkDump.executeSqlQuery(sql);

		try {
			while (rs.next()) {
				
				Faculty faculty = new Faculty();
				faculty.setFacultyName(rs.getString(1));
				faculty.setInstitution(institution);
				faculty = facultyService.createFaculty(faculty);
				listFaculties.add(faculty);
			}
			context.close();
			return listFaculties;
		} catch (SQLException e) {
			e.printStackTrace();
			context.close();
			return null;
		}
	}
	
	public ArrayList<Program> loadProgramsOfFaculty(Faculty faculty){
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProgramService programService = (ProgramService) context.getBean("programService");

		ArrayList<Program> listPrograms = new ArrayList<Program>();
		String sql = "SELECT DISTINCT `Program`, `Career`"
					+ " FROM Trajects "
					+ " WHERE `Faculty` = '"+ faculty.getFacultyName() +"'";

		rs = DbLinkDump.executeSqlQuery(sql);

		try {
			while (rs.next()) {
				
				Program program = new Program();
				program.setProgramName(rs.getString(1));
				program.setCarreer(Carreer.valueOf(rs.getString(2)));
				program.setFaculty(faculty);
				program = programService.createProgram(program);
				listPrograms.add(program);
			}
			context.close();
			return listPrograms;
		} catch (SQLException e) {
			e.printStackTrace();
			context.close();
			return null;
		}
	}
	
	public ArrayList<Traject> loadTrajectsOfProgram(Program program){
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		TrajectService trajectService = (TrajectService) context.getBean("trajectService");

		ArrayList<Traject> listTrajects = new ArrayList<Traject>();
		String sql = "SELECT DISTINCT `Traject`, `Year`"
					+ " FROM Trajects "
					+ " WHERE `Faculty` = '"+ program.getFaculty().getFacultyName() +"' AND `Program` = '"+ program.getProgramName() +"' AND `Career` = '"+program.getCarreer().toString()+"'";

		rs = DbLinkDump.executeSqlQuery(sql);

		try {
			while (rs.next()) {
				
				Traject traject = new Traject();
				traject.setTrajectName(rs.getString(1));
				traject.setYear(rs.getInt(2));
				traject.setProgram(program);
				traject = trajectService.createTraject(traject);
				listTrajects.add(traject);
			}
			context.close();
			return listTrajects;
		} catch (SQLException e) {
			e.printStackTrace();
			context.close();
			return null;
		}
	}
	
	public ArrayList<String> loadCourseNamesOfTraject(Traject traject){

		ArrayList<String> listCourses = new ArrayList<String>();
		String sql = "SELECT DISTINCT `Course`"
					+ " FROM Trajects "
					+ " WHERE `Traject` = '"+ traject.getTrajectName() +"'";

		rs = DbLinkDump.executeSqlQuery(sql);

		try {
			while (rs.next()) {
				listCourses.add(rs.getString(1));
			}
			return listCourses;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Course> loadCourseId() {
		ArrayList<Course> listCourses = new ArrayList<Course>();
		String sql = "SELECT `Studiedeel`, `Omschrijving`" + "FROM Cource_Id";

		rs = DbLinkDump.executeSqlQuery(sql);

		try {
			while (rs.next()) {
				
				Course course = new Course();
				CourseData courseData = new CourseData();
				course.setStudiedeel(rs.getInt(1));
				course.setCourseName(rs.getString(2));
				course.setCourseData(courseData);
				listCourses.add(course);
			}
			return listCourses;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Set<CourseComponent> loadCourseComponent(Course course) {
		Set<CourseComponent> listComponent = new HashSet<CourseComponent>(0);
		String sql = "SELECT DISTINCT Course_Offers.Ingangsdatum, Course_Offers.Onderdeel, Course_Offers.Uren, Course_TypeOff.Omschrijving"
				+ " FROM Course_Offers " 
				+ " JOIN Course_Owner ON Course_Offers.Studiedeel = Course_Owner.Studiedeel"
				+ " JOIN Course_TypeOff ON Course_Owner.DoorsneeAanbod = Course_TypeOff.DoorsneeAanbod"
				+ " WHERE Course_Offers.Studiedeel= '" + course.getStudiedeel()
				+ "' AND Course_Offers.Ingangsdatum >=  '2013-01-01 00:00:00.000'"; // YEAR 2013

		rs = DbLinkDump.executeSqlQuery(sql);

		try {
			while (rs.next()) {
				/**
				 * Make a courseComponent object for each result.
				 * With default values:
				 * <ul>
				 * <li>HOC duration = 2 hours</li>
				 * <li>WPO duration = 2 hours</li>
				 * <li>EXM duration = 4 hours</li>
				 * <li>Ending Date = Starting Date + 1 year</li>
				 * </ul>
				 */
				CourseComponent courseComponent = new CourseComponent();
				courseComponent.setStartingDate(rs.getDate(1));
				Calendar cal = Calendar.getInstance();
				cal.setTime(courseComponent.getStartingDate());
				cal.add(Calendar.YEAR, 1);
				courseComponent.setEndingDate(cal.getTime());
				courseComponent.setType(CourseComponent.CourseComponentType.valueOf(rs.getString(2)));
				if (courseComponent.getType() == CourseComponent.CourseComponentType.EXM){
					courseComponent.setDuration(4);
				} else if (courseComponent.getType() == CourseComponent.CourseComponentType.WPO || courseComponent.getType() == CourseComponent.CourseComponentType.HOC){
					courseComponent.setDuration(2);
				} else courseComponent.setDuration(0);
				courseComponent.setContactHours(rs.getInt(3));
				String semester = rs.getString(4);
				if ("1e semester".equals(semester)) {
					courseComponent.setTerm(CourseComponentTerm.S1);
				} else if ("2e semester".equals(semester)) {
					courseComponent.setTerm(CourseComponentTerm.S2);
				} else if ("1e en 2e semester".equals(semester)) {
					courseComponent.setTerm(CourseComponentTerm.S3);
				} else if ("2jaarlijks: 1e sem oneven acjr".equals(semester)) {
					courseComponent.setTerm(CourseComponentTerm.S4);
				} else if ("2jaarlijks: 2e sem oneven acjr".equals(semester)) {
					courseComponent.setTerm(CourseComponentTerm.S5);
				} else if ("2jaarlijks: 1+2sem oneven acjr".equals(semester)) {
					courseComponent.setTerm(CourseComponentTerm.S6);
				} else if ("2jaarlijks: 1e sem even acjr".equals(semester)) {
					courseComponent.setTerm(CourseComponentTerm.S7);
				} else if ("2jaarlijks: 2e sem even acjr".equals(semester)) {
					courseComponent.setTerm(CourseComponentTerm.S8);
				} else if ("2jaarlijks: 1+2sem even acjr".equals(semester)) {
					courseComponent.setTerm(CourseComponentTerm.S9);
				} else courseComponent.setTerm(CourseComponentTerm.EX);
				courseComponent.setCourse(course);
				listComponent.add(courseComponent);
			}
			return listComponent;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Returns all the professors linked to a course
	public Set<User> loadProfessor(Course course) {
		Set<User> listProfessor = new HashSet<User>(0);
		SessionIdentifierGenerator gen = new SessionIdentifierGenerator();
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		
		String sql = "SELECT DISTINCT Course_Intructor.ID, Instructor_Name.Achternaam, Instructor_Name.Voornaam "
				+ "FROM Course_Intructor "
				+ "JOIN Instructor_Name ON Course_Intructor.ID = Instructor_Name.ID "
				+ "WHERE Studiedeel= '"
				+ course.getStudiedeel()
				+ "' AND Course_Intructor.Titularis = 'Y'";

		rs = DbLinkDump.executeSqlQuery(sql);

		try {
			while (rs.next()) {
				User user = new User();
				Person person = new Person();
				UserRole userRole = new UserRole();
				// Initializing User object with data form the database.
				person.setFirstName(rs.getString(3).replace("'", ""));
				person.setLastName(rs.getString(2).replace("'", ""));
				user.setUsername(rs.getString(2).replace(" ", "")
						.replace("'", "").toLowerCase()
						+ "."
						+ rs.getString(3).replace(" ", "").replace("'", "")
								.toLowerCase());
				person.setEmail(rs.getString(2).toLowerCase().replace("'", "")
						.replace(" ", "")
						+ "."
						+ rs.getString(3).toLowerCase().replace("'", "")
								.replace(" ", "") + ".thisisatest@vub.ac.be");
				userRole.setUserRole(UserRole.UserRoleEnum.ROLE_PROFESSOR);
				person.setBirthdate(new Date(1990, 02, 01));
				user.setPassword(gen.nextSessionId(256)); // Generating random
															// password. User
															// will need to
															// reset this
															// password.
				user.setEnabled(true);
				user.setPerson(person);
				user.setUserRole(userRole);


				try {
					User user2;
					user2 = userService.findUserByUsername(user.getUsername());
					listProfessor.add(user2);
				} catch (UserNotFoundException e1) {
					user = userService.createUser(user);
					listProfessor.add(user);
				}
			}
			context.close();
			return listProfessor;

		} catch (SQLException e) {
			e.printStackTrace();
			context.close();
			return null;
		}
	}

	public Set<User> loadAssistant(Course course) {
		Set<User> listAssistant = new HashSet<User>(0);
		SessionIdentifierGenerator gen = new SessionIdentifierGenerator();
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		String sql = "SELECT DISTINCT Course_Intructor.ID, Instructor_Name.Achternaam, Instructor_Name.Voornaam "
				+ "FROM Course_Intructor "
				+ "JOIN Instructor_Name ON Course_Intructor.ID = Instructor_Name.ID "
				+ "WHERE Studiedeel= '"
				+ course.getStudiedeel()
				+ "' AND Course_Intructor.Titularis = 'N'";

		rs = DbLinkDump.executeSqlQuery(sql);

		try {
			while (rs.next()) {
				User user = new User();
				Person person = new Person();
				UserRole userRole = new UserRole();
				// Initializing User object with data form the database.
				person.setFirstName(rs.getString(3).replace("'", ""));
				person.setLastName(rs.getString(2).replace("'", ""));
				user.setUsername(rs.getString(2).replace(" ", "")
						.replace("'", "").toLowerCase()
						+ "."
						+ rs.getString(3).replace("'", "").replace(" ", "")
								.toLowerCase());
				person.setEmail(rs.getString(2).toLowerCase().replace("'", "")
						.replace(" ", "")
						+ "."
						+ rs.getString(3).toLowerCase().replace("'", "")
								.replace(" ", "") + ".thisisatest@vub.ac.be");
				user.setPassword(gen.nextSessionId(256)); // Generating random
															// password. User
															// will need to
															// reset this
															// password.
				userRole.setUserRole(UserRole.UserRoleEnum.ROLE_ASSISTANT);
				person.setBirthdate(new Date(1990, 02, 01));
				user.setEnabled(true);
				user.setPerson(person);
				user.setUserRole(userRole);

				try {
					User user2;
					user2 = userService.findUserByUsername(user.getUsername());
					listAssistant.add(user2);
				} catch (UserNotFoundException e1) {
					user = userService.createUser(user);
					listAssistant.add(user);
				}
			}

			context.close();
			return listAssistant;
		} catch (SQLException e) {
			e.printStackTrace();
			context.close();
			return null;
		}
	}

}
