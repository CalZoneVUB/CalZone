package com.vub.datadump;

//import java.security.spec.PSSParameterSpec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vub.model.Course;

public class DbTranslateDump {

	private static DbLinkDump DbLink;
	private static ResultSet rs = null;

	// When the object is created, open the connection with the db.

	public DbTranslateDump() {
		DbLinkDump.openConnection();
	}

	public ArrayList<Course> loadCourseId() {
		ArrayList<Course> listCourses = new ArrayList<Course>();
		String sql = "SELECT `Studiedeel`, `Omschrijving`" + "FROM Cource_Id";

		rs = DbLinkDump.executeSqlQuery(sql);

		try {
			while (rs.next()) {
				// TODO - Update met toegevoegde services
				/*
				Course course = new Course();
				course.setiD(rs.getInt(1));
				course.setDescription(rs.getString(2));
				listCourses.add(course);*/
			}
			return listCourses;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

/*	public ArrayList<CourseComponent> loadCourseComponent(int studiedeel) {
		ArrayList<CourseComponent> listComponent = new ArrayList<CourseComponent>();
		String sql = "SELECT `Ingangsdatum`, `Onderdeel`, `Uren` "
				+ "FROM Course_Offers " + "WHERE `Studiedeel`= '" + studiedeel
				+ "'";

		rs = DbLinkDump.executeSqlQuery(sql);

		try {
			while (rs.next()) {
				CourseComponent courseComponent = new CourseComponent();
				Calendar cal = Calendar.getInstance();
				cal.setTime(rs.getDate(1));
				courseComponent.setAcademicYear(cal.get(Calendar.YEAR));
				courseComponent.setComponentType(ComponentType.valueOf(rs
						.getString(2)));
				courseComponent.setContactHours(rs.getInt(3));

				if (courseComponent.getAcademicYear() == 2013) {
					listComponent.add(courseComponent);
				}

			}
			return listComponent;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}*/

	// Returns all the professors linked to a course
/*	public ArrayList<User> loadProfessor(int crouseId) {
		ArrayList<User> listProfessor = new ArrayList<User>();
		Set<User> setProfessor = new HashSet<User>();
		SessionIdentifierGenerator gen = new SessionIdentifierGenerator();
		String sql = "SELECT Course_Intructor.ID, Instructor_Name.Achternaam, Instructor_Name.Voornaam "
				+ "FROM Course_Intructor "
				+ "JOIN Instructor_Name ON Course_Intructor.ID = Instructor_Name.ID "
				+ "WHERE Studiedeel= '"
				+ crouseId
				+ "' AND Course_Intructor.Titularis = 'Y'";

		rs = DbLinkDump.executeSqlQuery(sql);

		try {
			while (rs.next()) {
				User user = new User();
				// Initializing User object with data form the database.
				user.setFirstName(rs.getString(3).replace("'", ""));
				user.setLastName(rs.getString(2).replace("'", ""));
				user.setUserName(rs.getString(2).replace(" ", "")
						.replace("'", "").toLowerCase()
						+ "."
						+ rs.getString(3).replace(" ", "").replace("'", "")
								.toLowerCase());
				user.setEmail(rs.getString(2).toLowerCase().replace("'", "")
						.replace(" ", "")
						+ "."
						+ rs.getString(3).toLowerCase().replace("'", "")
								.replace(" ", "") + ".thisisatest@vub.ac.be");
				user.setType(UserType.ROLE_PROFESSOR);
				user.setBirthdate(new Date(1990, 02, 01));
				user.setPassword(gen.nextSessionId(256)); // Generating random
															// password. User
															// will need to
															// reset this
															// password.

				UserDao userDao = new UserDao();
				if (!setProfessor.contains(user)) {
					if (userDao.checkIfUserNameAvailable(user.getUserName())) {
						userDao.insertEnabledUser(user);
						setProfessor.add(user);
						userDao.closeDao();
					} else {
						setProfessor.add(userDao.findByUserName(user
								.getUserName()));
						userDao.closeDao();
					}
				}
				setProfessor.add(user);
			}
			listProfessor.addAll(setProfessor);

			return listProfessor;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}*/

/*	public ArrayList<User> loadAssistant(int crouseId) {
		ArrayList<User> listAssistant = new ArrayList<User>();
		Set<User> setAssistant = new HashSet<User>();
		SessionIdentifierGenerator gen = new SessionIdentifierGenerator();
		String sql = "SELECT Course_Intructor.ID, Instructor_Name.Achternaam, Instructor_Name.Voornaam "
				+ "FROM Course_Intructor "
				+ "JOIN Instructor_Name ON Course_Intructor.ID = Instructor_Name.ID "
				+ "WHERE Studiedeel= '"
				+ crouseId
				+ "' AND Course_Intructor.Titularis = 'N'";

		rs = DbLinkDump.executeSqlQuery(sql);

		try {
			while (rs.next()) {
				User user = new User();
				// Initializing User object with data form the database.
				// user.setUserID(Integer.valueOf(gen.nextSessionId(50)));
				user.setFirstName(rs.getString(3).replace("'", ""));
				user.setLastName(rs.getString(2).replace("'", ""));
				user.setUserName(rs.getString(2).replace(" ", "")
						.replace("'", "").toLowerCase()
						+ "."
						+ rs.getString(3).replace("'", "").replace(" ", "")
								.toLowerCase());
				user.setEmail(rs.getString(2).toLowerCase().replace("'", "")
						.replace(" ", "")
						+ "."
						+ rs.getString(3).toLowerCase().replace("'", "")
								.replace(" ", "") + ".thisisatest@vub.ac.be");
				user.setPassword(gen.nextSessionId(256)); // Generating random
															// password. User
															// will need to
															// reset this
															// password.
				user.setType(UserType.ROLE_ASSISTANT);
				user.setBirthdate(new Date(1990, 02, 01));

				if (!setAssistant.contains(user)) {
					// System.out.println("Usert to check: --> " + user);
					UserDao userDao = new UserDao();

					if (userDao.checkIfUserNameAvailable(user.getUserName())) {
						// System.out.println("Inserting User: " + user);
						userDao.insertEnabledUser(user);
						setAssistant.add(user);
						userDao.closeDao();
					} else {
						// System.out.println("Getting User: " +
						// user.getUserName());
						setAssistant.add(userDao.findByUserName(user
								.getUserName()));
						userDao.closeDao();
					}
				}
				setAssistant.add(user);
			}

			listAssistant.addAll(setAssistant);

			return listAssistant;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}*/

}
