package com.vub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Standard CourseData class. Is included in the Course class and extends the properties defined in this class.
 * @author Sam
 *
 */

@Entity
@Table(name="COURSE_DATA")
public class CourseData {
	@Id
	@GeneratedValue
	@Column(name="CourseDataID")
	private int id;
	
	// What is the description of the course?
	@Column(name="Description")
	private String description; 
	
	// How many ECTS points does this course take?
	@Column(name="ECTS")
	private int ECTS; // European Credit Transfer and Accumulation System (Dutch: "Studiepunten")
	
	// What other courses are the prerequisites for this course?
	//TODO - Model prerequisites (many to many)
	//@Column(name="Prerequisites")
	//private List<Long> prerequisites;	
	
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
	/*public List<Long> getPrerequisites() {
		return prerequisites;
	}*/
	/**
	 * Set the list of prerequisites for this course.
	 * Expects a list of ID's
	 * @param prerequisites The new prerequisites
	 */
	/*public void setPrerequisites(List<Long> prerequisites) {
		this.prerequisites = prerequisites;
	}*/

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
