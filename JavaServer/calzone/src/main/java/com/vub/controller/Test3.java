package com.vub.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vub.dao.RoomDao;
import com.vub.datadump.ReadCSV;
import com.vub.model.Room;

@Controller 
public class Test3 {
	@RequestMapping(value = "/test3")
	public String sayHello(Model model) {
		
		ReadCSV csv = new ReadCSV();
		ArrayList<Room> roomList = csv.readRoom("Leslokalen.csv",";");
		RoomDao roomDao = new RoomDao();
		
		for (Room room : roomList) {
			roomDao.insertRoom(room);
		}

		return "hello";
	}
}
