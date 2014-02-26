package com.vub.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


public class EmailMe {
	@NotBlank(message="Cannot be empty")
	@Email(message = "Not a real email adress")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Email [email=" + email + "]";
	}
	
}
