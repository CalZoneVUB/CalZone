package com.vub.db;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vub.model.ActivationKey;
import com.vub.model.Course;
import com.vub.model.Enrollment;
import com.vub.model.Globals;
import com.vub.model.PasswordKey;
import com.vub.model.Room;
import com.vub.model.RoomType;
import com.vub.model.User;
import com.vub.model.UserType;

//import java.sql.Date;

public class DbTranslate {

	private DbLink DbLink = new DbLink();
	private ResultSet rs = null;

	public void closeDbTranslate() {
		DbLink.closeConnection();
	}

	// When the object is created, open the connection with the db.

	public DbTranslate() {
		DbLink.openConnection();
	}

	public  void showPersons() {

		rs = DbLink.executeSqlQuery("SELECT * FROM Persons");

		try {
			while (rs.next()) {
				if (Globals.DEBUG == 1) {
					System.out.println(rs.getInt(1) + " " + rs.getString(2)
							+ " " + rs.getString(3));
				}
			}
			if (Globals.DEBUG == 1)
				System.out.println("\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// CHECK

	public  boolean checkIfEmailAvailable(String email) {
		String sql = "SELECT 1 FROM Persons WHERE Email = '" + email + "';";

		rs = DbLink.executeSqlQuery(sql);

		try {
			if (!rs.isBeforeFirst()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkIfUserNameAvailable(String username) {
		String sql = "SELECT 1 FROM Users WHERE Username COLLATE latin1_general_ci = '"
				+ username + "';";
		if (Globals.DEBUG == 1) {
			System.out.println("Username to check: " + username);
		}
		rs = DbLink.executeSqlQuery(sql);

		try {
			if (!rs.isBeforeFirst()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// DELETE

	// UPGRADE NotRegisteredUser to User

	public void upgradeNotEnabledUser(User user) {
		String sqlqr = "UPDATE Users SET Enabled='1'  WHERE Username = '"
				+ user.getUserName() + "';";
		if (Globals.DEBUG == 1) {
			System.out.println(sqlqr);
		}
		DbLink.executeSql(sqlqr);
		DbLink.executeSql("UPDATE Users SET UserTypeID ='2' WHERE Username = '"
				+ user.getUserName() + "';");
	}

	// DELETE

	public void deleteKey(String keyString) {
		DbLink.executeSql("DELETE FROM KeyStrings WHERE KeyString = '"
				+ keyString + "';");
	}
	
	public void deleteEnrollment(int userID, int courseID, int academicYear) {
		DbLink.executeSql("DELETE FROM CourseEnrollments"
				+ "WHERE UserID = '"
				+ userID
				+ "' AND CourseID = '"
				+ courseID
				+ "' AND AcademicYear = '"
				+ academicYear 
				+"');");
	}

	// INSERT
	
	public void insertEnrollment(int userID, int courseID, int academicYear) {
		DbLink.executeSql("INSERT INTO CourseEnrollments (UserID, CourseID, AcademicYear)"
				+ "VALUES ('"
				+ userID
				+ "', '"
				+ courseID
				+ "', '"
				+ academicYear 
				+"');");
	}

	public void insertCourse(Course course, int academicYear) {
		// TODO CourseOffer = TypicallyOffered
		DbLink.executeSql("INSERT INTO Courses (CourseName, CourseOfferID)"
				+ "VALUES ('" + course.getDescription() + "', '1');");
		for (User u : course.getListOfProfessors()) {
			DbLink.executeSql("INSERT INTO CourseTeachers (UserID, CourseID, AcademicYear)"
					+ "VALUES ('"
					+ u.getUserID()
					+ "', '"
					+ course.getiD()
					+ "', '"
					+ academicYear 
					+"');");
		}
		for (User u : course.getListOfAssistants()) {
			DbLink.executeSql("INSERT INTO CourseTeachers (UserID, CourseID, AcademicYear)"
					+ "VALUES ('"
					+ u.getUserID()
					+ "', '"
					+ course.getiD()
					+ "', '"
					+ academicYear 
					+"');");
		}
	}

	public void insertRoom(Room room) { // TODO INSERT DisplayName
		String sqlBuilding = "INSERT IGNORE INTO Buildings (BuildingName, InstitutionID)"
				+ " VALUES ( '"
				+ room.getBuilding()
				+ "', ( SELECT InstitutionID FROM Institutions WHERE InstitutionName = '"
				+ room.getInstitution() + "')); ";
		String sqlFloor = "INSERT IGNORE INTO Floors (Floor, BuildingID)"
				+ " VALUES ( '"
				+ room.getFloor()
				+ "', ( SELECT BuildingID FROM Buildings WHERE BuildingName = '"
				+ room.getBuilding() + "')); ";

		String sqlRoom = "INSERT IGNORE INTO Rooms (Room, Capacity, RoomType, HasProjector, HasRecorder, HasSmartBoard, FloorID)"
				+ " VALUES ( '"
				+ room.getName()
				+ "', '"
				+ room.getCapacity()
				+ "', '"
				+ room.getType()
				+ "', '"
				+ room.isProjectorEquipped()
				+ "', '"
				+ room.isRecorderEquipped()
				+ "', '"
				+ room.isSmartBoardEquipped()
				+ "', ( SELECT FloorID FROM Floors JOIN Buildings "
				+ "ON Floors.BuildingID = Buildings.BuildingID "
				+ "WHERE BuildingName = '"
				+ room.getBuilding()
				+ "' AND Floor = '" + room.getFloor() + "'));";

		DbLink.executeSql(sqlBuilding);
		DbLink.executeSql(sqlFloor);
		DbLink.executeSql(sqlRoom);
	}

	public void insertKey(String keyString, Date createdOn, String userName, String keyType) {
		System.out.println("INSERT KEY: userName = "+userName);
		DbLink.executeSql("INSERT INTO KeyStrings (KeyString, CreatedOn, KeyType ,UserID) VALUES ('"
				+ keyString
				+ "', '"
				+ createdOn
				+ "', '"
				+ keyType
				+ "', (SELECT UserID FROM Users WHERE Username = '"
				+ userName + "'));");
	}

	public void insertNotEnabledUser(User user) {
		// BEGIN;
		// INSERT INTO Persons (LastName, FirstName, Email, Birthdate)
		// VALUES('test', 'test', 'test@test.com', '2013-12-08');
		// INSERT INTO Users (Username, Password, PersonID)
		// VALUES('test','test', LAST_INSERT_ID());
		// COMMIT;

		String sql1 = "INSERT INTO Persons (LastName, FirstName, Email, Birthdate)"
				+ " VALUES ('"
				+ user.getLastName()
				+ "', '"
				+ user.getFirstName()
				+ "', '"
				+ user.getEmail()
				+ "', '"
				+ user.getBirthdate() + "'); ";
		String sql2 = "INSERT INTO Users (Username, Password, PersonID, UserTypeID)"
				+ " VALUES('"
				+ user.getUserName()
				+ "', '"
				+ user.getPassword()
				+ "', LAST_INSERT_ID(),(SELECT UserTypeID FROM UserTypes WHERE UserTypeName = '"
				+ user.getType() + "'));";

		DbLink.executeSql(sql1);
		DbLink.executeSql(sql2);
	}

	public void insertEnabledUser(User user) {
		String sql1 = "INSERT INTO Persons (LastName, FirstName, Email, Birthdate)"
				+ " VALUES ('"
				+ user.getLastName()
				+ "', '"
				+ user.getFirstName()
				+ "', '"
				+ user.getEmail()
				+ "', '"
				+ user.getBirthdate() + "'); ";
		String sql2 = "INSERT INTO Users (Username, Password, PersonID, UserTypeID, Enabled)"
				+ " VALUES('"
				+ user.getUserName()
				+ "', '"
				+ user.getPassword()
				+ "', LAST_INSERT_ID(),(SELECT UserTypeID FROM UserTypes WHERE UserTypeName = '"
				+ user.getType() + "'),'1');";

		DbLink.executeSql(sql1);
		DbLink.executeSql(sql2);
	}

	// UPDATE

	// updateUser will only update Password and Language.

	public void updateUser(User user) {
		DbLink.executeSql("UPDATE Users SET Password = '"
				+ user.getPassword()
				+ "', Language = '" 
				+ user.getLanguage()
				+ "', UserTypeID = (SELECT UserTypeID FROM UserTypes WHERE UserTypeName = '"
				+ user.getUserTypeName()
				+ "')" 
				+ " WHERE Username = '" + user.getUserName() + "';");
		DbLink.executeSql("UPDATE Persons SET LastName = '"
				+ user.getLastName()
				+ "', FirstName = '"
				+ user.getFirstName()
				+ "', Email = '"
				+ user.getEmail() 
				+"' WHERE PersonID = (SELECT PersonID FROM Users WHERE UserName='"
				+ user.getUserName()
				+"');");
	}

	// SELECT
	
	public Course selectCourseByCourseID(int courseID) {
		Course course = new Course();
		ArrayList<User> professors = new ArrayList<User>();
		ArrayList<User> assistants = new ArrayList<User>();
		int teacherUserID;
		User teacher;
		
		rs = DbLink.executeSqlQuery("SELECT Courses.CourseID, Courses.CourseName, Users.UserID, Users.Username, Users.Password, Users.Language,UserTypes.UserTypeName, Persons.FirstName, Persons.LastName, Persons.Email, Persons.BirthDate "
				+ " FROM Users"
				+ " JOIN Persons ON Users.PersonID = Persons.PersonID"
				+ "	JOIN UserTypes ON Users.UserTypeID = UserTypes.UserTypeID"
				+ " JOIN CourseTeachers ON Users.UserID = CourseTeachers.UserID"
				+ " RIGHT JOIN Courses ON CourseTeachers.CourseID = Courses.CourseID"
				+ " WHERE Courses.CourseID = '"+courseID+"';");
		
		try {
			if (!rs.isBeforeFirst()) {
				return null;
			}
			while (rs.next()) {
				course.setiD(rs.getInt(1));
				course.setDescription(rs.getString(2));
				teacherUserID = rs.getInt(3);
				if (teacherUserID > 0){
					teacher = new User(teacherUserID, rs.getString(4), rs.getString(5), rs.getString(6),
							UserType.valueOf(rs.getString(7)), rs.getString(9), rs.getString(8), rs.getString(10),
									rs.getDate(11));
					if (teacher.getType() == UserType.ROLE_PROFESSOR){
						professors.add(teacher);
					}
					else {
						assistants.add(teacher);
					}
				}
			}
			course.setListOfAssistants(assistants);
			course.setListOfProfessors(professors);
			return course;
		} catch (SQLException e) {
			e.printStackTrace();
			return course;
		}
	}
	
	public ArrayList<Enrollment> selectEnrollmentsByUserID(int userID, int academicYear) {
		ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
		Enrollment enrollment;
		Course course;

		rs = DbLink.executeSqlQuery("SELECT * "
				+ " FROM CourseEnrollments"
				+ " WHERE CourseEnrollments.UserID =  '"+userID+"'");
		try {
			while (rs.next()) {
				course = new Course();
				course.setiD(rs.getInt(1));
				enrollment = new Enrollment(course, academicYear);

				if (Globals.DEBUG == 1) {
					System.out.println(enrollment);
				}

				enrollments.add(enrollment);
			}
			for (Enrollment e: enrollments){
				e.setCourse(this.selectCourseByCourseID(e.getCourse().getiD()));
			}
			return enrollments;
		} catch (SQLException e) {
			e.printStackTrace();
			return enrollments;
		}
	}

	public ActivationKey selectActivationKeyByEmail(String email) {
		ActivationKey activationkey = new ActivationKey();

		rs = DbLink
				.executeSqlQuery("SELECT KeyStrings.KeyString, KeyStrings.CreatedOn, Users.UserName"
						+ " FROM Persons"
						+ " JOIN Users ON Persons.PersonID = Users.PersonID"
						+ " JOIN KeyStrings ON KeyStrings.UserID = Users.UserID"
						+ " WHERE Persons.Email = '" + email + "'AND KeyType = 'Activation';");

		try {
			if (!rs.isBeforeFirst()) {
				if (Globals.DEBUG == 1)
					System.out
					.println("-> ! There is no activation key associated with this email address in the database !");
				return null;
			} else {
				rs.next();
				
				activationkey.setKeyString(rs.getString(1));
				activationkey.setCreatedOn(rs.getDate(2));
				activationkey.setUserName(rs.getString(3));
				
				if (Globals.DEBUG == 1)
					System.out.println(activationkey);

				return activationkey;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ActivationKey selectActivationKeyByKeyString(String keyString) {
		ActivationKey activationKey = new ActivationKey();

		rs = DbLink
				.executeSqlQuery("SELECT KeyStrings.CreatedOn, Users.Username"
						+ " FROM KeyStrings"
						+ " JOIN Users ON KeyStrings.UserID = Users.UserID"
						+ " WHERE KeyStrings.KeyString = '"
						+ keyString
						+ "' AND KeyType = 'Activation';");

		try {
			if (!rs.isBeforeFirst()) {
				if (Globals.DEBUG == 1)
					System.out
					.println("-> ! This keyString doesn't exist in the database !");
				return null;
			} else {
				rs.next();

				activationKey.setKeyString(keyString);
				activationKey.setCreatedOn(rs.getDate(1));
				activationKey.setUserName(rs.getString(2));

				if (Globals.DEBUG == 1)
					System.out.println(activationKey);

				return activationKey;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public PasswordKey selectPasswordKeyByKeyString(String keyString) {
		PasswordKey passwordKey = new PasswordKey();

		rs = DbLink
				.executeSqlQuery("SELECT KeyStrings.CreatedOn, Users.Username"
						+ " FROM KeyStrings"
						+ " JOIN Users ON KeyStrings.UserID = Users.UserID"
						+ " WHERE KeyStrings.KeyString = '"
						+ keyString
						+ "' AND KeyType = 'password';");

		try {
			if (!rs.isBeforeFirst()) {
				if (Globals.DEBUG == 1)
					System.out
					.println("-> ! This keyString doesn't exist in the database !");
				return null;
			} else {
				rs.next();

				passwordKey.setKeyString(keyString);
				passwordKey.setCreatedOn(rs.getDate(1));
				passwordKey.setUserName(rs.getString(2));

				if (Globals.DEBUG == 1)
					System.out.println(passwordKey);

				return passwordKey;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public PasswordKey selectPasswordKeyByEmail(String email) {
		PasswordKey passwordKey = new PasswordKey();

		rs = DbLink
				.executeSqlQuery("SELECT KeyStrings.KeyString, KeyStrings.CreatedOn, Users.Username"
						+ " FROM KeyStrings"
						+ " JOIN Users ON KeyStrings.UserID = Users.UserID"
						+ " JOIN Persons ON Users.PersonID = Persons.PersonID"
						+ " WHERE Persons.Email = '"
						+ email
						+ "' AND KeyType = 'password';");

		try {
			if (!rs.isBeforeFirst()) {
				if (Globals.DEBUG == 1)
					System.out
					.println("-> ! This keyString doesn't exist in the database !");
				return null;
			} else {
				rs.next();

				passwordKey.setKeyString(rs.getString(1));
				passwordKey.setCreatedOn(rs.getDate(2));
				passwordKey.setUserName(rs.getString(3));

				if (Globals.DEBUG == 1)
					System.out.println(passwordKey);

				return passwordKey;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public  ArrayList<Course> selectAllCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();
		Course course = new Course();
		ArrayList<User> professors = new ArrayList<User>();
		ArrayList<User> assistants = new ArrayList<User>();
		int teacherUserID;
		User teacher;
		String courseName;
		
		rs = DbLink.executeSqlQuery("SELECT Courses.CourseID, Courses.CourseName, Users.UserID, Users.Username, Users.Password, Users.Language,UserTypes.UserTypeName, Persons.FirstName, Persons.LastName, Persons.Email, Persons.BirthDate "
				+ " FROM Users"
				+ " JOIN Persons ON Users.PersonID = Persons.PersonID"
				+ "	JOIN UserTypes ON Users.UserTypeID = UserTypes.UserTypeID"
				+ " JOIN CourseTeachers ON Users.UserID = CourseTeachers.UserID"
				+ " RIGHT JOIN Courses ON CourseTeachers.CourseID = Courses.CourseID;");

		// VOOR TE TESTEN: ALLEEN MET EERSTE 1000 Courses van ResultSet !
		int lastCourseID = 0;
		int tempID = 0;
		
		try {
			while (rs.next() && lastCourseID<1000) {
				tempID = rs.getInt(1);
				courseName = rs.getString(2);
				teacherUserID = rs.getInt(3);
				if (tempID == lastCourseID){
					tempID = courses.size()-1;
					course = courses.get(tempID);
					courses.remove(tempID);
				}
				else {
					lastCourseID = tempID;
					course = new Course();
					course.setiD(lastCourseID);
					course.setDescription(courseName);
				}
				if (teacherUserID > 0){
					assistants = course.getListOfAssistants();
					professors = course.getListOfProfessors();
					teacher = new User(teacherUserID, rs.getString(4), rs.getString(5), rs.getString(6),
							UserType.valueOf(rs.getString(7)), rs.getString(9), rs.getString(8), rs.getString(10),
									rs.getDate(11));
					if (teacher.getType() == UserType.ROLE_PROFESSOR){
						professors.add(teacher);
					}
					else {
						assistants.add(teacher);
					}
					course.setListOfAssistants(assistants);
					course.setListOfProfessors(professors);
				}
				courses.add(course);
			}
			return courses;
		} catch (SQLException e) {
			e.printStackTrace();
			return courses;
		}
	}

	public ArrayList<Room> selectAllRooms() { // DOESN'T SET DisplayName and
		// RoomEquipment !!
		ArrayList<Room> rooms = new ArrayList<Room>();
		rs = DbLink
				.executeSqlQuery("SELECT Rooms.RoomID, Rooms.Room, Rooms.Capacity, Rooms.RoomType, Floors.Floor, Buildings.BuildingName, Institutions.InstitutionName, Rooms.HasProjector, Rooms.HasRecorder, Rooms.HasSmartBoard "
						+ " FROM Rooms"
						+ " JOIN Floors ON Rooms.FloorID = Floors.FloorID"
						+ " JOIN Buildings ON Floors.BuildingID = Buildings.BuildingID"
						+ " JOIN Institutions ON Buildings.InstitutionID = Institutions.InstitutionID;");
		// System.out.println(rs);
		try {
			while (rs.next()) {
				Room room2 = new Room();
				room2.setRoomId(rs.getInt(1));
				room2.setName(rs.getString(2));
				room2.setCapacity(rs.getInt(3));
				room2.setType(RoomType.valueOf(rs.getString(4)));
				room2.setFloor(rs.getInt(5));
				room2.setBuilding(rs.getString(6));
				room2.setInstitution(rs.getString(7));
				room2.setHasProjector(rs.getBoolean(8));
				room2.setHasRecorder(rs.getBoolean(9));
				room2.setHasSmartBoard(rs.getBoolean(10));
				room2.setDisplayName(null);

				if (Globals.DEBUG == 1) {
					System.out.println(room2);
				}

				rooms.add(room2);
			}
			return rooms;
		} catch (SQLException e) {
			e.printStackTrace();
			return rooms;
		}
	}

	public  ArrayList<User> selectAllUsers() {
		ArrayList<User> users = new ArrayList<User>();
		User user;

		rs = DbLink
				.executeSqlQuery("SELECT Users.Username, Users.Password, Users.Language, UserTypes.UserTypeName, Persons.LastName, Persons.FirstName, Persons.Email, Persons.BirthDate, Users.UserID"
						+ " FROM Persons"
						+ " JOIN Users ON Persons.PersonID = Users.PersonID"
						+ " JOIN UserTypes ON Users.UserTypeID = UserTypes.UserTypeID;");

		try {
			while (rs.next()) {
				user = new User();
				user.setUserName(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setLanguage(rs.getString(3));
				user.setUserTypeName(rs.getString(4));
				user.setLastName(rs.getString(5));
				user.setFirstName(rs.getString(6));
				user.setEmail(rs.getString(7));
				user.setBirthdate(rs.getDate(8));
				user.setUserID(rs.getInt(9));

				if (Globals.DEBUG == 1)
					System.out.println(user);

				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return users;
		}
	}

	public  User selectUserByEmail(String email) {
		User user = new User();

		rs = DbLink
				.executeSqlQuery("SELECT Users.Username, Users.Password, Users.Language, UserTypes.UserTypeName, Persons.LastName, Persons.FirstName, Persons.BirthDate, Users.UserID"
						+ " FROM Persons"
						+ " JOIN Users ON Persons.PersonID = Users.PersonID"
						+ " JOIN UserTypes ON Users.UserTypeID = UserTypes.UserTypeID"
						+ " WHERE Persons.Email = '" + email + "';");

		try {
			if (!rs.isBeforeFirst()) {
				System.out
				.println("-> ! This username doesn't exist in the database !");
				return null;
			} else {
				rs.next();

				user.setUserName(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setLanguage(rs.getString(3));
				user.setUserTypeName(rs.getString(4));
				user.setLastName(rs.getString(5));
				user.setFirstName(rs.getString(6));
				user.setEmail(email);
				user.setBirthdate(rs.getDate(7));
				user.setUserID(rs.getInt(8));

				if (Globals.DEBUG == 1)
					System.out.println(user);

				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public  User selectUserByUsername(String username) {
		User user = new User();

		rs = DbLink
				.executeSqlQuery("SELECT Users.Password, Users.Language, UserTypes.UserTypeName, Persons.LastName, Persons.FirstName, Persons.Email, Persons.BirthDate, Users.UserID"
						+ " FROM Persons"
						+ " JOIN Users ON Persons.PersonID = Users.PersonID"
						+ " JOIN UserTypes ON Users.UserTypeID = UserTypes.UserTypeID"
						+ " WHERE Users.Username = '" + username + "';");

		try {
			if (!rs.isBeforeFirst()) {
				System.out
				.println("-> ! This username doesn't exist in the database or isn't enabled !");

				return null;
			} else {
				rs.next();

				user.setUserName(username);
				user.setPassword(rs.getString(1));
				user.setLanguage(rs.getString(2));
				user.setUserTypeName(rs.getString(3));
				user.setLastName(rs.getString(4));
				user.setFirstName(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setBirthdate(rs.getDate(7));
				user.setUserID(rs.getInt(8));

				// if (Globals.DEBUG == 1)
				// System.out.println(user);

				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public  void selectUserByPersonID(int PersonID) {
		rs = DbLink.executeSqlQuery("SELECT * FROM Users WHERE PersonID = "
				+ PersonID + ";");

		if (Globals.DEBUG == 1)
			System.out.println("\nSelecting user with PersonID = " + PersonID);

		try {
			// test : System.out.println("Test rs.isBeforeFirst() = "+
			// rs.isBeforeFirst());
			if (!rs.isBeforeFirst()) {
				System.out
				.println("-> ! This person is not a registered user !");
			} else {
				while (rs.next()) {
					if (Globals.DEBUG == 1)
						System.out.println("-> " + rs.getString(2));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public  void addActivationKey(String KeyString, int UserID) {
		DbLink.executeSql("INSERT INTO ActivationKeys (`KeyString`, `CreatedOn`, `UserID`) VALUES ('"
				+ KeyString + "', NOW() , '" + UserID + "');");
	}

	public  void addUser(String Username, String Password, int PersonID) {
		DbLink.executeSql("INSERT INTO Users (`Username`, `Password`, `PersonID`) VALUES ('"
				+ Username + "',  '" + Password + "',  '" + PersonID + "');");
	}

	public  void addPerson(String LastName, String FirstName,
			String Email, String BirthDate) {
		DbLink.executeSql("INSERT INTO Persons (`LastName`, `FirstName`, `Email`, `BirthDate`) VALUES ('"
				+ LastName
				+ "',  '"
				+ FirstName
				+ "',  '"
				+ Email
				+ "',  '"
				+ BirthDate + "');");
	}

	public  void addUserType(String UserTypeName, int Permission) {
		DbLink.executeSql("INSERT INTO UserTypes (`UserTypeName` ,`Permission`) VALUES ('"
				+ UserTypeName + "',  '" + Permission + "');");
	}

	public  void addRoomEquipment(String RoomEquipmentDescription) {
		DbLink.executeSql("INSERT INTO RoomEquipments (`RoomEquipmentDescription`) VALUES ('"
				+ RoomEquipmentDescription + "');");
	}

	public  void addInstitution(String InstitutionName) {
		DbLink.executeSql("INSERT INTO Institutions (`InstitutionName`) VALUES ('"
				+ InstitutionName + "');");
	}

	public  void fillPersons() {
		addPerson("Suarez Groen", "Fernando",
				"Fernando.Suarez.Groen@vub.ac.be", "1993-01-01");
		addPerson("Coppens", "Youri", "Youri.Coppens@vub.ac.be", "1993-01-01");
		addPerson("Carraggi", "Nicolas", "Nicolas.Carraggi@vub.ac.be",
				"1993-01-01");
		addPerson("Witters", "Tim", "Tim.Witters@vub.ac.be", "1993-01-01");
		addPerson("Meiresone", "Pieter", "Pieter.Meiresone@vub.ac.be",
				"1993-01-01");
		addPerson("Van den Vonder", "Sam", "Sam.Van.den.Vonder@vub.ac.be",
				"1993-01-01");
		addPerson("Gaethofs", "Christophe", "Christophe.Gaethofs@vub.ac.be",
				"1993-01-01");
		// addPerson("Test","Test","Nicolas.Carraggi@vub.ac.be", "test");
	}

	public  void fillUserTypes() {
		addUserType("ROLE_STUDENT", 0);
		addUserType("ROLE_ASSISTANT", 1);
		addUserType("ROLE_PROFESSOR", 2);
		addUserType("ROLE_ADMIN", 3);
	}

	public  void fillRoomEquipments() {
		addRoomEquipment("Projector");
		addRoomEquipment("Recorder");
		addRoomEquipment("SmartBoard");
	}

	public  void createDb() { // TODO: UPDATE !!!!!!!!!!!!!!!!!!
		/*
		 * 
		 * Users
		 */
		String tablePersons = "CREATE TABLE Persons ("
				+ " PersonID int NOT NULL AUTO_INCREMENT,"
				+ " LastName varchar(255) NOT NULL,"
				+ " FirstName varchar(255) NOT NULL,"
				+ " Email varchar(255) NOT NULL UNIQUE,"
				+ " BirthDate date NOT NULL," + " PRIMARY KEY (PersonID));";
		String tableUsers = "CREATE TABLE Users ("
				+ " UserID int NOT NULL AUTO_INCREMENT,"
				+ " Username varchar(255) NOT NULL UNIQUE,"
				+ " Password varchar(255) NOT NULL,"
				+ " Language varchar(255) NOT NULL DEFAULT 'English',"
				+ " UserTypeID int NOT NULL DEFAULT 1,"
				+ " PersonID int NOT NULL UNIQUE,"
				+ " Enabled bit DEFAULT 0,"
				+ " PRIMARY KEY (UserID),"
				+ " FOREIGN KEY (PersonID) REFERENCES Persons(PersonID),"
				+ " FOREIGN KEY (UserTypeID) REFERENCES UserTypes(UserTypeID));";
		String tableActivationKeys = "CREATE TABLE ActivationKeys ("
				+ " ActivationKeyID int NOT NULL AUTO_INCREMENT,"
				+ " KeyString varchar(255) NOT NULL UNIQUE,"
				+ " CreatedOn datetime NOT NULL," + " UserID int NOT NULL,"
				+ " PRIMARY KEY (ActivationKeyID),"
				+ " FOREIGN KEY (UserID) REFERENCES Users(UserID));";
		String tableUserTypes = "CREATE TABLE UserTypes ("
				+ " UserTypeID int NOT NULL AUTO_INCREMENT,"
				+ " UserTypeName varchar(255) NOT NULL UNIQUE,"
				+ " Permission int NOT NULL," + " PRIMARY KEY (UserTypeID));";
		/*
		 * 
		 * Institutions and Faculties
		 */
		String tableInstitutions = "CREATE TABLE Institutions ("
				+ " InstitutionID int NOT NULL AUTO_INCREMENT,"
				+ " InstitutionName varchar(255) NOT NULL UNIQUE,"
				+ " PRIMARY KEY (InstitutionID));";
		String tableFaculties = "CREATE TABLE Faculties ("
				+ " FacultyID int NOT NULL AUTO_INCREMENT,"
				+ " FacultyName varchar(255) NOT NULL UNIQUE,"
				+ " InstitutionID int NOT NULL,"
				+ " PRIMARY KEY (FacultyID),"
				+ " FOREIGN KEY (InstitutionID) REFERENCES Institutions(InstitutionID));";
		/*
		 * 
		 * Courses
		 */
		String tableCourseOffers = "CREATE TABLE CourseOffers ("
				+ " CourseOfferID int NOT NULL AUTO_INCREMENT,"
				+ " CourseOfferDescription varchar(255) NOT NULL UNIQUE,"
				+ " PRIMARY KEY (CourseOfferID));";
		String tableCourses = "CREATE TABLE Courses ("
				+ " CourseID int NOT NULL AUTO_INCREMENT,"
				+ " CourseDescription varchar(255) NOT NULL UNIQUE,"
				+ " CourseOfferID int NOT NULL,"
				+ " FacultyID int NOT NULL,"
				+ " PRIMARY KEY (CourseID),"
				+ " FOREIGN KEY (CourseOfferID) REFERENCES CourseOffers(CourseOfferID),"
				+ " FOREIGN KEY (FacultyID) REFERENCES Faculties(FacultyID));";
		String tableCourseTeachers = "CREATE TABLE CourseTeachers ("
				+ " CourseTeacherID int NOT NULL AUTO_INCREMENT,"
				+ " CourseID int NOT NULL," + " UserID int NOT NULL,"
				+ " AcademicYear int NOT NULL,"
				+ " PRIMARY KEY (CourseTeacherID),"
				+ " FOREIGN KEY (CourseID) REFERENCES Courses(CourseID),"
				+ " FOREIGN KEY (UserID) REFERENCES Users(UserID),"
				+ " UNIQUE KEY (RoomID,RoomEquipmentID, AcademicYear));";
		String tableCourseComponents = "CREATE TABLE CourseComponents ("
				+ " CourseComponentID int NOT NULL AUTO_INCREMENT,"
				+ " CourseComponentType varchar(255) NOT NULL," // ENUM ? ( WPO
				// HOC EXM ZLF )
				+ " ContactHours int NOT NULL," + " AcademicYear int NOT NULL,"
				+ " CourseID int NOT NULL,"
				+ " PRIMARY KEY (CourseComponentID),"
				+ " FOREIGN KEY (CourseID) REFERENCES Courses(CourseID));";
		/*
		 * 
		 * Rooms
		 */
		String tableBuildings = "CREATE TABLE Buildings ("
				+ " BuildingID int NOT NULL AUTO_INCREMENT,"
				+ " BuildingName varchar(255) NOT NULL UNIQUE,"
				+ " InstitutionID int NOT NULL,"
				+ " PRIMARY KEY (BuildingID),"
				+ " FOREIGN KEY (InstitutionID) REFERENCES Institutions(InstitutionID));";
		String tableFloors = "CREATE TABLE Floors ("
				+ " FloorID int NOT NULL AUTO_INCREMENT,"
				+ " Floor int NOT NULL,"
				+ " FloorName varchar(255) NOT NULL UNIQUE,"
				+ " BuildingID int NOT NULL," + " PRIMARY KEY (FloorID),"
				+ " FOREIGN KEY (BuildingID) REFERENCES Buildings(BuildingID),"
				+ " UNIQUE KEY (Floor,BuildingID));";
		String tableRooms = "CREATE TABLE Rooms ("
				+ " RoomID int NOT NULL AUTO_INCREMENT,"
				+ " Room int NOT NULL,"
				+ " RoomName varchar(255) NOT NULL UNIQUE,"
				+ " Places int NOT NULL," + " RoomType varchar(255) NOT NULL,"
				+ " FloorID int NOT NULL," + " PRIMARY KEY (RoomID),"
				+ " FOREIGN KEY (FloorID) REFERENCES Floors(FloorID),"
				+ " UNIQUE KEY (Room,FloorID));";
		String tableRoomEquipments = "CREATE TABLE RoomEquipments ("
				+ " RoomEquipmentID int NOT NULL AUTO_INCREMENT,"
				+ " RoomEquipmentDescription varchar(255) NOT NULL UNIQUE,"
				+ " PRIMARY KEY (RoomEquipmentID));";
		String tableRoomHasEquipment = "CREATE TABLE RoomHasEquipment ("
				+ " RoomHasEquipmentID int NOT NULL AUTO_INCREMENT,"
				+ " RoomID int NOT NULL,"
				+ " RoomEquipmentID int NOT NULL,"
				+ " PRIMARY KEY (RoomHasEquipmentID),"
				+ " FOREIGN KEY (RoomID) REFERENCES Rooms(RoomID),"
				+ " FOREIGN KEY (RoomEquipmentID) REFERENCES RoomEquipments(RoomEquipmentID),"
				+ " UNIQUE KEY (RoomID,RoomEquipmentID));";

		DbLink.executeSql(tableUserTypes);
		DbLink.executeSql(tablePersons);
		DbLink.executeSql(tableUsers);
		DbLink.executeSql(tableActivationKeys);
		DbLink.executeSql(tableInstitutions);
		DbLink.executeSql(tableFaculties);
		DbLink.executeSql(tableCourseOffers);
		DbLink.executeSql(tableCourses);
		DbLink.executeSql(tableCourseTeachers);
		DbLink.executeSql(tableCourseComponents);
		DbLink.executeSql(tableBuildings);
		DbLink.executeSql(tableFloors);
		DbLink.executeSql(tableRooms);
		DbLink.executeSql(tableRoomEquipments);
		DbLink.executeSql(tableRoomHasEquipment);
	}

	public  void eraseDb() { // moet uitgebreid worden met ALLE tables !
		DbLink.executeSql("DROP TABLE Users, ActivationKeys, Persons, UserTypes");
	}

	public  void freshDb() {
		// This method will make clean Db with only the UserTypes and
		// RoomEquipment.
		eraseDb();
		createDb();
		fillUserTypes();
		fillRoomEquipments();
	}

	public  void main(String[] args) {
		
	}
}