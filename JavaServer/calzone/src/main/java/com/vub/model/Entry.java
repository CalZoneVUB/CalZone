package com.vub.model;

import java.util.Date;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import com.vub.scheduler.EntryDifficultyComparator;

/**
 * Data object that represents an entry in someone's calender.
 * 
 * @author pieter
 *
 */
@PlanningEntity(difficultyComparatorClass = EntryDifficultyComparator.class)
public class Entry {
	Date startDate;
	Date endDate;
	Course course;
	Room room;

	@PlanningVariable(valueRangeProviderRefs = {"startDateRange"})
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@PlanningVariable(valueRangeProviderRefs = {"roomRange"})
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
