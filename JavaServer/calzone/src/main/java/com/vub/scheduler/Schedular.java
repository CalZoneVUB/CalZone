package com.vub.scheduler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.value.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.solution.Solution;

import com.vub.model.Entry;
import com.vub.model.Room;
import com.vub.model.Room.RoomType;

@PlanningSolution
public class Schedular implements Solution<HardSoftScore> {
	
	private HardSoftScore score;
	
	// Problem facts
	private List<Date> startDateList;
	private List<Room> roomList;
	
	// Planning Entities
	private List<Entry> entryList;
	
	@ValueRangeProvider(id = "startDateRange")
	public List<Date> getStartDateList() {
		List<Date> dateList = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 3, 24, 8, 0);
		dateList.add(cal.getTime());
		cal.set(2014, 3, 24, 10, 0);
		dateList.add(cal.getTime());
		cal.set(2014, 3, 24, 13, 0);
		dateList.add(cal.getTime());		
		cal.set(2014, 3, 24, 15, 0);
		dateList.add(cal.getTime());
		
		return dateList;
	}
	
	@ValueRangeProvider(id = "roomRange")
	public List<Room> getRoomList() {
		List<Room> roomList = new ArrayList<Room>();
		Room room = new Room();
		room.setCapacity(30);
		room.setProjectorEquipped(false);
		room.setType(RoomType.ClassRoom);
		roomList.add(room);
		return roomList;
	}

	public Collection<? extends Object> getProblemFacts() {
		List<Object> facts = new ArrayList<Object>();
		
		return facts;
	}

	public HardSoftScore getScore() {
		return score;
	}

	public void setScore(HardSoftScore score) {
		this.score = score;
	}

	public void setStartDateList(List<Date> startDateList) {
		this.startDateList = startDateList;
	}

	public void setRoomList(List<Room> roomList) {
		this.roomList = roomList;
	}

	public void setEntryList(List<Entry> entryList) {
		this.entryList = entryList;
	}
}
