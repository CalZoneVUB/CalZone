package com.vub.controller;

/**
 * @author Tim
 *
 */
/**
 * @author Tim
 * SelectResponse needed to serve X-editable Select data
 */
public class SelectResponse {
	private int value;
	private String text;
	
	/**
	 * Standard Constructor
	 */
	public SelectResponse() {
		
	}
	/**
	 * @param value representing the ID X-editable will return when selected
	 * @param string representing the displayed string of the ID
	 */
	public SelectResponse(int value, String string) {
		this.value = value;
		this.text = string;
	}
	
	/**
	 * @return Returns value of the object
	 */
	public int getValue() {
		return value;
	}
	/**
	 * Set value of the object 
	 * @param value  
	 */
	public void setValue(int value) {
		this.value = value;
	}
	/**
	 * @return Returns text field
	 */
	public String getText() {
		return text;
	}
	/**
	 * Set text of the object
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}
}
