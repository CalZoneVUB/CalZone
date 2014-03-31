package com.vub.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.impl.score.director.simple.SimpleScoreCalculator;

import com.vub.model.Entry;

/**
 * Class that calculates the score of a current solution.
 * 
 * This way is simple, but is not recommended due to too slow and not scalable.
 * 
 * Use drools rule engine instead.
 * 
 * @author pieter
 *
 */
public class SchedularSimpleScoreCalculator implements SimpleScoreCalculator<Schedular> {

	public SimpleScore calculateScore(Schedular solution) {
		int score = 0;
		List<Entry> entryList = solution.getEntryList();
		List<Date> startDateList = new ArrayList<Date>();
		
		for (Entry e : entryList) {
			// Check for courses which start on the same date
			Date currDate = e.getStartDate();
			if (startDateList.contains(currDate)) {
				score--;
			} else {
				startDateList.add(currDate);
			}
			
			// Check for courses which have the same professor
			
		}
		
		return SimpleScore.valueOf(score);
	}
}