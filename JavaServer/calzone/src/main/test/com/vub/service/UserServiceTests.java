package com.vub.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vub.exception.CannotActivateUserException;
import com.vub.exception.UserNotFoundException;
import com.vub.model.Key;
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
public class UserServiceTests {

	@Test
	/**
	 * Test if a user can be created in the database
	 */
	public void testUserCreation() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		
		User user = userService.createTestUser();
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
			assertEquals(user, otherUser);
		} catch (UserNotFoundException ex) {
			fail("Could not find the user we just stored in the database");
		} finally {
			userService.deleteUser(user);
			context.close();
		}
	}
	
	@Test
	/**
	 * Test if users can be activated. More specifically, test if they can't be activated with keys of the wrong type
	 * and also test if activating them works
	 */
	public void testUserActivation() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		KeyService keyService = (KeyService) context.getBean("keyService");
		UserService userService = (UserService) context.getBean("userService");
		
		// Generate a test user
		User user = userService.createTestUser();
		// Generate a password-forgot key and make sure the user cannot be activated with this key
		Key pwKey = keyService.generatePasswordForgotKey(user);
		try {
			userService.activateUser(user, pwKey);
			fail("Successfully activated user with a different key than an ActivationKey");
		} catch (CannotActivateUserException ex) {
			assertEquals(false, user.isEnabled());
		}
		
		// Now check if the user can be activated with the activation key
		Key actiKey = keyService.generateActivationKey(user);
		try {
			userService.activateUser(user, actiKey);
			assertEquals(true, user.isEnabled());
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
