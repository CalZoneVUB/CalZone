package com.vub.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

/**
 * 
 * Class which encapsulates an e-mail address.
 * 
 * @author Tim Witters
 *
 */

@Component
public class Email {
	@NotBlank(message="Cannot be empty")
	@org.hibernate.validator.constraints.Email(message = "Not a real email adress")
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
