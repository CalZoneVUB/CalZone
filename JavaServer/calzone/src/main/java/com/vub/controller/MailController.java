package com.vub.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MailController {

	@RequestMapping(value = "/mail", method = RequestMethod.GET)
	public String initMail() {
		// "
		//SendMail("se2-1314@wilma.vub.ac.be", "pieter.meiresone@gmail.com", "xdqe481dfq4e8f41h4jk48u");
		//SendMail("pieter.meiresone@gmail.com", "pieter.meiresone@gmail.com", "xdqe481dfq4e8f41h4jk48u");
		SendMail("pieter.meiresone@vub.ac.be", "pieter.meiresone@gmail.com", "fsqgoiu87954rter87541rt");
		return "mail";
	}

	public void SendMail(String to, String from, String activationKey) {
		// Assuming you are sending email from wilma.vub.ac.be
		String host = "wilma.vub.ac.be";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));

			// Set Subject: header field
			message.setSubject("Automatische mail.");

			// Now set the actual message
			String messageText = "Gelieve op onderstaande link te klikken voor u account te activeren: \n\n";
			messageText += "http://localhost:8080/activate/";
			messageText += activationKey;
			messageText += "\n\n\n";
			messageText += "Met vriendelijke groeten, \n";
			messageText += "Het Calzone team.";
			message.setText(messageText);

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}