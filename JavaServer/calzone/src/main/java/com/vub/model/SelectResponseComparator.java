package com.vub.model;

import java.util.Comparator;

import com.vub.controller.SelectResponse;

/**
 * 
 * @author Tim
 *
 */
public class SelectResponseComparator  implements Comparator<SelectResponse> {

	@Override
	public int compare(SelectResponse o1, SelectResponse o2) {
		return o1.getText().compareTo(o2.getText());
	}

}
