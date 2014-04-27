package com.vub.scheduler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.ArrayList;
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
import com.vub.model.Room;
import com.vub.model.Traject;
import com.vub.model.User;
import com.vub.model.CourseComponent.CourseComponentType;
import com.vub.model.Room.RoomType;
import com.vub.service.CourseService;
import com.vub.service.RoomService;
import com.vub.service.TrajectService;

/**
 * 
 * @author youri
 * 
 */
public class SchedulerBenchmarker {

	/**
	 * This method generates an XML file wherein an instance of the class {@link com.vub.scheduler.Schedular Schedular} has been serialized.
	 * This XML file will be used for the OptaPlanner Benchmarker.
	 * 
	 * 
	 */
	public static void makeXml(){
		// file names 
		String filePath = "src/main/java/com/vub/scheduler/schedule-1.xml";
		
		/* TODO: Use this when Services work as they MUST work
		// open services
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseService courseService = (CourseService) context.getBean("courseService");
		TrajectService trajectService = (TrajectService) context.getBean("trajectService");
		RoomService roomService = (RoomService) context.getBean("roomService");

		// fetch the necessary data from the database via services
		Traject traject = trajectService.getTrajects().iterator().next();
		traject = trajectService.findTrajectByIdInitialized(traject.getId());
		List<Room> rooms = new ArrayList<>(roomService.getRooms());

		// create additional necessary data
		List<Integer> weeks = new ArrayList<>();
		for(int i = 1; i<35; ++i){
			weeks.add(new Integer(i));
		}
		List<Date> dates = SchedulerInitializer.createSlotsOfTerm(2014, weeks);
		Set<Traject> trajects = new HashSet<Traject>();
		trajects.add(traject);
		 */



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
		SchedularSolver solver = new SchedularSolver(startDateList, roomList, trajectSet);
		solver.createEntryList(SchedularSolver.getTrajectList());
		Schedular initialSolution = solver.createSchedular();

		// create XStream and serialize to XML
		XStream xstream = new XStream();
		//xstream.alias("Schedular", Schedular.class);
		String result = xstream.toXML(initialSolution);

		// write result to file
		try {
			FileWriter fw;
			fw = new FileWriter(filePath);
			fw.write(result);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* TODO: Use this when Services work as they MUST work
		finally{
			context.close();
		}
		 */
	}
	public static void main(String [ ] args){
		makeXml();
		PlannerBenchmarkFactory plannerBenchmarkFactory = new FreemarkerXmlPlannerBenchmarkFactory(
				"/com/vub/scheduler/SchedulerBenchmarkConfig.xml.ftl");
		PlannerBenchmark plannerBenchmark = plannerBenchmarkFactory.buildPlannerBenchmark();
		plannerBenchmark.benchmark();
	}
}
