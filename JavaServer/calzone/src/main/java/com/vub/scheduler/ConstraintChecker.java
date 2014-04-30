/**
 * 
 */
package com.vub.scheduler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;
import org.drools.core.rule.Package;
import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.constraint.ConstraintMatch;
import org.optaplanner.core.api.score.constraint.ConstraintMatchTotal;
import org.optaplanner.core.impl.score.director.ScoreDirector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vub.model.CourseComponent;
import com.vub.model.Entry;
import com.vub.model.Traject;

/**
 * @author Pieter Meiresone
 * 
 */
public class ConstraintChecker {
	final Logger logger = LoggerFactory.getLogger(getClass());

	private Schedular sol;
	// private boolean adjacentCcViolated;
	// private boolean teacherAgendaViolated;
	// private boolean studentAgendaViolated;
	// private boolean startDateViolated;
	// private boolean endDateViolated;
	// private boolean roomTypeViolated;
	// private boolean roomProjectorViolated;
	// private boolean roomRecorderViolated;
	// private boolean roomSmartBoardViolated;
	// private boolean roomCapacityViolated;

	private List<ConstraintViolation> violations;
	private Set<Traject> trajectSet;

	public ConstraintChecker(ScoreDirector scoreDirector) {
		Score score = scoreDirector.calculateScore();

		for (ConstraintMatchTotal constraintMatchTotal : scoreDirector
				.getConstraintMatchTotals()) {
			String constraintName = constraintMatchTotal.getConstraintName();
			Number weightTotal = constraintMatchTotal.getWeightTotalAsNumber();
			logger.info("ConstraintName: " + constraintName);
			for (ConstraintMatch constraintMatch : constraintMatchTotal
					.getConstraintMatchSet()) {
				List<Object> justificationList = constraintMatch
						.getJustificationList();
				Number weight = constraintMatch.getWeightAsNumber();
				for (Object o : justificationList) {
					logger.info("Member of justificationList: " + o.toString());
				}
			}
		}
	}

	public ConstraintChecker(Schedular sol, Set<Traject> trajectSet) {
		this.sol = sol;
		this.violations = new ArrayList<ConstraintViolation>();
		this.trajectSet = trajectSet;

		// Check solution

		// Solver solver = solverFactory.buildSolver();
		// solver.setPlanningProblem(createSchedular());

		// Check solution in drools
		// try {
		// RuleBase ruleBase = initialiseDrools();
		// WorkingMemory workingMemory = initializeObjects(ruleBase, sol);
		// TrackingAgendaEventListener agendaEventListener = new
		// TrackingAgendaEventListener();
		// workingMemory.addEventListener(agendaEventListener);
		//
		// int actualNumberOfRulesFired = workingMemory.fireAllRules();
		// List<Activation> activations =
		// agendaEventListener.getActivationList();
		// logger.info("Constraint Checker: " +
		// Integer.toString(actualNumberOfRulesFired));
		//
		// } catch (DroolsParserException | IOException e) {
		// e.printStackTrace();
		// }

		// Check constraints
		// this.adjacentCcViolated =
		// checkForAdjacentCourseComponent(sol.getEntryList());
		// this.teacherAgendaViolated =
		// checkForOverlapTeacherAgenda(sol.getEntryList());
		// this.studentAgendaViolated = checkForOverlapStudentAgenda(trajectSet,
		// sol.getEntryList());
		// this.startDateViolated = checkForValidStartDate(sol.getEntryList());
		// this.endDateViolated = checkForValidEndDate(sol.getEntryList());
		// this.roomTypeViolated = checkRoomType(sol.getEntryList());
		// this.roomProjectorViolated =
		// checkRoomEquipmentProjector(sol.getEntryList());
		// this.roomRecorderViolated =
		// checkRoomEquipmentRecorder(sol.getEntryList());
		// this.roomSmartBoardViolated =
		// checkRoomEquipmentSMARTBoard(sol.getEntryList());
		// this.roomCapacityViolated = checkRoomsEnoughCapacity(sol);
	}

	private RuleBase initialiseDrools() throws IOException,
			DroolsParserException {
		PackageBuilder packageBuilder = readRuleFiles();
		return addRulesToWorkingMemory(packageBuilder);
	}

