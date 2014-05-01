package com.vub.model;

import java.util.Arrays;

public class XeditCheckbox {
	private String name;
	private int id;
	private String[] value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String[] getValue() {
		return value;
	}
	public void setValue(String[] value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "XeditCheckbox [name=" + name + ", id=" + id + ", value="
				+ Arrays.toString(value) + "]";
	}
	
	
	
	
	
}
