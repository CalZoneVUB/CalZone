package com.vub.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.codehaus.jackson.map.annotate.JsonView;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import com.vub.scheduler.DateStrengthComparator;
import com.vub.scheduler.EntryDifficultyComparator;
import com.vub.scheduler.MovableEntrySelectionFilter;
import com.vub.scheduler.RoomStrengthComparator;
import com.vub.utility.DateUtility;
import com.vub.utility.Views;

/**
 * Data object that represents an entry in someone's calender.
 * 
 * @author pieter
 * 
 */
@Entity
@Table(name = "ENTRY")
@PlanningEntity(difficultyComparatorClass = EntryDifficultyComparator.class, movableEntitySelectionFilter = MovableEntrySelectionFilter.class)
public class Entry implements Comparable<Entry> {
	@Id
	@GeneratedValue
	@Column(name = "EntryID")
	@JsonView(Views.EntryFilter.class)
	int id;

	@Column(name = "StartingDate")
	@JsonView(Views.EntryFilter.class)
	Date startingDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RoomID")
	@JsonView(Views.EntryFilter.class)
	Room room;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CourseComponentID")
	@JsonView(Views.EntryFilter.class)
	CourseComponent courseComponent;

	@Column(name = "indexInCourseComponent")
	int indexInCourseComponent;

	@JsonView(Views.EntryFilter.class)
	@Column(name = "Frozen")
	boolean frozen;

	@PlanningVariable(valueRangeProviderRefs = {"startDateRange"}, strengthComparatorClass = DateStrengthComparator.class)
	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startDate) {
		this.startingDate = startDate;
	}

	@PlanningVariable(valueRangeProviderRefs = {"roomRange"}, strengthComparatorClass = RoomStrengthComparator.class)
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

	public int getDuration() {
		return courseComponent.getDuration();
	}

	/**
	 * Calculated the EndDate based on the StartingDate and duration.
	 * 
	 * @return Date
	 * 
	 * @author Christophe Gaethofs
	 */
	public Date getEndingDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.getStartingDate());
		cal.add(Calendar.HOUR, this.getCourseComponent().getDuration());
		return cal.getTime();
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
	 * @return the frozen
	 */
	public boolean isFrozen() {
		return frozen;
	}

	/**
	 * Also sets the necessery frozen variables on his parents, namely Course
	 * and Traject.
	 * 
	 * @param frozen
	 *            the frozen to set
	 */
	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
		propagateFreeze();
	}

	/**
	 * Differs from {@link #setFrozen(boolean)} in that way that it doesn't
	 * report it changes to its parent, namely Course. This method is necessary
	 * to avoid loops.
	 * 
	 * @param frozen
	 *            the frozen to set
	 */
	public void updateFrozen(boolean frozen) {
		this.frozen = frozen;
	}

	public User getTeacher() {
		if (courseComponent.getTeachers() != null) {
			if (this.courseComponent.getTeachers().iterator().hasNext()) {
				return this.courseComponent.getTeachers().iterator().next();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static int getDayOfWeek(Date startingDate) {
		return DateUtility.getDayOfWeek(startingDate);
	}

	public static int getHourOfDay(Date startingDate) {
		return DateUtility.getHourOfDay(startingDate);
	}
	
	public int getId() {
		return this.id;
	}

	/**
	 * an entry is frozen when the boolean frozen is set true.
	 * a course is frozen when the entries of all its coursecomponents are frozen.
	 * a traject is frozen when all its courses are frozen.
	 */
	private void propagateFreeze(){
		if (!frozen) {
			// One entry is set to false, so the course and traject this entry
			// belongs are also not frozen
			// courseComponent.getCourse().updateFrozen(false);
			courseComponent.getCourse().updateFrozen(false);
			for (Traject t : courseComponent.getCourse().getTrajects()) {
				t.updateFrozen(false);
			}
			
		} else {
			//parent has to check when all entries for cc are frozen.
			courseComponent.getCourse().checkIfFrozen();
		}
	}
	/**
	 * Returns the enddate of the entry. This is a derived value based based on
	 * the startdate and the duration of the coursecomponent. This method is
	 * static so it can be used with Drools Rule engine.
	 * 
	 * @param e
	 *            The entry of which the enddate has to be calculated.
	 * 
	 * @return The enddate of the entry slot.
	 */
	public static Date calcEndDate(Entry e) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(e.getStartingDate());
		cal.add(Calendar.HOUR, e.getCourseComponent().getDuration());
		return cal.getTime();
	}

	/**
	 * Returns the enddate of the entry. This is a derived value based based on
	 * the startdate and the duration of the coursecomponent. This method is
	 * static so it can be used with Drools Rule engine.
	 * 
	 * @see {@link #calcEndDate(Entry)}
	 * 
	 * @param entryStartDate
	 *            The start date of the entry.
	 * @param entryCc
	 *            The course component of the entry.
	 * @return The enddate of the entry slot.
	 */
	public static Date calcEndDate(Date entryStartDate, CourseComponent entryCc) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(entryStartDate);
		cal.add(Calendar.HOUR, entryCc.getDuration());
		return cal.getTime();
	}
	
	public static int getWeekOfYear(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
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
		result += "ID: "+id+", Lecture start: Week ";
		result += "Lecture start: Week ";
		Calendar cal = Calendar.getInstance();
		cal.setTime(startingDate);
		result += cal.get(Calendar.WEEK_OF_YEAR);
		result += ",[Frozen: " + frozen + "] ";
		result += ", Students: ";
		result += courseComponent.getRoomCapacityRequirement();
		result += ", Date ";
		result += startingDate.toString();
		result += ", Duration: ";
		result += courseComponent.getDuration();
		result += "; CourseComp: ";
		result += courseComponent.hashCode();
		result += "(index: ";
		result += this.indexInCourseComponent;
		result += " , startDate: Week ";
		cal.setTime(courseComponent.getStartingDate());
		result += cal.get(Calendar.WEEK_OF_YEAR);
		result += " )";
		result += "; Room ID: ";
		result += room.hashCode();
		result += "; Room Capacity: ";
		result += room.getCapacity();

		// Print Trajects
		result += " ; Traject ID: ";
		Set<Traject> trajects = this.courseComponent.getCourse().getTrajects();
		if (trajects != null) {
			for (Traject t : trajects) {
				result += t.getId() + ", ";
			}
		}
		// Print Teachers
		result += "; Teachers: ";
		Set<User> teachers = courseComponent.getTeachers();
		if (teachers != null) {
			for (User u : teachers) {
				result += u.getUsername() + ", ";
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
		.append(this.startingDate, o.startingDate)
		.append(this.courseComponent.getDuration(),
				o.courseComponent.getDuration()).toComparison();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entry other = (Entry) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
