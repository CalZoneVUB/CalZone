package com.vub.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vub.exception.RoomNotFoundException;
import com.vub.model.Building;
import com.vub.model.Floor;
import com.vub.model.Institution;
import com.vub.model.Room;

public class RoomServiceTest {

	/**
	 * Creates a test room without associated Floor
	 * @return
	 */
	private Room createTestRoom() {
		Room room = new Room();
		room.setName("roomname");
		room.setCapacity(0);
		room.setDisplayName("displayname");
		room.setProjectorEquipped(true);
		room.setRecorderEquipped(true);
		room.setSmartBoardEquipped(true);
		room.setType(Room.RoomType.ComputerRoom);
		
		return room;
	}
	/**
	 * Test if a room can be successfully constructed
	 */
	@Test
	public void testRoomCreation() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		InstitutionService institutionService = (InstitutionService) context.getBean("institutionService");
		BuildingService buildingService = (BuildingService) context.getBean("buildingService");
		FloorService floorService = (FloorService) context.getBean("floorService");
		RoomService roomService = (RoomService) context.getBean("roomService");
		
		
		Room room = this.createTestRoom();
		// Create every layer one by one - because that's how it should happen
		Institution institution = new Institution();
		institution.setName("instname");
		Building building = new Building();
		building.setName("buildingname");
		Floor floor = new Floor();
		floor.setFloor(0);
		
		// Now persist each entity, and add references to each other
		building.setInstitution(institutionService.createInstitution(institution));
		floor.setBuilding(buildingService.createBuilding(building));
		room.setFloor(floorService.createFloor(floor));
		room = roomService.createRoom(room);
		// Now the real test - try to find the room in the database, and check if they are equal
		// The equality test will also check if the floor, building and institution are equal
		try {
			Room room2 = roomService.findRoomById(room.getId());
			assertEquals("Checking if the saved and the fetched room are equal", room, room2);
		} catch (RoomNotFoundException ex) {
			fail("Could not fetch room which has just been persisted to the database");
		} finally {
			// Remove everything separately, because removing a room does not result in the removal of the floor, etc
			roomService.deleteRoom(room);
			floorService.deleteFloor(floor);
			buildingService.deleteBuilding(building);
			institutionService.deleteInstitution(institution);

			context.close();
		}
	}
}
