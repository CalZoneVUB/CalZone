package com.vub.scheduler;

import java.util.Date;
import java.util.Comparator;

import org.apache.commons.lang.builder.CompareToBuilder;
/**
 * Some optimization algorithms require a comparison method for planning variables.
 * This class is used to compare the planning variable startDate 
 * by setting a strengthComparatorClass to the @PlanningVariable annotation.
 * 
 * @author youri
 *
 */
public class DateStrengthComparator implements Comparator<Date> {
	@Override
	public int compare(Date a, Date b) {
		return new CompareToBuilder()
		.append(a.getTime(), b.getTime())
		.toComparison();
	}
}
