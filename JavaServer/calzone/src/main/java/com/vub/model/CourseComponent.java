package com.vub.model;

import java.util.Date;

public class CourseComponent {
	ComponentType componentType;
	int contactHours;
	Date startDate;
	
	public ComponentType getComponentType() {
		return componentType;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}
