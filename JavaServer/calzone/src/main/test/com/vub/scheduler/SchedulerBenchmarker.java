package com.vub.scheduler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.optaplanner.benchmark.api.PlannerBenchmark;
import org.optaplanner.benchmark.api.PlannerBenchmarkFactory;
import org.optaplanner.benchmark.config.FreemarkerXmlPlannerBenchmarkFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.thoughtworks.xstream.XStream;
import com.vub.model.CourseComponent;
import com.vub.model.CourseComponent.CourseComponentType;
import com.vub.model.Room;
import com.vub.model.Room.RoomType;
import com.vub.model.Traject;
import com.vub.model.User;
import com.vub.service.RoomService;
import com.vub.service.TrajectService;

/**
 * This class can generate several datasets. 
 * These data sets are XML files containing a serialization of 
 * a {@link Scheduler Schedule} Object, being a planning solution.
 * 
 * This class contains a main method where the Optaplanner Benchmarker is started.
 * 
 * @author youri
 * 
 */
public class SchedulerBenchmarker {
	/**
	 * Generates a data set
	 */
	private static void makeXml3(){
		// file name 
		String filePath = "src/main/test/com/vub/scheduler/schedule-3.xml";
		
	}
	/**
	 * Generates a data set where a traject from the database will be scheduled for a period of 3 weeks.
	 * This is an average data set
	 */
	private static void makeXml2(){
		//make a file name
		String filePath = "src/main/test/com/vub/scheduler/schedule-2.xml";
		
		// open services
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		TrajectService trajectService = (TrajectService) context.getBean("trajectService");
		RoomService roomService = (RoomService) context.getBean("roomService");

		// fetch the necessary data from the database via services
		//Set<Traject> trajects = trajectService.getTrajectsInitialized();
		Traject traject = trajectService.findTrajectByIdInitializedFull(177);
		
		List<Room> roomList = new ArrayList<>(roomService.getRooms());

		// create additional necessary data
		List<Integer> weeks = new ArrayList<>();
		for(int i = 1; i<6; ++i){
			weeks.add(new Integer(i));
		}
		List<Date> startDateList = SchedulerInitializer.createSlotsOfTerm(2014, weeks);
		
		Set<Traject> trajectSet = new HashSet<Traject>();
		trajectSet.add(traject);

		// create the solution class we want to serialize
		SchedulerSolver solver = new SchedulerSolver(startDateList, roomList, trajectSet);
		Scheduler initialSolution = solver.getScheduler();

		//serialize to XML
		String result =  toXml(initialSolution);

		// write result to file
		writeXmlToFile(result, filePath);
		context.close();
	}

	/**
	 * Generates a small dataset. 
	 * 10 course components
	 * 2 rooms
	 * slots for 1 week
	 */
	private static void makeXml1(){
		// file name 
		String filePath = "src/main/test/com/vub/scheduler/schedule-1.xml";

		List<Date> startDateList = SchedulerInitializer.createSlotsOfWeek(2014,
				5);

		Room standardRoom = Helper.createRoom(40, true, true, true,
				RoomType.ClassRoom);
		Room computerRoom = Helper.createRoom(40, true, true, true,
				RoomType.ComputerRoom);
		List<Room> roomList = Arrays.asList(standardRoom, computerRoom);

		// Create Courses
		HashSet<User> tchMech = Helper.createTeachers("Dirk Lefeber");
		HashSet<User> tchAnalyse = Helper.createTeachers("Stefaan Canepeel");
		HashSet<User> tchAlgebra = Helper.createTeachers("Philippe Cara");
		HashSet<User> tchChemie = Helper.createTeachers("Rudi Whillem");
		HashSet<User> tchInformatica = Helper.createTeachers("Ann Dooms");

		List<CourseComponent> ccList = new ArrayList<CourseComponent>();

		ccList.add(Helper.createCourseComponent(tchMech, 30, 4, 2,
				CourseComponentType.HOC));
		ccList.add(Helper.createCourseComponent(tchMech, 30, 4, 4,
				CourseComponentType.WPO));
		ccList.add(Helper.createCourseComponent(tchAnalyse, 30, 4, 2,
				CourseComponentType.HOC));
		ccList.add(Helper.createCourseComponent(tchAnalyse, 30, 4, 4,
				CourseComponentType.WPO));
		ccList.add(Helper.createCourseComponent(tchAlgebra, 30, 4, 2,
				CourseComponentType.HOC));
		ccList.add(Helper.createCourseComponent(tchAlgebra, 30, 4, 4,
				CourseComponentType.WPO));
		ccList.add(Helper.createCourseComponent(tchChemie, 30, 4, 2,
				CourseComponentType.HOC));
		ccList.add(Helper.createCourseComponent(tchChemie, 30, 4, 2,
				CourseComponentType.WPO));
		ccList.add(Helper.createCourseComponent(tchInformatica, 30, 2, 2,
				CourseComponentType.HOC));
		ccList.add(Helper.createCourseComponent(tchInformatica, 30, 4, 4,
				CourseComponentType.WPO, false, false, false,
				RoomType.ComputerRoom));

		Set<Traject> trajectSet = Helper.createTraject(ccList);

		// create the solution class we want to serialize
		SchedulerSolver solver = new SchedulerSolver(startDateList, roomList, trajectSet);
		Scheduler initialSolution = solver.getScheduler();

		//serialize to XML
		String result =  toXml(initialSolution);

		// write result to file
		writeXmlToFile(result, filePath);
	}

	private static String toXml(Object obj){
		XStream xstream = new XStream();
		xstream.setMode(XStream.ID_REFERENCES);
		String result = xstream.toXML(obj);
		return result;
	}

	private static void writeXmlToFile(String xml, String filePath){
		try {
			FileWriter fw;
			fw = new FileWriter(filePath);
			fw.write(xml);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 */
	public static void generateDataSets(){
		//makeXml1();
		//makeXml2();
		makeXml3();
	}

	/**
	 * Main method for starting the benchmarker (and generating the datasets)
	 * 
	 * @param args command line arguments. Won't have an effect on the method.
	 */		
	public static void main(String [ ] args){
		//generateDataSets();
		
		PlannerBenchmarkFactory plannerBenchmarkFactory = new FreemarkerXmlPlannerBenchmarkFactory(
				"/com/vub/scheduler/SchedulerBenchmarkConfig.xml.ftl");
		PlannerBenchmark plannerBenchmark = plannerBenchmarkFactory.buildPlannerBenchmark();
		plannerBenchmark.benchmark();
		//
	}
}