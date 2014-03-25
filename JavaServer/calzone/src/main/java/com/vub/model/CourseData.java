package com.vub.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Classname: CourseData
 * 
 * Version: 1
 *
 * Date: 25/02/2014
 * 
 * Author: Sam Van den Vonder
 */

@Entity
@Table(name="CourseComponents")
public class CourseData {
	@Id
	@GeneratedValue
	@Column(name="CourseComponentID")
	private long id;
	
	// How many hours of classes or working classes?
	@Column(name="ContactHours")
	private String contactHours;
	
	// What is the description of the course?
	@Column(name="Description")
	private String description; 
	
	// How many ECTS points does this course take?
	@Column(name="ECTS")
	private int ECTS; // European Credit Transfer and Accumulation System (Dutch: "Studiepunten")
	
	// What other courses are the prerequisites for this course?
	@Column(name="Prerequisites")
	private List<Long> prerequisites;
	
	// When is the course given
	@Column(name="CourseTerm")
	@Enumerated(EnumType.STRING)
	private CourseTerm term; // Dutch: "Semester"	
	
	// is re-examination possible?
	@Column(name="ReexaminationPossible")
	private boolean reexaminationPossible;
	
	// What language will the course be given in?
	@Column(name="Language")
	private String language; // Language constraints in UI
	
	// What are people expected to learn from the course?
	@Column(name="LearningGoals")
	private String learningGoals;
	
	// How is the student graded?
	@Column(name="Grading")
	private String grading;

	// TODO
	private int studyTime; // Derived value?
	
	/**
	 * <p>Enumeration that describes what term a course should be given.<br>
	 * <ul>
	 * <li>S1: Semester one</li>
	 * <li>S2: Semester two</li>
	 * <li>S3: Both semesters</lI>
	 * <li>EX: Exam</li>
	 * </ul></p>
	 * 
	 * @author Sam
	 *
	 */
	public static enum CourseTerm {
		S1, S2,
		S3, EX
	}
	
	/**
	 * 
	 * @return Gets the description of the course
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Sets the description of the course
	 * @param description New description of the course
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * European Credit Transfer System
	 * @return Returns the amount of ECTS points this course is worth
	 */
	public int getECTS() {
		return ECTS;
	}
	/**
	 * 
	 * @param ECTS  Sets the amount of ECTS points this course is worth
	 */
	public void setECTS(int eCTS) {
		ECTS = eCTS;
	}
	/**
	 * 
	 * @return Returns a list of Course ID's that are prerequisites for this course
	 */
	public List<Long> getPrerequisites() {
		return prerequisites;
	}
	/**
	 * Set the list of prerequisites for this course.
	 * Expects a list of ID's
	 * @param prerequisites The new prerequisites
	 */
	public void setPrerequisites(List<Long> prerequisites) {
		this.prerequisites = prerequisites;
	}
	/**
	 * 
	 * @return Returns the term for this course (how long it runs)
	 */
	public CourseTerm getTerm() {
		return term;
	}
	/**
	 * Sets the term for this course
	 * @param term the term for the course
	 */
	public void setTerm(CourseTerm term) {
		this.term = term;
	}
	/**
	 * 
	 * @return Checks if re-examination is possible for this course
	 */
	public boolean isReexaminationPossible() {
		return reexaminationPossible;
	}
	/**
	 * 
	 * @param reexaminationPossible Sets if re-examination is possible for this course
	 */
	public void setReexaminationPossible(boolean reexaminationPossible) {
		this.reexaminationPossible = reexaminationPossible;
	}
	/**
	 * 
	 * @return Returns the language this course is taught in
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * 
	 * @param language Sets the language this course is taught in
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * Returns the learning goals this course provides. This is plain-text, filled in by the professor.
	 * @return Leaning goals in plain text
	 */
	public String getLearningGoals() {
		return learningGoals;
	}
	/**
	 * Set the learning goals, provided by the professor (plain text explanation)
	 * @param learningGoals Leaning goals in plain-text
	 */
	public void setLearningGoals(String learningGoals) {
		this.learningGoals = learningGoals;
	}
	/**
	 * Get the explanation on how this course is graded.
	 * @return Plain-text explanation on how this course is graded
	 */
	public String getGrading() {
		return grading;
	}
	/**
	 * Set the explanation corresponding to how this course is graded
	 * @param grading New explanation regarding the way this course is graded
	 */
	public void setGrading(String grading) {
		this.grading = grading;
	}
}
