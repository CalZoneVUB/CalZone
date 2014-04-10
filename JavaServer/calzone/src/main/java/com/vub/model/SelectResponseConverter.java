package com.vub.model;

import java.util.ArrayList;
import java.util.List;
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
			listSelectResponse.add(new SelectResponse(listCourse.get(i).getiD(), listCourse.get(i).getCourseName()));
		}
		
		return listSelectResponse;
	}
}
