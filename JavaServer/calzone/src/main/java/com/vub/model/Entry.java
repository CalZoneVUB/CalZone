package com.vub.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.CompareToBuilder;
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
public class Entry implements Comparable<Entry> {
	Date startDate;
	Room room;

	CourseComponent courseComponent;
	int indexInCourseComponent;

	@PlanningVariable(valueRangeProviderRefs = { "startDateRange" })
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@PlanningVariable(valueRangeProviderRefs = { "roomRange" })
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public CourseComponent getCourseComponent() {
		return courseComponent;
	}

	public void setCourseComponent(CourseComponent courseComponent) {
		this.courseComponent = courseComponent;
	}

	/**
	 * A course components exists most of the times of multiple lectures. This
	 * number gives the index number of the lecture in all the given lectures.
	 * 
	 * @return the index in the coursecomponent.
	 * 
	 * @author Pieter Meiresone
	 */
	public int getIndexInCourseComponent() {
		return indexInCourseComponent;
	}

	/**
	 * @see {@link #getIndexInCourseComponent()}
	 * 
	 * @param indexInCourseComponent
	 *            the index in the coursecomponent
	 * 
	 * @author Pieter Meiresone
	 */
	public void setIndexInCourseComponent(int indexInCourseComponent) {
		this.indexInCourseComponent = indexInCourseComponent;
	}

	/**
	 * Returns the enddate of the entry. This is a derived value based based on
	 * the startdate and the duration of the coursecomponent. This method is
	 * static so it can be used with Drools Rule engine.
	 * 
	 * @param entryStartDate
	 *            The start date of the entry.
	 * @param entryCc
	 *            The course component of the entry.
	 * 
	 * @return The enddate of the entry slot.
	 */
	public static Date calcEndDate(Date entryStartDate, CourseComponent entryCc) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(entryStartDate);
		cal.add(Calendar.HOUR, entryCc.getDuration());
		return cal.getTime();
	}

	// @Override
	// public String toString() {
	// return "Entry [startDate=" + startDate + ", endDate=" + endDate
	// + ", courseComponent=" + courseComponent + ", room=" + room
	// + "]";
	// }

	@Override
	public String toString() {
		String result = "";

		result += "Lecture start: Week ";
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		result += cal.get(Calendar.WEEK_OF_YEAR);
		result += ", Date ";
		result += startDate.toString();
		result += " CourseComp: ";
		result += courseComponent.hashCode();
		result += " (startDate: Week ";
		cal.setTime(courseComponent.getStartingDate());
		result += cal.get(Calendar.WEEK_OF_YEAR);
		result += " )";
		result += "; Room ";
		result += room.hashCode();
		List<User> teachers = courseComponent.getTeachers();
		if (teachers != null) {
			for (User u : teachers) {
				result += "teacher = ";
				result += u.getUsername();
			}
		}

		return result;
	}

	/**
	 * Sorts entry's based on their startdate. If the startdate is the same,
	 * then the enddate is compared.
	 */
	@Override
	public int compareTo(Entry o) {
		return new CompareToBuilder()
				.append(this.startDate, o.startDate)
				.append(this.courseComponent.getDuration(),
						o.courseComponent.getDuration()).toComparison();
	}
}
