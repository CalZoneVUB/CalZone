package com.vub.scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.value.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;
import org.optaplanner.core.impl.solution.Solution;

import com.vub.model.Entry;
import com.vub.model.Room;

@PlanningSolution
public class Schedular implements Solution<HardSoftLongScore> {
	
	private HardSoftLongScore score;
	
	// Problem facts
	private List<Date> startDateList;
	private List<Room> roomList;

	// Planning Entities
	private List<Entry> entryList;
	
	
	@ValueRangeProvider(id = "startDateRange")
	public List<Date> getStartDateList() {
		return startDateList;
	}
	
	@ValueRangeProvider(id = "roomRange")
	public List<Room> getRoomList() {
		return roomList;
	}

	/**
	 * The method is only used if Drools is used for score calculation. Other score directors do not use it. 
	 * 
	 * All objects returned by the getProblemFacts() method will be asserted into the Drools working memory, 
	 * so the score rules can access them. 
	 */
	@Override
	public Collection<? extends Object> getProblemFacts() {
		List<Object> facts = new ArrayList<Object>();
		
		// Do not add the planning entity's (entryList) because that will be done automatically
		return facts;
	}

	@Override
	public HardSoftLongScore getScore() {
		return score;
	}

	@Override
	public void setScore(HardSoftLongScore score) {
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

	@PlanningEntityCollectionProperty
	public List<Entry> getEntryList() {
		return entryList;
	}
}
