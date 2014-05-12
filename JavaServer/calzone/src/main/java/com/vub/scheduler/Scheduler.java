package com.vub.scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.value.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;
import org.optaplanner.core.impl.solution.Solution;

import com.vub.model.Entry;
import com.vub.model.Room;
import com.vub.model.Traject;
import com.vub.scheduler.constraints.ConstraintViolation;

@PlanningSolution
public class Scheduler implements Solution<HardSoftLongScore> {

	private HardSoftLongScore score;

	// Problem facts
	private List<Date> startDateList;
	private List<Room> roomList;

	// Planning Entities
	private List<Entry> entryList;

	// Other Information about the schedule
	private Set<Traject> trajectSet;

	/**
	 * A no-arg constructor to create a clone during solving of the solver.
	 */
	public Scheduler() {

	}

	public Scheduler(List<Date> startDateList, List<Room> roomList,
			List<Entry> entryList, Set<Traject> trajectSet) {
		this.startDateList = startDateList;
		this.roomList = roomList;
		this.entryList = entryList;
		this.trajectSet = trajectSet;
	}

	@ValueRangeProvider(id = "startDateRange")
	public List<Date> getStartDateList() {
		return startDateList;
	}

	@ValueRangeProvider(id = "roomRange")
	public List<Room> getRoomList() {
		return roomList;
	}

	/**
	 * The method is only used if Drools is used for score calculation. Other
	 * score directors do not use it.
	 * 
	 * All objects returned by the getProblemFacts() method will be asserted
	 * into the Drools working memory, so the score rules can access them.
	 */
	@Override
	public Collection<? extends Object> getProblemFacts() {
		List<Object> facts = new ArrayList<Object>();

		facts.addAll(trajectSet);
		// Do not add the planning entity's (entryList) because that will be
		// done automatically
		facts.addAll(trajectSet);
		facts.addAll(startDateList);
		facts.addAll(roomList);
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
