package com.vub.datadump;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.hibernate.Hibernate;
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
				roomObj.setProjectorEquipped((1 == hasEquipment));
				hasEquipment = Integer.valueOf(room[6]);
				roomObj.setSmartBoardEquipped((1 == hasEquipment));
				hasEquipment = Integer.valueOf(room[7]);
				roomObj.setRecorderEquipped((1 == hasEquipment));
				
				Floor floorObj;
				Building buildingObj;
				Institution institutionObj;
				
				try {
					floorObj = floorService.getFloor(floor, building);
				} catch (FloorNotFoundException e) {
					try {
						buildingObj = buildingService.getBuilding(building);
					} catch (BuildingNotFoundException e1) {
						try {
							institutionObj = institutionService.getInstitution(institution);
						} catch (InstitutionNotFoundException e2) {
							institutionObj = new Institution();
							institutionObj.setName(institution);
							institutionService.createInstitution(institutionObj);
						}
						buildingObj = new Building();
						buildingObj.setName(building);
						buildingObj.setInstitution(institutionObj);
						buildingService.createBuilding(buildingObj);
					}
					floorObj = new Floor();
					floorObj.setFloor(floor);
					floorObj.setBuilding(buildingObj);
					floorService.createFloor(floorObj);
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
}

