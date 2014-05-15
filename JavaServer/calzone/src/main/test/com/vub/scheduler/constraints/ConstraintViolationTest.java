/**
 * 
 */
package com.vub.scheduler.constraints;

import static org.junit.Assert.*;

import org.junit.Test;

import com.vub.scheduler.SchedulerScoreCalculator;
import com.vub.scheduler.SchedulerTest;

/**
 * @author pieter
 * 
 */
public class ConstraintViolationTest extends SchedulerTest {
	protected String ruleName;

	public ConstraintViolationTest(String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * Checks whether all the
	 * {@link com.vub.scheduler.constraints.ConstraintViolation} objects can be
	 * created correctly.
	 * 
	 * @param ssc
	 */
	public void assertConstraintViolationObject(SchedulerScoreCalculator ssc) {
		ConstraintChecker cc = new ConstraintChecker(ssc.getScoreDirector());

		for (ConstraintViolation cv : cc.getViolations()) {
			assertNotNull(
					"ConstraintViolation is not initialized (null value).", cv);
			logger.info("Testing constraint violation object: {}",
					cv.description());
		}
	}
}
