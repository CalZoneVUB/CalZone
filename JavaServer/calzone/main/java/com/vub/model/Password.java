package com.vub.model;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Password {
	@NotBlank(message="Cannot be empty")
	@Size(min=8, max=42, message = "Pick a password between 8-42 characters")
	private String password;
	@NotBlank(message="Cannot be empty")
	@Size(min=8, max=42, message = "Pick a password between 8-42 characters")
	private String confirmPassword;
	
	@AssertTrue(message="Both fields need to be equal")
	private boolean isValid() {
		return this.password.equals(this.confirmPassword);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
