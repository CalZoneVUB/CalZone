package com.vub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

import com.vub.model.Room;
import com.vub.repository.RoomRepository;

/**
 * Service class for the Room object. 
 * 
 * @author Sam
 *
 */
@Service("roomService")
public class RoomService {
	@Autowired
	RoomRepository roomRepository;
	
	@Transactional
	public void createRoom(Room room) {
		roomRepository.save(room);
	}

	@Transactional
	public void updateRoom(Room room) {
		roomRepository.save(room);
	}

	@Transactional
	public Room findRoomById(long id) {
		return roomRepository.findOne(id);
	}

	@Transactional
	public void deleteRoom(Room room) {
		roomRepository.delete(room);
	}
	
	@Transactional
	public List<Room> getRooms() {
		return roomRepository.findAll();
	}
}
