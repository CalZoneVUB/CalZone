/**
 * 
 */
package com.vub.scheduler;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.config.solver.XmlSolverFactory;
import org.optaplanner.core.impl.score.director.ScoreDirector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Pieter Meiresone
 *
 */
public class SchedulerScoreCalculator {
	final Logger logger = LoggerFactory.getLogger(getClass());

	protected Solver solver;
	private Scheduler solution;
	private ScoreDirector scoreDirector;
	private HardSoftLongScore score;
	
	public SchedulerScoreCalculator(Scheduler scheduler) {
		this.solution = scheduler;
		this.solver = createSolverByXML();
		this.solver.setPlanningProblem(scheduler);
		
		this.scoreDirector = solver.getScoreDirectorFactory().buildScoreDirector();
		this.scoreDirector.setWorkingSolution(solution);
		this.score = (HardSoftLongScore) scoreDirector.calculateScore();
	}
	

	/**
	 * Constructs the solver by use of the solver configuration written in the
	 * XML file.
	 * 
	 * This is the recommended way above the creation of the solver by use of
	 * the API.
	 * 
	 * @return a solver instance.
	 */
	private Solver createSolverByXML() {
		XmlSolverFactory solverFactory = new XmlSolverFactory(
				"/com/vub/scheduler/SchedulerSolverConfig.xml");
		return solverFactory.buildSolver();
	}
	
	/**
	 * Set the the solution to calculate the score. The score is automatically calculated.
	 * 
	 * @param solution the new solution
	 */
	protected void setScheduler(Scheduler solution) {
		this.solution = solution;
		this.scoreDirector.setWorkingSolution(solution);
		this.score = (HardSoftLongScore) scoreDirector.calculateScore();
	}
	
	protected Scheduler getScheduler() {
		return solution;
	}
	
	public ScoreDirector getScoreDirector() {
		return scoreDirector;
	}

	/**
	 * @return the score
	 */
	public HardSoftLongScore getScore() {
		return score;
	}
}
