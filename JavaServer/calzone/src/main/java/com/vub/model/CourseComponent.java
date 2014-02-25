package com.vub.model;

import java.util.ArrayList;

public class CourseComponent {
	ComponentType componentType;
	int contactHours;
	int startStop;
	ArrayList<Assistant> listOfAssistants;
	
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
	public int getStartStop() {
		return startStop;
	}
	public void setStartStop(int startStop) {
		this.startStop = startStop;
	}
}
