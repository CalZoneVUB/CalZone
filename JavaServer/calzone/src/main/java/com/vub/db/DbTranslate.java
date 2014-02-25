package com.vub.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.vub.model.User;
import com.vub.model.ActivationKey;

public class DbTranslate {
	
	private static DbLink DbLink;
	private static ResultSet rs = null;

	// When the object is created, open the connection with the db.

	public DbTranslate() {
		DbLink.openConnection();
	}

	public static void showPersons() {

		rs = DbLink.executeSqlQuery("SELECT * FROM Persons");

		try {
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " "
						+ rs.getString(3));
			}
			System.out.println("\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// CHECK

	public static boolean checkIfEmailAvailable(String email) {
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
	
	public static boolean checkIfUserNameAvailable(String username) {
		String sql = "SELECT 1 FROM Users WHERE Username COLLATE latin1_general_ci = '" + username + "';";
		
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

	public static void upgradeNotEnabledUser(User user) {
		DbLink.executeSql("UPDATE Users SET Enabled=1 WHERE Username = '"+ user.getUserName() +"';");
	}

	// DELETE

	public static void deleteActivationKey(ActivationKey activationKey) {
		DbLink.executeSql("DELETE FROM ActivationKeys WHERE KeyString = '"
				+ activationKey.getKeyString() + "';");
	}

	// INSERT

	public static void insertActivationKey(ActivationKey activationKey) {
		DbLink.executeSql("INSERT INTO ActivationKeys (`KeyString`, `CreatedOn`, `UserID`) VALUES ('"
				+ activationKey.getKeyString()
				+ "', '"
				+ activationKey.getCreatedOn()
				+ "' , (SELECT UserID FROM Users WHERE Username = '"
				+ activationKey.getUserName() + "'));");
	}

	public static void insertNotEnabledUser(User user) {
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
		String sql2 = "INSERT INTO Users (Username, Password, PersonID)"
				+ " VALUES('"
				+ user.getUserName()
				+ "', '"
				+ user.getPassword() + "', LAST_INSERT_ID());";

		DbLink.executeSql(sql1);
		DbLink.executeSql(sql2);
	}

	// UPDATE

	// updateUser will only update Password and Language.

	public static void updateUser(User user) {
		DbLink.executeSql("UPDATE Users" + " SET Password = '"
				+ user.getPassword() + "', Language = '" + user.getLanguage()
				+ "'" + " WHERE Username = '" + user.getUserName() + "';");
	}

	// SELECT
	
	public static ActivationKey selectActivationKeyByEmail(String email) {
		ActivationKey activationkey = new ActivationKey();

		rs = DbLink
				.executeSqlQuery("SELECT ActivationKeys.KeyString, ActivationKeys.CreatedOn, Users.UserName"
						+ " FROM Persons"
						+ " JOIN Users ON Persons.PersonID = Users.PersonID"
						+ " JOIN ActivationKeys ON ActivationKeys.UserID = Users.UserID"
						+ " WHERE Persons.Email = '" + email + "';");

		try {
			if (!rs.isBeforeFirst()) {
				System.out.println("-> ! There is no activation key associated with this email address in the database !");
				return null;
			} else {
				rs.next();

				activationkey.setKeyString(rs.getString(1));
				activationkey.setCreatedOn(rs.getDate(2));
				activationkey.setUserName(rs.getString(3));
				
				System.out.println(activationkey);

				return activationkey;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ActivationKey selectUserByActivationKey(String keyString) {
		ActivationKey activationKey = new ActivationKey();

		rs = DbLink
				.executeSqlQuery("SELECT ActivationKeys.CreatedOn, Users.Username"
						+ " FROM ActivationKeys"
						+ " JOIN Users ON ActivationKeys.UserID = Users.UserID"
						+ " WHERE ActivationKeys.KeyString = '"
						+ keyString
						+ "';");

		try {
			if (!rs.isBeforeFirst()) {
				System.out
						.println("-> ! This keyString doesn't exist in the database !");
				return null;
			} else {
				rs.next();

				activationKey.setKeyString(keyString);
				activationKey.setCreatedOn(rs.getDate(1));
				activationKey.setUserName(rs.getString(2));

				System.out.println(activationKey);

				return activationKey;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<User> selectAllUsers() {
		List<User> users = new ArrayList<User>();
		User user;

		rs = DbLink
				.executeSqlQuery("SELECT Users.Username, Users.Password, Users.Language, UserTypes.UserTypeName, Persons.LastName, Persons.FirstName, Persons.Email, Persons.BirthDate"
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

				System.out.println(user);

				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return users;
		}
	}

	public static User selectUserByEmail(String email) {
		User user = new User();

		rs = DbLink
				.executeSqlQuery("SELECT Users.Username, Users.Password, Users.Language, UserTypes.UserTypeName, Persons.LastName, Persons.FirstName, Persons.BirthDate"
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

				System.out.println(user);

				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public static User selectUserByUsername(String username) {
		User user = new User();

		rs = DbLink
				.executeSqlQuery("SELECT Users.Password, Users.Language, UserTypes.UserTypeName, Persons.LastName, Persons.FirstName, Persons.Email, Persons.BirthDate"
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

				System.out.println(user);

				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void selectUserByPersonID(int PersonID) {
		rs = DbLink.executeSqlQuery("SELECT * FROM Users WHERE PersonID = "
				+ PersonID + ";");

		System.out.println("\nSelecting user with PersonID = " + PersonID);

		try {
			// test : System.out.println("Test rs.isBeforeFirst() = "+
			// rs.isBeforeFirst());
			if (!rs.isBeforeFirst()) {
				System.out
						.println("-> ! This person is not a registered user !");
			} else {
				while (rs.next()) {
					System.out.println("-> " + rs.getString(2));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addActivationKey(String KeyString,
			int UserID) {
		DbLink.executeSql("INSERT INTO ActivationKeys (`KeyString`, `CreatedOn`, `UserID`) VALUES ('"
				+ KeyString + "', NOW() , '" + UserID + "');");
	}

	public static void addUser(String Username, String Password, int PersonID) {
		DbLink.executeSql("INSERT INTO Users (`Username`, `Password`, `PersonID`) VALUES ('"
				+ Username + "',  '" + Password + "',  '" + PersonID + "');");
	}

	public static void addPerson(String LastName, String FirstName,
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

	public static void addUserType(String UserTypeName, int Permission) {
		DbLink.executeSql("INSERT INTO UserTypes (`UserTypeName` ,`Permission`) VALUES ('"
				+ UserTypeName + "',  '" + Permission + "');");
	}

	public static void fillPersons() {
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

	public static void fillUserTypes() {
		addUserType("Extern", 0);
		addUserType("Student", 1);
		addUserType("Assistent", 2);
		addUserType("Professor", 3);
		addUserType("Admin", 4);
	}

	public static void createDb() {
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
				+ " CreatedOn datetime NOT NULL,"
				+ " UserID int NOT NULL,"
				+ " PRIMARY KEY (ActivationKeyID),"
				+ " FOREIGN KEY (UserID) REFERENCES Users(UserID));";
		String tableUserTypes = "CREATE TABLE UserTypes ("
				+ " UserTypeID int NOT NULL AUTO_INCREMENT,"
				+ " UserTypeName varchar(255) NOT NULL UNIQUE,"
				+ " Permission int NOT NULL," + " PRIMARY KEY (UserTypeID));";
		DbLink.executeSql(tableUserTypes);
		DbLink.executeSql(tablePersons);
		DbLink.executeSql(tableUsers);
		DbLink.executeSql(tableActivationKeys);
	}

	public static void eraseDb() {
		DbLink.executeSql("DROP TABLE Users, ActivationKeys, Persons, UserTypes");
	}
	
	
	public static void freshDb(){
		// This method will make clean Db with only the UserTypes.
		eraseDb();
		createDb();
		fillUserTypes();
	}
	
	public static void main(String[] args) {
		
//		User user = new User();
//		user.setUserName("ncarragg");
//		Session session1 = new Session(user);
//		insertSession(session1);
//		Session session2 = new Session(user);
//		insertSession(session2);
//		Session session3 = new Session(user);
//		insertSession(session3);
//		System.out.println(session2);
//		deleteSession(session2);
//		deleteAllSessions(session1);
		
//		// DbLink.openConnection();
//
//		// System.out.println("\\\\ CreateDb() // \n");
//		// createDb();
//		// System.out.println("\\\\ fillUserTypes() // \n");
//		// fillUserTypes();
//		// System.out.println("\\\\ fillPersons() // \n");
//		// fillPersons();
//		// System.out.println("\\\\ showPersons() // \n");
//		// showPersons();
//		// System.out.println("\\\\ addNotRegisteredUser() // \n");
//		// addNotRegisteredUser("timbo", "test", 4);
//		// System.out.println("\\\\ addActivationKey() // \n");
//		// addActivationKey("key123", 1);
//		// System.out.println("\\\\ addUser() // \n");
//		// addUser("ncarragg","test",3);
//		// System.out.println("\\\\ addUser() // \n");
//		// addUser("ycoppens","test",2);
//
//		List<User> users = selectAllUsers();
//
//		DbLink.openConnection();
		
//		System.out.println("\\\\ CreateDb() // \n");
//		createDb();
//		System.out.println("\\\\ fillUserTypes() // \n");
//		fillUserTypes();
//		System.out.println("\\\\ fillPersons() // \n");
//		fillPersons();
//		System.out.println("\\\\ showPersons() // \n");
//		showPersons();
//		System.out.println("\\\\ addNotRegisteredUser() // \n");
//		addNotRegisteredUser("timbo", "test", 4);
//		System.out.println("\\\\ addActivationKey() // \n");
//		addActivationKey("key123", 1);
//		System.out.println("\\\\ addUser() // \n");
//		addUser("ncarragg","test",3);
//		System.out.println("\\\\ addUser() // \n");
//		addUser("ycoppens","test",2);
		
//		List<User> users = selectAllUsers();
//		
//		DbLink.openConnection();
//		
//		User user = selectUserByUsername("ncarragg");
//		
//		user.setPassword("newpass");
//		
//		updateUser(user);
//		
//		users = selectAllUsers();
//		
//		System.out.println("??? -> Is 'ncarragg' available? "+ checkIfUserNameAvailable("ncarragg"));
//		System.out.println("??? -> Is 'timbo' available? "+ checkIfUserNameAvailable("timbo"));
//		System.out.println("??? -> Is 'hahaha' available? "+ checkIfUserNameAvailable("hahaha"));		
//		
//		ActivationKey activationKey= new ActivationKey();
//		activationKey.setKeyString("haha");
//		activationKey.setUserName("timbo");
//		activationKey.setCreatedOn(Date.valueOf("2013-12-08"));
//		insertActivationKey(activationKey);
//		deleteActivationKey(activationKey);
//		
//		selectUserByActivationKey("testKey");
//		
//		DbLink.closeConnection();
				
//		eraseDb();
//		createDb();
//		System.out.println("\\\\ fillUserTypes() // \n");
//		fillUserTypes();
		
	}
}
