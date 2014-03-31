package com.vub.scheduler;

import java.util.Comparator;

import org.apache.commons.lang.builder.CompareToBuilder;

import com.vub.model.Entry;

/**
 * Some optimization algorithms work more efficiently if they have an estimation
 * of which planning entities are more difficult to plan.
 * 
 * For example: in bin packing bigger items are harder to fit, in course
 * scheduling lectures with more students are more difficult to schedule.
 * 
 * This class is used in the planning entity by setting a
 * difficultyComparatorClass to the @PlanningEntity annotation.
 * 
 * @author pieter
 * 
 */
public class EntryDifficultyComparator implements Comparator<Entry> {

	public int compare(Entry e1, Entry e2) {
		// TODO implement
		/*
		 * return new CompareToBuilder()
		 * .append(a.getRequiredMultiplicand(), b.getRequiredMultiplicand())
		 * .append(a.getId(), b.getId())
		 * .toComparison();
		 */
		return 0;
	}

}
