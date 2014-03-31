package com.vub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TRAJECT")
public class Traject {
	 @Id
	 @GeneratedValue
	 @Column(name="TrajectID")
	 private int id;
	 
	 @Column(name="TrajectName")
	 private String trajectName;
	 
	 @Column(name="AcademicYear")
	 private int startingYear;
}
