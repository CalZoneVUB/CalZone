package com.vub.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vub.exception.CannotActivateUserException;
import com.vub.exception.UserNotFoundException;
import com.vub.model.Key;
import com.vub.model.Person;
import com.vub.model.User;
import com.vub.model.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
/**
 * Test suite which defines tests for the UserService service. 
 * This suite tests the important services the service has to offer
 * @author Sam
 *
 */
public class UserServiceTest {

	/**
	 * Create a test user with dummy data, only for testing purposes.
	 * The generated User has a Person object attached, but no other relations. 
	 * This user is not saved to the database.
	 * 
	 * @return Returns a dummy User
	 */
	private User createTestUser() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		
		Person person = new Person();
		person.setFirstName("firstname");
		person.setLastName("lastname");
		person.setEmail("person@test.com");
		person.setBirthdate(new Date());
		
		User user = new User();
		user.setPerson(person);
		user.setUsername("testusername");
		user.setPassword("testpassword");
		userService.hashPassword(user);
		
		context.close();
		return user;
	}
	
	/**
	 * Test if a user can be created in the database
	 */
	@Test
	public void testUserCreation() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		
		User user = this.createTestUser();
		// Save the test user in the database
		user = userService.createUser(user);
		// Assign a role
		userService.assignUserRole(user, UserRole.UserRoleEnum.ROLE_STUDENT);
		// Save him again.... Because he just had a role attached
		userService.updateUser(user);
		// The ID should now be filled in when the user is saved in the db
		assertNotNull(user.getId());
		try {
			User otherUser = userService.findUserByID(user.getId());
			assertEquals("Checking if the saved user and the fetched user are equal", user, otherUser);
		} catch (UserNotFoundException ex) {
			fail("Could not find the user we just stored in the database");
		} finally {
			userService.deleteUser(user);
			context.close();
		}
	}
	
	
	/**
	 * Test if users can be activated. More specifically, test if they can't be activated with keys of the wrong type
	 * and also test if activating them works
	 */
	@Test
	public void testUserActivation() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		KeyService keyService = (KeyService) context.getBean("keyService");
		UserService userService = (UserService) context.getBean("userService");
		
		// Generate a test user
		User user = this.createTestUser();
		// Generate a password-forgot key and make sure the user cannot be activated with this key
		Key pwKey = keyService.generatePasswordForgotKey(user);
		try {
			userService.activateUser(user, pwKey);
			fail("Successfully activated user with a different key than an ActivationKey");
		} catch (CannotActivateUserException ex) {
			assertEquals("Testing if the user could be activated with a key other than an activation key", false, user.isEnabled());
		}
		
		// Now check if the user can be activated with the activation key
		Key actiKey = keyService.generateActivationKey(user);
		try {
			userService.activateUser(user, actiKey);
			assertEquals("Checking if the user can be activated with a valid activation key", true, user.isEnabled());
		} catch (CannotActivateUserException ex) {
			fail("Failed to activate user with activation key");
		} finally {
			keyService.deleteKey(pwKey);
			keyService.deleteKey(actiKey);
			userService.deleteUser(user);
			context.close();
		}
	}

}
