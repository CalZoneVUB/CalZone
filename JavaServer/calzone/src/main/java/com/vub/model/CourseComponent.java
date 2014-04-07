package com.vub.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Class which contains data about courses. CourseData is general data about a course, 
 * while CourseComponent is a specific part of the course (for example a "dutch: WPO" or "dutch: HOC")
 * @author Sam
 *
 */
@Entity
@Table(name="COURSE_COMPONENT")
public class CourseComponent {
	@Id
	@GeneratedValue
	@Column(name="CourseComponentID")
	private int id;
	
	// What type the given course component is.
	@Column(name="CourseComponentType")
	private CourseComponentType type;
	
	// When is the course given
	@Column(name="CourseComponentTerm")
	private CourseComponentTerm term; // Dutch: "Semester"
	
	// How many hours of classes or working classes?
	@Column(name="ContactHours")
	private int contactHours;
	
	// Models the week number (e.g. "week 3 of the semester") when the associated course starts
	@Column(name="StartingDate")
	private Date startingDate;
	
	@Column(name="endDate")
	private Date endDate;
	
	// How many hours is one sitting of this course? (e.g. "2 hours per class")
	@Column(name="Duration")
	private int duration;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="CourseID")
	private Course course;

	@OneToMany(mappedBy="courseComponent", fetch=FetchType.LAZY)
	private List<CourseTeacherAssociation> teachers;

	/**
	 * <p>Enumeration that describes what term a CourseComponent should be given.<br>
	 * <ul>
	 * <li>S1: Semester one</li>
	 * <li>S2: Semester two</li>
	 * <li>S3: Both semesters</lI>
	 * <li>S4: 2 Years: Semester one odd acjr</li>
	 * <li>S5: 2 Years: Semester two odd acjr</li>
	 * <li>S6: 2 Years: Both semesters odd acjr</lI>
	 * <li>S7: 2 Years: Semester one even acjr</li>
	 * <li>S8: 2 Years: Semester two even acjr</li>
	 * <li>S9: 2 Years: Both semesters even acjr</lI>
	 * <li>EX: Exam</li>
	 * </ul></p>
	 * 
	 * @author Sam
	 *
	 */
	public static enum CourseComponentTerm {
		S1, S2, S3, S4, S5, S6, S7, S8, S9, EX
	}
	
	/**
	 * <p>Enumeration that describes what type a CourseComponent is.<br>
	 * <ul>
	 * <li>HOC</li>
	 * <li>WPO</li>
	 * <li>ZLF</lI>
	 * <li>EXM</li>
	 * </ul></p>
	 * 
	 * @author Nicolas
	 *
	 */
	public static enum CourseComponentType {
		HOC, WPO,
		ZLF, EXM
	}
	
	/**
	 * 
	 * @return Returns the type of this course component
	 */
	public CourseComponentType getType() {
		return type;
	}
	/**
	 * Sets the type of this course component
	 * @param type the type of this course component
	 */
	public void setType(CourseComponentType type) {
		this.type = type;
	}
	/**
	 * 
	 * @return Returns the term for this course (how long it runs)
	 */
	public CourseComponentTerm getTerm() {
		return term;
	}
	/**
	 * Sets the term for this course
	 * @param term the term for the course
	 */
	public void setTerm(CourseComponentTerm term) {
		this.term = term;
	}
	/**
	 * 
	 * @return Returns the amount of hours are reserved for this CourseComponent
	 */
	public int getContactHours() {
		return contactHours;
	}
	/**
	 * Set the amount of hours necessary for this CourseComponent
	 * @param contactHours
	 */
	public void setContactHours(int contactHours) {
		this.contactHours = contactHours;
	}
	/**
	 * 
	 * @return Returns the Course this CourseComponent belongs to
	 */
	public Course getCourse() {
		return course;
	}
	/**
	 * Set the Course linked to this CourseComponent
	 * @param course The Course linked to this CourseComponent
	 */
	public void setCourse(Course course) {
		this.course = course;
	}
	/**
	 * Set the list of teachers linked to this CourseComponent
	 * @param teachers
	 */
	public void setTeachers(List<CourseTeacherAssociation> teachers) {
		this.teachers = teachers;
	}
	
	/**
	 * Gets a list of all the Course-Teacher associations which are associated with this course.
	 * @return List of associations for this course.
	 */
	public List<CourseTeacherAssociation> getTeachers() {
		return teachers;
	}
	/**
	 * 
	 * @return Returns the week when this course starts (which doesn't have to be week one of the year)
	 */
	public Date getStartingDate() {
		return startingDate;
	}
	/**
	 * @param startingWeek Sets the new starting week when this course starts
	 */
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}
	/**
	 * 
	 * @return Returns the end date when a course needs to be finished.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 
	 * @param endDate Sets the new end week when the course has to finish.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/** 
	 * @return Returns the duration of the course (e.g. "2 hours in one sitting")
	 */
	public int getDuration() {
		return duration;
	}
	/**
	 * @param duration Sets the duration of the course (e.g. "2 hours in one sitting")
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
}