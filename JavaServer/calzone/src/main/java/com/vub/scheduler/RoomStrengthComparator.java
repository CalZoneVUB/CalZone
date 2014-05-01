package com.vub.scheduler;

import java.util.Comparator;

import org.apache.commons.lang.builder.CompareToBuilder;

import com.vub.model.Room;
/**
 * Some optimization algorithms require a comparison method for planning variables.
 * This class is used to compare the planning variable room 
 * by setting a strengthComparatorClass to the @PlanningVariable annotation.
 * 
 * @author youri
 *
 */
public class RoomStrengthComparator implements Comparator<Room> {
	 @Override
	public int compare(Room a, Room b) {
	        return new CompareToBuilder()
	                .append(a.getId(), b.getId())
	                .toComparison();
	    }
}