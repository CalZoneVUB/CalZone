package com.vub.model;

import java.util.Set;

/*
 * Classname: CourseData
 * 
 * Version: 1
 *
 * Date: 25/02/2014
 * 
 * Author: Sam Van den Vonder
 */

public class CourseData {
	// TEMPORTARY - REMOVE WHEN COURSES ARE IMPLEMENTED
	private class Course {
		
	}
	private String description; 
	private int ECTS; // European Credit Transfer and Accumulation System (Dutch: "Studiepunten")
	private Set<Course> prerequisites;
	private int term; // Dutch: "Semester"
	private int studyTime; // Amount of hours it takes to study this course (e.g. "160")
	private String catalogueID;
	private boolean reexaminationPossible;
	private String language;
	private String faculty;
	private String department;
	private Set<String> educationalTeam;
	private String activities;
	private String courseMaterial;
	private String learningGoals;
	private String grading;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getECTS() {
		return ECTS;
	}
	public void setECTS(int eCTS) {
		ECTS = eCTS;
	}
	public Set<Course> getPrerequisites() {
		return prerequisites;
	}
	public void setPrerequisites(Set<Course> prerequisites) {
		this.prerequisites = prerequisites;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public int getStudyTime() {
		return studyTime;
	}
	public void setStudyTime(int studyTime) {
		this.studyTime = studyTime;
	}
	public String getCatalogueID() {
		return catalogueID;
	}
	public void setCatalogueID(String catalogueID) {
		this.catalogueID = catalogueID;
	}
	public boolean isReexaminationPossible() {
		return reexaminationPossible;
	}
	public void setReexaminationPossible(boolean reexaminationPossible) {
		this.reexaminationPossible = reexaminationPossible;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Set<String> getEducationalTeam() {
		return educationalTeam;
	}
	public void setEducationalTeam(Set<String> educationalTeam) {
		this.educationalTeam = educationalTeam;
	}
	public String getActivities() {
		return activities;
	}
	public void setActivities(String activities) {
		this.activities = activities;
	}
	public String getCourseMaterial() {
		return courseMaterial;
	}
	public void setCourseMaterial(String courseMaterial) {
		this.courseMaterial = courseMaterial;
	}
	public String getLearningGoals() {
		return learningGoals;
	}
	public void setLearningGoals(String learningGoals) {
		this.learningGoals = learningGoals;
	}
	public String getGrading() {
		return grading;
	}
	public void setGrading(String grading) {
		this.grading = grading;
	}
	
	
}
