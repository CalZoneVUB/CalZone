package com.vub.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.vub.controller.SelectResponse;

/**
 * @author Tim
 * Different converters to convert to/from x-editable SelectResponse Object 
 */
public class SelectResponseConverter {
	
	/**
	 * Converter to extraxt all Course id's and names to the SelectResponse format
	 * @param listCourse
	 * @return list of SelectResponss usable by x-editable
	 */
	public List<SelectResponse> classesToSelectResponse(List<Course> listCourse) {
		List<SelectResponse> listSelectResponse = new ArrayList<SelectResponse>();
		
		for(int i=0;i<listCourse.size();i++) {
			listSelectResponse.add(new SelectResponse(listCourse.get(i).getId(), listCourse.get(i).getCourseName()));
		}
		
		return listSelectResponse;
	}

	public List<SelectResponse> trajectsToSelectResponse(List<Traject> listTraject) {
		List<SelectResponse> listSelectResponse = new ArrayList<SelectResponse>();
		
		for(int i=0;i<listTraject.size();i++) {
			listSelectResponse.add(new SelectResponse(listTraject.get(i).getId(), listTraject.get(i).getTrajectName()));
		}
		
		return listSelectResponse;
	}
	
	/**
	 * Convert all users to a format of SelectResponse
	 * @param teachers - List<User>
	 * @return - return list<SelectResponse>
	 */
	public List<SelectResponse> usersToSelectResponse(Set<User> teachers) {
		List<SelectResponse> listSelectResponses = new ArrayList<SelectResponse>();

		for (User u: teachers) {

			listSelectResponses.add(new SelectResponse(u.getId(), u.getPerson().getFirstName() + " " + u.getPerson().getLastName()));
		}
		return listSelectResponses;
	}
}
