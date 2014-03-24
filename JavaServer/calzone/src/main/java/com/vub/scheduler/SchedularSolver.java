package com.vub.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.config.solver.SolverConfig;
import org.optaplanner.core.config.solver.XmlSolverFactory;

import com.vub.model.Course;
import com.vub.model.Entry;
import com.vub.model.Room;

public class SchedularSolver {
	private List<Date> startDateList;
	private List<Room> roomList;
	private List<Entry> entryList;
	
	public SchedularSolver(List<Date> startDateList, List<Room> roomList, List<Course> courseList) {
		this.startDateList = startDateList;
		this.roomList = roomList;
		this.entryList = createEntryList(courseList);
	}
	
	public Schedular run() {
		XmlSolverFactory solverFactory = new XmlSolverFactory("/com/vub/scheduler/SchedulerSolverConfig.xml");

		SolverConfig solverConfig = solverFactory.getSolverConfig();
		//solverConfig.getTerminationConfig().setMaximumMinutesSpend(userInput);

		Solver solver = solverConfig.buildSolver();
		solver.setPlanningProblem(createSchedular());
		solver.solve();
		Schedular bestSolution = (Schedular) solver.getBestSolution();
		return bestSolution;
	}

	private Schedular createSchedular() {
		Schedular schedular = new Schedular();
		schedular.setStartDateList(startDateList);
		schedular.setRoomList(roomList);
		schedular.setEntryList(entryList);
		return schedular;
	}

	private List<Entry> createEntryList(List<Course> courseList) {
		List<Entry> entryList = new ArrayList<Entry>();
		for (Course c : courseList) {
			Entry entry = new Entry();
			entry.setStartDate(startDateList.get(0));
			entry.setRoom(roomList.get(0));
			entry.setCourse(c);
			entryList.add(entry);
		}
		return entryList;
	}
}
