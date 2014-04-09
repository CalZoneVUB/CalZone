package com.vub.controller;

public class Select2Response {
	private String id;
	private String text;
	
	public Select2Response(String s1, String s2) {
		this.id = s1;
		this.text = s2;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
