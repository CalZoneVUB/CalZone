package com.vub.model;

import java.util.Date;
import java.util.List;

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
	CourseComponent courseComponent;
	Room room;

	@PlanningVariable(valueRangeProviderRefs = { "startDateRange" })
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

	public CourseComponent getCourseComponent() {
		return courseComponent;
	}

	public void setCourseComponent(CourseComponent courseComponent) {
		this.courseComponent = courseComponent;
	}

	@PlanningVariable(valueRangeProviderRefs = { "roomRange" })
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

//	@Override
//	public String toString() {
//		return "Entry [startDate=" + startDate + ", endDate=" + endDate
//				+ ", courseComponent=" + courseComponent + ", room=" + room
//				+ "]";
//	}

	@Override
	public String toString() {
		String result = "";

		result += "Lecture start: ";
		result += startDate.toString();
		result += "CourseComp: ";
		result += courseComponent.hashCode();
		result += "; Room ";
		result += room.hashCode();
		List<CourseTeacherAssociation> teachers = courseComponent.getTeachers();
		if (teachers != null) {
			for (int j = 0; j < teachers.size(); j++) {
				CourseTeacherAssociation currTeacher = teachers.get(j);
				result += "teacher = ";
				result += currTeacher.getUser().getUsername();
			}
		}

		return result;
	}
}
