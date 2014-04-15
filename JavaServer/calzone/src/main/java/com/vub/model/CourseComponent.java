package com.vub.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class which contains data about courses. CourseData is general data about a course, 
 * while CourseComponent is a specific part of the course (for example a "dutch: WPO" or "dutch: HOC")
 * @author Sam, Nicolas
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
	
	// Models the date when the associated course starts
	@Column(name="StartingDate")
	private Date startingDate;
	
	// Models the date when the associated course starts
	@Column(name="EndingDate")
	private Date endingDate;
	
	// How many hours is one sitting of this course? (e.g. "2 hours per class")
	@Column(name="Duration")
	private int duration;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="CourseID", updatable = false, nullable = true)
	private Course course;

	// Every coursecomponent has certain requirements that define in which room they can take place
	// These requirements go in the following fields, prefixed by room
	@Column(name="RoomCapacityRequirement")
	private int roomCapacityRequirement;
	
	@Column(name="RoomTypeRequirement")
	private Room.RoomType roomTypeRequirement;
	
	@Column(name="RoomProjectorRequirement")
	private boolean roomProjectorRequirement;
	
	@Column(name="RoomRecorderRequirement")
	private boolean roomRecorderRequirement;
	
	@Column(name="RoomSMARTBoardRequirement")
	private boolean roomSMARTBoardRequirement;
	
	@ManyToMany(cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
	@JoinTable(name = "COURSE_COMPONENT_USER", joinColumns = { 
			@JoinColumn(name = "CourseComponentID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "UserID", 
					nullable = false, updatable = false) })
	private Set<User> teachers = new HashSet<User>(0);

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
	
	public int getId() {
		return id;
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
	 * Set the set of teachers linked to this CourseComponent
	 * @param newTeachers
	 */
	public void setTeachers(Set<User> newTeachers) {
		this.teachers.addAll(newTeachers);
	}
	/**
	 * Get all teachers of this CourseComponent.
	 * @return Set of User objects that represent the teachers of this CourseComponent
	 */
	public Set<User> getTeachers() {
		return teachers;
	}
	/**
	 * 
	 * @return Returns the date when this course starts
	 */
	public Date getStartingDate() {
		return startingDate;
	}
	/**
	 * @param startingDate Sets the new starting date when this course starts
	 */
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}
	/**
	 * 
	 * @return Returns the date when this course ends
	 */
	public Date getEndingDate() {
		return endingDate;
	}
	/**
	 * @param endingDate Sets the new date when this course ends
	 */
	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
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

	/**
	 * 
	 * @return Returns this courses' capacity requirement on the room it is taught in (how many people it should fit)
	 */
	public int getRoomCapacityRequirement() {
		return roomCapacityRequirement;
	}
	/**
	 * 
	 * @param roomCapacityRequirement Sets the capacity requirements on the room this course is taught in
	 */
	public void setRoomCapacityRequirement(int roomCapacityRequirement) {
		this.roomCapacityRequirement = roomCapacityRequirement;
	}
	/**
	 *
	 * @return Returns this courses' room type requirement on the room it is taught in (whether it is a classroom, or a normal room, etc)
	 */
	public Room.RoomType getRoomTypeRequirement() {
		return roomTypeRequirement;
	}
	/**
	 * 
	 * @param roomTypeRequirement Sets the new room type requirements on the room this course is taught in
	 */
	public void setRoomTypeRequirement(Room.RoomType roomTypeRequirement) {
		this.roomTypeRequirement = roomTypeRequirement;
	}
	
	/**
	 * 
	 * @return Returns whether the room this course is taught in needs a projector
	 */
	public boolean getRoomProjectorRequirement() {
		return roomProjectorRequirement;
	}
	/**
	 * 
	 * @param roomProjectorRequirement Sets whether the room this course is taught in needs a projector
	 */
	public void setRoomProjectorRequirement(boolean roomProjectorRequirement) {
		this.roomProjectorRequirement = roomProjectorRequirement;
	}
	/**
	 * 
	 * @return Returns whether the room this course is taught in needs recording equipment
	 */
	public boolean getRoomRecorderRequirement() {
		return roomRecorderRequirement;
	}
	/**
	 * 
	 * @param roomRecorderRequirement Set whether the room this course is taught in needs recording equipment
	 */
	public void setRoomRecorderRequirement(boolean roomRecorderRequirement) {
		this.roomRecorderRequirement = roomRecorderRequirement;
	}
	/**
	 * 
	 * @return Returns whether the room this course is taught in needs a SMART Board
	 */
	public boolean getRoomSMARTBoardRequirement() {
		return roomSMARTBoardRequirement;
	}
	/**
	 * 
	 * @param roomSMARTBoardRequirement Sets whether the room this course is taught in requires a SMART Board
	 */
	public void setRoomSMARTBoardRequirement(boolean roomSMARTBoardRequirement) {
		this.roomSMARTBoardRequirement = roomSMARTBoardRequirement;
	}
	@Override
	public String toString() {
		return "CourseComponent [id=" + id + ", type=" + type + ", term="
				+ term + ", contactHours=" + contactHours + ", startingDate="
				+ duration 	+ ", roomCapacityRequirement=" + roomCapacityRequirement
				+ ", roomTypeRequirement=" + roomTypeRequirement
				+ ", roomProjectorRequirement=" + roomProjectorRequirement
				+ ", roomRecorderRequirement=" + roomRecorderRequirement
				+ ", roomSMARTBoardRequirement=" + roomSMARTBoardRequirement + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	/* (non-Javadoc)
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
		CourseComponent other = (CourseComponent) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
