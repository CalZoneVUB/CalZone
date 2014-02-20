package com.vub.model.tests;

// jUnit stuff
import static org.junit.Assert.*;
import org.junit.Test;


//Import the object that needs testing
import com.vub.model.User; 

public class UserTests {
	@Test
	public void constructorTest() {
		User u = new User("username_placeholder");
		assertEquals("Checking if username is correctly inserted through the constructor", "username_placeholder", u.getUserName());
	}
}