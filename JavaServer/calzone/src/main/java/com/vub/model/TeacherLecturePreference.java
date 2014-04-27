package com.vub.model;

/**
 * Class that represent a preference of a professor where he wants to give a
 * lecture.
 * 
 * @author Pieter Meiresone
 * 
 */
public class TeacherLecturePreference {
	private int id;

	private int dayOfWeek;

	private int startingHour;

	private int endingHour;

	private User teacher;

	private CourseComponent courseComponent;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the dayOfWeek
	 */
	public int getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * @param dayOfWeek
	 *            the dayOfWeek to set
	 */
	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * @return the startingHour
	 */
	public int getStartingHour() {
		return startingHour;
	}

	/**
	 * @param startingHour
	 *            the startingHour to set
	 */
	public void setStartingHour(int startingHour) {
		this.startingHour = startingHour;
	}

	/**
	 * @return the endingHour
	 */
	public int getEndingHour() {
		return endingHour;
	}

	/**
	 * @param endingHour
	 *            the endingHour to set
	 */
	public void setEndingHour(int endingHour) {
		this.endingHour = endingHour;
	}

	/**
	 * @return the teacher
	 */
	public User getTeacher() {
		return teacher;
	}

	/**
	 * @param teacher
	 *            the teacher to set
	 */
	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	/**
	 * @return the courseComponent
	 */
	public CourseComponent getCourseComponent() {
		return courseComponent;
	}

	/**
	 * @param courseComponent
	 *            the courseComponent to set
	 */
	public void setCourseComponent(CourseComponent courseComponent) {
		this.courseComponent = courseComponent;
	}
}
