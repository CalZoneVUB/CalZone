package com.vub.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.vub.model.Key;
import com.vub.model.MailMail;
import com.vub.model.Person;
import com.vub.model.User;

/**
 * This class provides services regarding sending emails (such as activation emails)
 * 
 * @author Sam
 *
 */
@Service("mailService")
public class MailService {
	/** 
	 * Send an activation e-mail to a certain user, given an activation key.
	 * @param user User to fetch the email from
	 * @param key Key which is required to activate the user.
	 */
	public void sendActivationMail(User user, Key key) {
		ApplicationContext mailContext = new ClassPathXmlApplicationContext("Spring-Mail.xml");
		MailMail mm = (MailMail) mailContext.getBean("mailMailActivation");
		String siteRoot = mm.getSiteRoot() + "activate/";

		Person userPerson = user.getPerson();
		mm.sendMail(userPerson.getEmail(),
				"CalZone Activation",
				userPerson.getFirstName() + " " + userPerson.getLastName(),
				siteRoot + key.getKeyString());
		
		((ClassPathXmlApplicationContext) mailContext).close(); 
	}
	
	public void sendPasswordForgotMail(User user, Key key) {
		// TODO - email isn't correct
		ApplicationContext mailContext = new ClassPathXmlApplicationContext("Spring-Mail.xml");
		MailMail mm = (MailMail) mailContext.getBean("mailMailActivation");
		String siteRoot = mm.getSiteRoot() + "passwordforgot/";

		Person userPerson = user.getPerson();
		mm.sendMail(userPerson.getEmail(),
				"CalZone Password Reset",
				userPerson.getFirstName() + " " + userPerson.getLastName(),
				siteRoot + key.getKeyString());
		
		((ClassPathXmlApplicationContext) mailContext).close(); 
	}
}