	private PackageBuilder readRuleFiles() throws DroolsParserException,
			IOException {
		PackageBuilder packageBuilder = new PackageBuilder();

		String ruleFile = "/com/vub/scheduler/SchedularScoreRules.drl";
		Reader reader = getRuleFileAsReader(ruleFile);

		packageBuilder.addPackageFromDrl(reader);

		return packageBuilder;
	}

	private Reader getRuleFileAsReader(String ruleFile) {
		InputStream resourceAsStream = getClass().getResourceAsStream(ruleFile);

		return new InputStreamReader(resourceAsStream);
	}

	private RuleBase addRulesToWorkingMemory(PackageBuilder packageBuilder) {
		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		Package rulesPackage = packageBuilder.getPackage();
		ruleBase.addPackage(rulesPackage);

		return ruleBase;
	}

	private WorkingMemory initializeObjects(RuleBase ruleBase, Schedular sol) {
		WorkingMemory workingMemory = ruleBase.newStatefulSession();
		workingMemory.insert(sol);

		return workingMemory;
	}

	/**
	 * Check for adjacent coursecomponents in the entry list.
	 * 
	 * @param entryList
	 *            The list of entries.
	 * @return True if there are adjacent coursecomponenets, false otherwise.
	 */
	private boolean checkForAdjacentCourseComponent(List<Entry> entryList) {
		boolean conflict = false;
		for (Entry e1 : entryList) {
			Date endDateCourse = Entry.calcEndDate(e1);
			for (Entry e2 : entryList) {
				if (!e1.equals(e2)
						&& e1.getCourseComponent().equals(
								e2.getCourseComponent())
						&& endDateCourse.compareTo(e2.getStartingDate()) == 0) {
					violations.add(new AdjacentCourseComponentCV(e1, e2));
					conflict = true;
				}
			}
		}
		// TODO : werkt nog niet met springuren (alsook in rule niet)
		return conflict;
	}

	/**
	 * Check for overlap in a teacher's agenda.
	 * 
	 * @param entryList
	 *            The list of entries.
	 * @return true if there is overlap in a teacher's agenda. False otherwise.
	 */
	private boolean checkForOverlapTeacherAgenda(List<Entry> entryList) {
		List<Pair<Long, String>> agendaTeacher = new ArrayList<Pair<Long, String>>();
		for (Entry e : entryList) {
			CourseComponent cc = e.getCourseComponent();
			String teacherName = cc.getTeachers().iterator().next()
					.getUsername();
			Long currDateStart = e.getStartingDate().getTime();
			Long currDateEnd = Entry.calcEndDate(e).getTime();

			for (Pair<Long, String> otherPair : agendaTeacher) {
				if (teacherName.equals(otherPair.second)
						&& currDateStart <= otherPair.first
						&& currDateEnd > otherPair.first) {
					return true;
				}
			}
			agendaTeacher
					.add(new Pair<Long, String>(currDateStart, teacherName));
		}

		return false;
	}

	/**
	 * Check for overlap in a student's agenda. The student agenda is declared
	 * by a traject. A traject contains of a group of courses which may not
	 * overlap.
	 * 
	 * @param trajectList
	 *            The list of trajects.
	 * @param entryList
	 *            The list of entries.
	 * @return true if there is overlap in the student agenda. False otherwise.
	 */
	private boolean checkForOverlapStudentAgenda(Set<Traject> trajectList,
			List<Entry> entryList) {
		for (Traject t : trajectList) {
			List<Long> studentAgenda = new ArrayList<Long>();
			for (Entry e : entryList) {
				if (t.getCourses().contains(e.getCourseComponent().getCourse())) {
					Long currDateStart = e.getStartingDate().getTime();
					Long currDateEnd = Entry.calcEndDate(e).getTime();

					for (Long other : studentAgenda) {
						if (currDateStart <= other && currDateEnd > other) {
							return true;
						}
					}
					studentAgenda.add(currDateStart);
				}
			}
		}
		return false;
	}

	/**
	 * Checks that a course starts after the specified start date.
	 * 
	 * @param entryList
	 *            The list of entries.
	 * @return True if all courses start after the specified start date. False
	 *         otherwise.
	 */
	private boolean checkForValidStartDate(List<Entry> entryList) {
		for (Entry e : entryList) {
			if (e.getCourseComponent().getStartingDate()
					.compareTo(e.getStartingDate()) > 0)
				return false;
		}
		return true;
	}

