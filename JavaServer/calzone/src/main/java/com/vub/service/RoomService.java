package com.vub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;

import com.vub.dao.RoomDAO;
import com.vub.model.Room;

/**
 * Service class for the Room object. 
 * 
 * @author Sam
 *
 */
@Service("roomService")
public class RoomService {
	@Autowired
	RoomDAO roomDAO;
	
	@Transactional
	public void persistRoom(Room room) {
		roomDAO.persistRoom(room);
	}

	@Transactional
	public void updateRoom(Room room) {
		roomDAO.updateRoom(room);
	}

	@Transactional
	public Room findRoomById(int id) {
		return roomDAO.findRoomById(id);
	}

	@Transactional
	public void deleteRoom(Room room) {
		roomDAO.deleteRoom(room);
	}
	
	@Transactional
	public List<Room> getRooms() {
		return new ArrayList<Room>();
	}
}
