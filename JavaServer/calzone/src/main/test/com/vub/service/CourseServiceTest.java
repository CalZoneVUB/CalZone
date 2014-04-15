package com.vub.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vub.exception.CourseNotFoundException;
import com.vub.model.Course;
import com.vub.model.CourseComponent;
import com.vub.model.CourseData;
import com.vub.model.Room;

public class CourseServiceTest {

	/**
	 * @return Creates a CourseData object with all fields filled in
	 */
	private CourseData createTestCourseData() {
		CourseData cd = new CourseData();
		// Generate a very large text (which should be realistic)
		String largeDescription = 
				"Lorem ipsum dolor sit amet, in erroribus dignissim dissentias quo, te putent essent appareat pro. Corpora reprehendunt cu mea,"
				+ " ea pri veniam verterem assueverit. An meis nominavi reformidans est, mandamus patrioque molestiae ad mel. "
				+ "Ius tota zril epicuri ne, erant aliquip gloriatur sed an. Regione oporteat ad vel, dolorum noluisse mnesarchum qui ad."
				+ " Modus causae tractatos nec ei. Cum an everti nostrud diceret, ne pro porro cetero abhorreant. Sed sale consequuntur ad, "
				+ "cu eos solet everti democritum. Utinam munere detraxit at sit, vim ex causae meliore consequat. An mollis vivendum mei. "
				+ "Ea iusto nonumy pri, fabellas principes eu mei. Munere labitur temporibus est te. Has fabulas inimicus petentium id. "
				+ "Congue ignota voluptua id sed, quo id graece lucilius. At pro verear contentiones, et liber dolorem assentior duo. "
				+ "Doctus principes forensibus no usu, agam nominati eos te. At sit pericula elaboraret theophrastus, ne harum detraxit nam, "
				+ "zril saepe ne his. Mei ut ponderum constituam inciderint, duis illud exerci ne sed. Oblique officiis electram ex mea, "
				+ "vim ad sonet fabellas. Partem repudiare ex mea, est aperiam oporteat definitiones at. Ne ullum scripserit usu. "
				+ "Ea sea dicta adipisci. Ad sit liber perpetua, duis appetere mel ea. Sed ei veri decore, diam populo viderer est ut."
				+ " Ei deseruisse expetendis per, has enim persius te, te modo modus eam. His eu soleat adolescens honestatis, "
				+ "civibus adipiscing eu pro. Usu ex amet sonet vocent, vel in suas option. Duo ea melius voluptua, vis id putent mediocrem, "
				+ "sea at volutpat scriptorem. Ei graeco scripserit qui, eu choro clita sea. Ut quodsi feugiat mea. "
				+ "Qui deseruisse constituam in, at magna alterum conceptam vim, odio evertitur dissentiunt an quo. "
				+ "Pro te senserit assentior voluptatum. Vis deserunt conceptam incorrupte no, duo et doming facilis. "
				+ "Mea cu detraxit prodesset, eius malorum intellegam his at. Quot veniam dissentiet has ea, affert soluta expetendis vix ne. "
				+ "Id pro solet reprimique, ad mundi congue prodesset mel. Ad legere scribentur vim, eius oporteat est ei, "
				+ "facilisi incorrupte ea sea. Erant utroque mel ei, vivendum explicari appellantur in quo. Solet putant urbanitas vis ut, "
				+ "persius comprehensam vel ne. Laoreet hendrerit pri an, sapientem efficiantur duo ad, ex admodum percipitur usu. "
				+ "Quo ea minim semper, vis quas vituperata dissentias ex. Eos ea quod solet. Vim nihil neglegentur an. Voluptua corrumpit te eum, "
				+ "et partem elaboraret usu, his an facete numquam. In pri vidit pertinax, ad qui probo partiendo reprimique. "
				+ "An has minim eligendi electram, at mei iisque facilis. Sea simul iisque appellantur ad. Et qui agam utinam causae, "
				+ "tincidunt delicatissimi nam ad, esse reque perfecto te vim. In quando saperet mea, id eos alia legere aliquip, "
				+ "brute posse putant ut vix. Ex purto quaestio eum, malis latine intellegam sea no. Has at volumus rationibus, "
				+ "facilis repudiandae mel id. Ex cum justo velit argumentum. Vim te minimum ullamcorper, mundi quidam dolores ut ius.";
		cd.setDescription(largeDescription);
		cd.setECTS(60);
		cd.setGrading(largeDescription);
		cd.setLanguage("random language");
		cd.setLearningGoals(largeDescription);
		cd.setReexaminationPossible(true);
		return cd;
	}
	
	/**
	 * 
	 * @return Creates a test CourseComponent with all data filled in
	 */
	private CourseComponent createTestCourseComponent() {
		CourseComponent cp = new CourseComponent();
		cp.setContactHours(160);
		cp.setDuration(2);
		cp.setStartingDate(new Date());
		cp.setEndingDate(new Date());
		cp.setRoomCapacityRequirement(0);
		cp.setRoomProjectorRequirement(true);
		cp.setRoomRecorderRequirement(true);
		cp.setRoomSMARTBoardRequirement(true);
		cp.setRoomTypeRequirement(Room.RoomType.ComputerRoom);
		cp.setTerm(CourseComponent.CourseComponentTerm.S1);
		cp.setType(CourseComponent.CourseComponentType.HOC);
		return cp;
	}
	/**
	 * Test if courses (with CourseData and CourseComponents) can be created and fetch from the database
	 */
	@Test
	public void testCourseCreation() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseService courseService = (CourseService) context.getBean("courseService");
		
		CourseData courseData = this.createTestCourseData();
		CourseComponent courseComponent = this.createTestCourseComponent();
		Course course = new Course();
		course.setCourseName("testcoursename");
		
		course.setCourseData(courseData);
		course.getCourseComponents().add(courseComponent);
		
		course = courseService.createCourse(course);
		try {			
			Course fetchedCourse = courseService.findCourseByIdInitialized(course.getId());
			assertEquals("Checking if the fetched course equals the persisted course", true, course.equals(fetchedCourse));
			// Now check risk fields
			assertEquals("Checking if the entire description was stored completely", 
						 courseData.getDescription(), fetchedCourse.getCourseData().getDescription());
			assertEquals("Checking if the entire learning goals have been saved", 
					courseData.getLearningGoals(), fetchedCourse.getCourseData().getLearningGoals());
			assertEquals("Checking if the grading has been saved entirely", 
					courseData.getGrading(), fetchedCourse.getCourseData().getGrading());
		} catch (CourseNotFoundException ex) {
			fail("Could not fetch the persisted course back from the database");
		} finally {
			courseService.deleteCourse(course);
			context.close();
		}
	}

}
