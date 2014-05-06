/**
 * 
 */
package com.vub.scheduler.constraints;

import java.util.ArrayList;
import java.util.List;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.constraint.ConstraintMatch;
import org.optaplanner.core.api.score.constraint.ConstraintMatchTotal;
import org.optaplanner.core.impl.score.director.ScoreDirector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Pieter Meiresone
 * 
 */
public class ConstraintChecker {
	final Logger logger = LoggerFactory.getLogger(getClass());

	private List<ConstraintViolation> violations;

	public ConstraintChecker(ScoreDirector scoreDirector) {
		violations = new ArrayList<ConstraintViolation>();

		for (ConstraintMatchTotal constraintMatchTotal : scoreDirector
				.getConstraintMatchTotals()) {
			String constraintName = constraintMatchTotal.getConstraintName();
			for (ConstraintMatch constraintMatch : constraintMatchTotal
					.getConstraintMatchSet()) {
				List<Object> justificationList = constraintMatch
						.getJustificationList();
				ConstraintViolation cv = ConstraintViolationFactory
						.createConstraintViolation(constraintName,
								justificationList);
				violations.add(cv);
			}
		}
	}

	/**
	 * @return the violations
	 */
	public List<ConstraintViolation> getViolations() {
		return violations;
	}

}
