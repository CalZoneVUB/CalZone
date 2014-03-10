package com.vub.dao;

//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import javax.swing.tree.RowMapper;




import org.springframework.stereotype.Component;

import com.vub.db.DbTranslate;
import com.vub.model.Room;

@Component("RoomDao")
public class RoomDao {
	// Database Access Object
	
	DbTranslate db = new DbTranslate();

	// Insert room
	 public void insertRoom(Room room){
	 	db.insertRoom(room);
	 }
	 
	// List of all the rooms in database
	public ArrayList<Room> getRooms() {
		ArrayList<Room>  rooms = db.selectAllRooms();
		return rooms;
	}
	
	public void closeDao() {
		db.closeDbTranslate();
	}
	 
	 
}
