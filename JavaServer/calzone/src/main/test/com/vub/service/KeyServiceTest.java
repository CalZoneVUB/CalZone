package com.vub.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vub.exception.KeyNotFoundException;
import com.vub.model.Key;
import com.vub.model.Person;
import com.vub.model.User;

/**
 * Test suite which tests the 
 * @author Sam
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class KeyServiceTest {

	/**
	 * Create a test user with dummy data, only for testing purposes.
	 * The generated User has a Person object attached, but no other relations. 
	 * This user is not saved to the database.
	 * <ul>
	 * 	<li>Person.FirstName: "firstname"</li>
	 * 	<li>Person.LastName: "lastname"</li>
	 * 	<li>Person.Email: "person@test.com"</li>
	 * 	<li>Person.Birthdate: the current date of creation</li>
	 * 	<li>SupportedLanguage: default (usually EN_UK)</li>
	 * 	<li>Enabled: default (usually false)</li>
	 * 	<li>Username: "testusername"</li>
	 * 	<li>Password: "testpassword" (hashed)</li>
	 * </ul>
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
	 * Test if keys can be created and saved in the database
	 */
	@Test
	public void testKeyCreation() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		KeyService keyService = (KeyService) context.getBean("keyService");
		
		User user = this.createTestUser();
		Key actiKey = keyService.generateActivationKey(user);
		try {
			Key foundKey = keyService.findKey(actiKey.getKeyString());
			assertEquals("Checking if the saved key and the fetched key are equal", actiKey, foundKey);
		} catch(KeyNotFoundException ex) {
			fail("Key could not be stored in and retrieved from database correctly");
		} finally {
			keyService.deleteKey(actiKey);
			// Check if the key has been successfully deleted
			try {
				keyService.findKey(actiKey.getKeyString());
				fail("Didn't delete the key from the database correctly");
			} catch (KeyNotFoundException ex) {
				
			}
			context.close();
		}
	}
}
