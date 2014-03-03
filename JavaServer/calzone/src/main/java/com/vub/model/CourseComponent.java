package com.vub.model;

public class CourseComponent {
	ComponentType componentType;
	int contactHours;
	int academicYear;
	
	public ComponentType getComponentType() {
		return componentType;
	}
	@Override
	public String toString() {
		return "CourseComponent [componentType=" + componentType
				+ ", contactHours=" + contactHours + ", academicYear="
				+ academicYear + "]";
	}
	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}
	public int getContactHours() {
		return contactHours;
	}
	public void setContactHours(int contactHours) {
		this.contactHours = contactHours;
	}
	public int getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}
}
