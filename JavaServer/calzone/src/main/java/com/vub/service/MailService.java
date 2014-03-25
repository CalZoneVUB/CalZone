package com.vub.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.vub.model.Key;
import com.vub.model.MailMail;
import com.vub.model.Person;
import com.vub.model.User;

@Service("mailService")
public class MailService {
	public void sendActivationMail(User user, Key key) {
		ApplicationContext mailContext = new ClassPathXmlApplicationContext("Spring-Mail.xml");
		MailMail mm = (MailMail) mailContext.getBean("mailMailActivation");
		String siteRoot = mm.getSiteRoot() + "activate/";

		Person userPerson = user.getPerson();
		mm.sendMail(userPerson.getEmail(),
				"CalZone Activation",
				userPerson.getFirstName() + " " + userPerson.getLastName(),
				siteRoot + key.getKey());
		
		((ClassPathXmlApplicationContext) mailContext).close(); 
	}
}