	/**
	 * Checks that a course ends before the specified end date.
	 * 
	 * @param entryList
	 *            The list of entries.
	 * @return True if all courses end before the specified end date. False
	 *         otherwise.
	 */
	private boolean checkForValidEndDate(List<Entry> entryList) {
		for (Entry e : entryList) {
			if (e.getCourseComponent().getEndingDate()
					.compareTo(e.getStartingDate()) < 0)
				return false;
		}
		return true;
	}

	/**
	 * 
	 * @param entryList
	 *            The list of entries.
	 * @return True if all lectures are given in the room with a correct
	 *         RoomType. False otherwise.
	 */
	private boolean checkRoomType(List<Entry> entryList) {
		for (Entry e : entryList) {
			if (e.getCourseComponent().getRoomTypeRequirement() != e.getRoom()
					.getType()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param entryList
	 *            The list of entries
	 * @return True if all lectures are given in a room with a projector if
	 *         required. False otherwise.
	 */
	private boolean checkRoomEquipmentProjector(List<Entry> entryList) {
		for (Entry e : entryList) {
			if (e.getCourseComponent().getRoomProjectorRequirement() == true
					&& e.getRoom().isProjectorEquipped() == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param entryList
	 *            The list of entries.
	 * @return True if all lectures are given in a room with a recorder if
	 *         required. False otherwise.
	 */
	private boolean checkRoomEquipmentRecorder(List<Entry> entryList) {
		for (Entry e : entryList) {
			if (e.getCourseComponent().getRoomRecorderRequirement() == true
					&& e.getRoom().isRecorderEquipped() == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param entryList
	 *            The list of entries.
	 * @return True if all lectures are given in a room with a smartboard if
	 *         required. False otherwise.
	 */
	private boolean checkRoomEquipmentSMARTBoard(List<Entry> entryList) {
		for (Entry e : entryList) {
			if (e.getCourseComponent().getRoomSmartBoardRequirement() == true
					&& e.getRoom().isSmartBoardEquipped() == false) {
				return false;
			}
		}
		return true;
	}

	private boolean checkRoomsEnoughCapacity(Schedular solution) {
		for (Entry e : solution.getEntryList()) {
			int roomCapacity = e.getRoom().getCapacity();
			int numberOfStudents = e.getCourseComponent()
					.getRoomCapacityRequirement();
			if (roomCapacity < numberOfStudents) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return the sol
	 */
	public Schedular getSol() {
		return sol;
	}

	/**
	 * @return the adjacentCcViolated
	 */
	public boolean isAdjacentCcViolated() {
		return checkForAdjacentCourseComponent(sol.getEntryList());
		// return adjacentCcViolated;
	}

	/**
	 * @return the teacherAgendaViolated
	 */
	public boolean isTeacherAgendaViolated() {
		return checkForOverlapTeacherAgenda(sol.getEntryList());
		// return teacherAgendaViolated;
	}

	/**
	 * @return the studentAgendaViolated
	 */
	public boolean isStudentAgendaViolated() {
		return checkForOverlapStudentAgenda(trajectSet, sol.getEntryList());
		// return studentAgendaViolated;
	}

	/**
	 * @return the startDateViolated
	 */
	public boolean isStartDateViolated() {
		return checkForValidStartDate(sol.getEntryList());
		// return startDateViolated;
	}

	/**
	 * @return the endDateViolated
	 */
	public boolean isEndDateViolated() {
		return checkForValidEndDate(sol.getEntryList());
		// return endDateViolated;
	}

	/**
	 * @return the roomTypeViolated
	 */
	public boolean isRoomTypeViolated() {
		return checkRoomType(sol.getEntryList());
		// return roomTypeViolated;
	}

	/**
	 * @return the roomProjectorViolated
	 */
	public boolean isRoomProjectorViolated() {
		return checkRoomEquipmentProjector(sol.getEntryList());
		// return roomProjectorViolated;
	}

	/**
	 * @return the roomRecorderViolated
	 */
	public boolean isRoomRecorderViolated() {
		return checkRoomEquipmentRecorder(sol.getEntryList());
		// return roomRecorderViolated;
	}

	/**
	 * @return the roomSmartBoardViolated
	 */
	public boolean isRoomSmartBoardViolated() {
		return checkRoomEquipmentSMARTBoard(sol.getEntryList());
		// return roomSmartBoardViolated;
	}

	/**
	 * @return the roomCapacityViolated
	 */
	public boolean isRoomCapacityViolated() {
		return checkRoomsEnoughCapacity(sol);
		// return roomCapacityViolated;
	}

}
