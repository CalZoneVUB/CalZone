package com.vub.dao;

//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.List;

//import javax.swing.tree.RowMapper;



import org.springframework.stereotype.Component;

import com.vub.db.DbTranslate;
import com.vub.model.Room;
import com.vub.model.User;

@Component("RoomDao")
public class RoomDao {
	// Database Access Object
	
	DbTranslate db = new DbTranslate();

	// Insert room
	 public void insertRoom(Room room){
	 	db.insertRoom(room);
	 }
	 
	// List of all the rooms in database
	public List<Room> getRooms() {
		List<Room>  rooms = db.selectAllRooms();
		return rooms;
	}
	
	 
	 
}
