package com.vub.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.vub.validators.ValidEmail;


/** 
 * 
 * 
 * @author Nicolas
 *
 */
@Entity
@Table(name="Persons")
public class Person {
	@Id
	@Column(name="PersonID")
	@GeneratedValue
	private int id;
	@NotBlank(message = "Cannot be empty")
	@Column(name="FirstName")
	private String firstName;
	@NotBlank(message = "Cannot be empty")
	@Column(name="LastName")
	private String lastName;
	@NotBlank(message = "Cannot be empty")
	@Email(message = "Not a real email adress")
	@ValidEmail(message = "Email already exist.")
	@Column(name="Email")
	private String email;
	@Column(name="Birthdate")
	private Date birthdate; // TODO naar date ?!
	
	////////////////////////////////////////////////////////
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	
	////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @return	Gets the ID of the person
	 */
	public int getId() {
		return id;
	}
}
