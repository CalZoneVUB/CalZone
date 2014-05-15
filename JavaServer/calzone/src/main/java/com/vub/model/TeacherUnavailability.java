package com.vub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonView;

import com.vub.utility.Views;

/**
 * Class that represent an unavailibility slot of a teacher (assistant or
 * professor).
 * 
 * @author Pieter Meiresone
 * 
 */
@Entity
@Table(name="TEACHER_UNAVAILABILITY")
public class TeacherUnavailability {
	@JsonView(Views.Prefs.class)
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@JsonView(Views.Prefs.class)
	@Column(name="DayOfWeek")
	private int dayOfWeek;

	@JsonView(Views.Prefs.class)
	@Column(name="StartingHour")
	private int startingHour;

	@JsonView(Views.Prefs.class)
	@Column(name="EndingHour")
	private int endingHour;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TeacherID")
	private User teacher;

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
}
