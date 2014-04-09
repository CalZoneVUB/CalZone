package com.vub.controller;

public class SelectResponse {
	private String value;
	private String text;
	
	public SelectResponse(String s1, String s2) {
		this.value = s1;
		this.text = s2;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String id) {
		this.value = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
