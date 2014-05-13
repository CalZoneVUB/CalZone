/**
 * 
 */
package com.vub.scheduler;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.constraint.ConstraintMatchTotal;
import org.optaplanner.core.impl.score.director.ScoreDirector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vub.model.Entry;

/**
 * @author pieter
 *
 */
public class SchedulerTest {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * Method for logging the all the entries. This is used for debugging.
	 * 
	 * @param description
	 *            '"Unit Test: " + description' will be send to the logger.
	 * @param entryList
	 *            The entries to log to the logger.
	 * 
	 * @author pieter
	 */
	protected void logEntries(String description, List<Entry> entryList) {
		Collections.sort(entryList);
		logger.info("Unit test: " + description);
		for (Entry e : entryList) {
			logger.info(e.toString());
		}
	}
	
	protected Collection<String> getViolatedConstraintNames(ScoreDirector sd) {
		Score score = sd.calculateScore();
		Collection<String> constraintNames = new HashSet<String>();

		for (ConstraintMatchTotal cmt : sd.getConstraintMatchTotals()) {
			constraintNames.add(cmt.getConstraintName());
		}

		return constraintNames;
	}
}
