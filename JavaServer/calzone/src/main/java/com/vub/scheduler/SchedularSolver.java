package com.vub.scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.config.constructionheuristic.ConstructionHeuristicSolverPhaseConfig;
import org.optaplanner.core.config.localsearch.LocalSearchSolverPhaseConfig;
import org.optaplanner.core.config.phase.SolverPhaseConfig;
import org.optaplanner.core.config.score.director.ScoreDirectorFactoryConfig;
import org.optaplanner.core.config.solver.SolverConfig;
import org.optaplanner.core.config.solver.XmlSolverFactory;
import org.optaplanner.core.config.termination.TerminationConfig;

import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.Entry;
import com.vub.model.Room;

/**
 * Class which acts as an extra abstraction layer above the 'Solver' class from
 * the optaplanner API.
 * 
 * @author pieter
 * 
 */
public class SchedularSolver {
	private List<Date> startDateList;
	private List<Room> roomList;
	private List<CourseComponent> courseComponentList;
	private List<Entry> entryList;

	public SchedularSolver(List<Date> startDateList, List<Room> roomList,
			List<CourseComponent> courseComponentList) {
		this.startDateList = startDateList;
		this.roomList = roomList;
		this.courseComponentList = courseComponentList;
		this.entryList = createEntryList();
	}

	public Schedular run() {
		Solver solver = createSolverByXML();
		solver.setPlanningProblem(createSchedular());
		solver.solve();
		Schedular bestSolution = (Schedular) solver.getBestSolution();
		return bestSolution;
	}

	/**
	 * Constructs the solver by use of the solver configuration written in the
	 * XML file.
	 * 
	 * This is the recommended way above the creation of the solver by use of
	 * the API.
	 * 
	 * @return
	 */
	private Solver createSolverByXML() {
		XmlSolverFactory solverFactory = new XmlSolverFactory(
				"/com/vub/scheduler/SchedulerSolverConfig.xml");
		return solverFactory.buildSolver();
	}

	/**
	 * The use of this method is not recommended. Use of this method is because
	 * of issues with XML file.
	 * 
	 * Use {@link #createSolverByXML() createSolverByXML} instead.
	 * 
	 * @return
	 */
	@Deprecated
	private Solver createSolverByAPI() {
		SolverConfig solverConfig = new SolverConfig();
		solverConfig.setSolutionClass(Schedular.class);
		solverConfig.setPlanningEntityClassList(Collections
				.<Class<?>> singletonList(Entry.class));

		// Define the score function
		ScoreDirectorFactoryConfig scoreDirectorFactoryConfig = new ScoreDirectorFactoryConfig();
		scoreDirectorFactoryConfig
				.setScoreDefinitionType(ScoreDirectorFactoryConfig.ScoreDefinitionType.SIMPLE);
		// scoreDirectorFactoryConfig
		// .setScoreDrlList(Arrays
		// .asList("/com/vub/scheduler/SchedularScoreRules.drl"));
		scoreDirectorFactoryConfig
				.setSimpleScoreCalculatorClass(SchedularSimpleScoreCalculator.class);
		solverConfig.setScoreDirectorFactoryConfig(scoreDirectorFactoryConfig);

		TerminationConfig terminationConfig = new TerminationConfig();
		solverConfig.setTerminationConfig(terminationConfig);

		List<SolverPhaseConfig> solverPhaseConfigList = new ArrayList<SolverPhaseConfig>();

		ConstructionHeuristicSolverPhaseConfig constructionHeuristicSolverPhaseConfig = new ConstructionHeuristicSolverPhaseConfig();
		solverPhaseConfigList.add(constructionHeuristicSolverPhaseConfig);

		LocalSearchSolverPhaseConfig localSearchSolverPhaseConfig = new LocalSearchSolverPhaseConfig();
		solverPhaseConfigList.add(localSearchSolverPhaseConfig);

		solverConfig.setSolverPhaseConfigList(solverPhaseConfigList);

		return solverConfig.buildSolver();
	}

	private Schedular createSchedular() {
		Schedular schedular = new Schedular();
		schedular.setStartDateList(startDateList);
		schedular.setRoomList(roomList);
		schedular.setCourseComponentList(courseComponentList);
		schedular.setEntryList(entryList);
		return schedular;
	}

	private List<Entry> createEntryList() {
		List<Entry> entryList = new ArrayList<Entry>();
		for (CourseComponent c : courseComponentList) {
				Entry entry = new Entry();
				entry.setStartDate(startDateList.get(0));
				entry.setRoom(roomList.get(0));
				entry.setCourseComponent(c);
				entryList.add(entry);
			}
		return entryList;
	}
}
