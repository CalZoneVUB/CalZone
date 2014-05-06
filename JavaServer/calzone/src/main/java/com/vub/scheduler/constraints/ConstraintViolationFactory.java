/**
 * 
 */
package com.vub.scheduler.constraints;

import java.util.Date;
import java.util.List;

import com.vub.model.Entry;
import com.vub.model.Traject;

/**
 * @author Pieter Meiresone
 * @author Youri Coppens
 * 
 */
public class ConstraintViolationFactory {
	public static ConstraintViolation createConstraintViolation(
			String constraintName, List<Object> justificationList) {
		if (constraintName.equals(RuleNames.adjacentCcViolated)) {
			return new AdjacentCourseComponentCV((Entry) justificationList.get(0), (Entry) justificationList.get(1));
		
		} else if (constraintName.equals(RuleNames.teacherAgendaViolated)) {
			return new TeacherAgendaCV((Entry) justificationList.get(0), (Entry) justificationList.get(0));
		
		} else if (constraintName.equals(RuleNames.studentAgendaViolated)) {
			return new StudentAgendaCV((Traject) justificationList.get(0), (Entry) justificationList.get(0), (Entry) justificationList.get(1));
		
		} else if (constraintName.equals(RuleNames.studentAgendaDurationViolated)) {
			return new StudentAgendaDurationCV((Traject) justificationList.get(0));
		
		} else if (constraintName.equals(RuleNames.courseStartDateViolated)) {
			return new CourseStartDateCV((Entry) justificationList.get(0));
		
		} else if (constraintName.equals(RuleNames.courseEndDateViolated)) {
			return new CourseEndDateCV((Entry) justificationList.get(0));
		
		} else if (constraintName.equals(RuleNames.roomTypeViolated)) {
			return new RoomTypeCV((Entry) justificationList.get(0));
		
		} else if (constraintName.equals(RuleNames.roomProjectorViolated)) {
			return new RoomProjectorCV((Entry) justificationList.get(0));
		
		} else if (constraintName.equals(RuleNames.roomRecorderViolated)) {
			return new RoomRecorderCV((Entry) justificationList.get(0));
		
		} else if (constraintName.equals(RuleNames.roomSmartBoardViolated)) {
			return new RoomSmartBoardCV((Entry) justificationList.get(0));
		
		} else if (constraintName.equals(RuleNames.roomCapacityViolated)) {
			return new RoomCapacityCV((Entry) justificationList.get(0));
		
		} else if (constraintName.equals(RuleNames.roomAgendaViolated)) {
			return new RoomAgendaCV((Entry) justificationList.get(0), (Entry) justificationList.get(1));
			
		} else if (constraintName.equals(RuleNames.spareHoursViolated)) {
			return new SpareHoursCV((Traject) justificationList.get(0), (Date) justificationList.get(1));
			
		} else {
			return null;
		}
	}
}
