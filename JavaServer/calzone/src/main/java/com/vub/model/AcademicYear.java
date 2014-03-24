package com.vub.model;


public class AcademicYear {
	private int start;

	/**
	 * Initialise the academic year from the string representation of the form "start-end", for example "2013-2014"
	 * @param academicYear String representation of the form "start-end"
	 */
	public void fromString(String academicYear) {
		this.fromString(academicYear, "-");
	}
	/**
	 * Initialise the academic year from a string representation in the form of "start-end", for example "2013-2014"
	 * @param academicYear String representation, e.g. "2013-2014"
	 * @param separator Separator of the string representation, for example: "2013-2014" has separator "-"
	 */
	public void fromString(String academicYear, String separator) {
		String[] imploded = academicYear.split(separator);
		this.start = Integer.parseInt(imploded[0]);
	}
	/**
	 * 
	 * @return Returns the starting year of the academic year
	 */
	public int getStartYear() {
		return start;
	}
	/**
	 * 
	 * @return Returns the ending year of the academic year (derived from starting year)
	 */
	public int getEndingYear() {
		return start + 1;
	}
	
	/**
	 * Convert this academic year to string representation, for example "2013-2014"
	 * @return String representation for this academic year
	 */
	public String toString() {
		return Integer.toString(start) + "-" + Integer.toString(start + 1);
	}
}