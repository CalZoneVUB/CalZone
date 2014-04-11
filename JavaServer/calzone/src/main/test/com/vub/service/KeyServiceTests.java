package com.vub.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vub.exception.KeyNotFoundException;
import com.vub.model.Key;
import com.vub.model.User;

/**
 * Test suite which tests the 
 * @author Sam
 *
 */
public class KeyServiceTests {

	@Test
	/**
	 * Test if keys can be created and saved in the database
	 */
	public void testKeyCreation() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		KeyService keyService = (KeyService) context.getBean("keyService");
		
		// TODO - More tests
		User user = userService.createTestUser();
		Key actiKey = keyService.generateActivationKey(user);
		try {
			Key foundKey = keyService.findKey(actiKey.getKeyString());
			assertEquals(actiKey, foundKey);
		} catch(KeyNotFoundException ex) {
			fail("Key could not be stored in and retrieved from database correctly");
		} finally {
			keyService.deleteKey(actiKey);
			context.close();
		}
	}
	
	@Test
	public void testUserActivation() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		KeyService keyService = (KeyService) context.getBean("keyService");
		
	}
}
