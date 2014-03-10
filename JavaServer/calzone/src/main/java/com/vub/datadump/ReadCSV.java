package com.vub.datadump;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import com.vub.model.Course;
import com.vub.model.Globals;
import com.vub.model.Professor;
import com.vub.model.Room;
import com.vub.model.RoomType;
import com.vub.model.SessionIdentifierGenerator;
import com.vub.model.User;

public class ReadCSV {

	//"Leslokalen.csv" ";"
	public ArrayList<Room> readRoom(String csvFile, String csvSplitBy) {

		//String csvFile = "Leslokalen.csv"; //File located in src/main/resources
		//String cvsSplitBy = ";";//seperator used in the csv
		BufferedReader br = null;
		String line = "";
		ArrayList<Room> roomList = new ArrayList<Room>();

		try {
			ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-module.xml");
			Resource resource = appContext.getResource("Leslokalen.csv");
			InputStream is = resource.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			
			br.readLine(); // Skip first line with Header			
			
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] room = line.split(csvSplitBy);
				Room roomObj = new Room();
				String str = room[0];
				str = str.replace(" r", "R");
				roomObj.setType(RoomType.valueOf(str));
				//roomObj.setName(room[0]);
				roomObj.setBuilding(room[1]);
				roomObj.setFloor(Integer.valueOf(room[2]));
				roomObj.setName(room[3]);
				//roomObj.setNumber(Integer.valueOf(room[3]));
				roomObj.setCapacity(Integer.valueOf(room[4]));
				//roomObj.setPlaces(Integer.valueOf(room[4]));
				int i = Integer.valueOf(room[5]);
				roomObj.setHasProjector(1 == i);
				i = Integer.valueOf(room[6]);
				roomObj.setHasSmartBoard(1 == i);
				i = Integer.valueOf(room[7]);
				roomObj.setHasRecorder(1 == i);
				roomObj.setInstitution("VUB");
				
				roomList.add(roomObj);
				
				if (Globals.DEBUG == 1) {System.out.println(roomObj);}

			}
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
		return roomList;
		
	}


	//readProfessor("INSTR_NAME.csv",";")
	//Reads csv file of the professors and makes user object of this type 
	public ArrayList<Professor> readProfessor(String csvFile, String csvSplitBy) {
	
	BufferedReader br = null;
	String line = "";		
	ArrayList<Professor> professorList = new ArrayList<Professor>();
	SessionIdentifierGenerator gen = new SessionIdentifierGenerator();

	try {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-module.xml");
		Resource resource = appContext.getResource(csvFile);
		InputStream is = resource.getInputStream();
		br = new BufferedReader(new InputStreamReader(is));
		br.readLine(); // Skip first line with Header
		
		
		
		while ((line = br.readLine()) != null) {

			// use comma as separator
			String[] csvLine = line.split(csvSplitBy);
			User user = new User();
			user.setFirstName(csvLine[2]);
			user.setLastName(csvLine[1]);
			user.setUserName(csvLine[1].toLowerCase() + "." + csvLine[2].toLowerCase());
			user.setEmail(csvLine[1].toLowerCase() + "." + csvLine[2].toLowerCase() + ".thisisatest@vub.ac.be");
			user.setPassword(gen.nextSessionId(256));
			user.setUserTypeName("ROLE_PROFESSOR");
			
			Professor professor = new Professor(user);
			
			professorList.add(professor);
			
			if (Globals.DEBUG == 1) {System.out.println(professor);}
		}

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	System.out.println("Done");
	return professorList;
}

	public ArrayList<Course> readCourceId(String csvFile, String csvSplitBy) {	
		BufferedReader br = null;
		String line = "";		
		ArrayList<Course> courseList = new ArrayList<Course>();
		SessionIdentifierGenerator gen = new SessionIdentifierGenerator();

		try {
			ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-module.xml");
			Resource resource = appContext.getResource(csvFile);
			InputStream is = resource.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			br.readLine(); // Skip first line with Header
			
			
			
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] csvLine = line.split(csvSplitBy);
				Course course = new Course();
				course.setiD(Integer.valueOf(csvLine[0]));
				course.setDescription(csvLine[1]);
				
				courseList.add(course);
				
				if (Globals.DEBUG == 1) {System.out.println(course);}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
		return courseList;
	}

}

