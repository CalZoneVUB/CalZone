package com.vub.datadump;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import com.vub.exception.BuildingNotFoundException;
import com.vub.exception.FloorNotFoundException;
import com.vub.exception.InstitutionNotFoundException;
import com.vub.model.Building;
import com.vub.model.Course;
import com.vub.model.Floor;
import com.vub.model.Globals;
import com.vub.model.Institution;
import com.vub.model.Room;
import com.vub.model.SessionIdentifierGenerator;
import com.vub.service.BuildingService;
import com.vub.service.FloorService;
import com.vub.service.InstitutionService;
import com.vub.service.KeyService;
import com.vub.service.RoomService;

public class ReadCSV {

	//"Leslokalen.csv" ";"
	public void readRoom(String csvFile, String csvSplitBy) {

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		RoomService roomService = (RoomService) context.getBean("roomService");
		FloorService floorService = (FloorService) context.getBean("floorService");
		BuildingService buildingService = (BuildingService) context.getBean("buildingService");
		InstitutionService institutionService = (InstitutionService) context.getBean("institutionService");
		
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
				
				// TODO - FIX WITH NEW STRUCTURE FOR ROOMS
				// use comma as separator
				String[] room = line.split(csvSplitBy);
				Room roomObj = new Room();
				int floor = Integer.valueOf(room[2]);
				String building = room[1];
				String institution = "VUB";
				String str = room[0];
				str = str.replace(" r", "R");
				roomObj.setType(Room.RoomType.valueOf(str));
				roomObj.setName(room[3]);
				roomObj.setCapacity(Integer.valueOf(room[4]));
				int hasEquipment = Integer.valueOf(room[5]);
				roomObj.setProjectorEquipped(1 == hasEquipment);
				hasEquipment = Integer.valueOf(room[6]);
				roomObj.setSmartBoardEquipped(1 == hasEquipment);
				hasEquipment = Integer.valueOf(room[7]);
				roomObj.setRecorderEquipped(1 == hasEquipment);
				
				Floor floorObj;
				Building buildingObj;
				Institution institutionObj;
				try {
					floorObj = floorService.getFloor(floor, building, institution);
				} catch (FloorNotFoundException e) {
					try {
						institutionObj = institutionService.getInstitution(institution);
						try {
							buildingObj = buildingService.getBuilding(building, institution);
						} catch (BuildingNotFoundException e1) {
							buildingObj = new Building();
							buildingObj.setName(building);
							buildingObj.setInstitution(institutionObj);
						}
					} catch (InstitutionNotFoundException e1) {
						institutionObj = new Institution();
						institutionObj.setName(institution);
						buildingObj = new Building();
						buildingObj.setName(building);
						buildingObj.setInstitution(institutionObj);
					}
					floorObj = new Floor();
					floorObj.setFloor(floor);
					floorObj.setBuilding(buildingObj);
				}
				roomObj.setFloor(floorObj);
				roomService.createRoom(roomObj);
				
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
		
		context.close();
		
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
				// TODO - Update met toegevoegde services
			/*	course.setiD(Integer.valueOf(csvLine[0]));
				course.setDescription(csvLine[1]);*/
				
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

