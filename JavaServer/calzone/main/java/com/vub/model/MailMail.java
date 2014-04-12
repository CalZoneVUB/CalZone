package com.vub.model;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
 
public class MailMail
{
	private MailSender mailSender;
	private SimpleMailMessage simpleMailMessage;
	private String siteRoot;
	
	public String getSiteRoot() {
		return siteRoot;
	}

	public void setSiteRoot(String siteRoot) {
		this.siteRoot = siteRoot;
	}

	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(String to, String subject, String name, String link) {

		SimpleMailMessage message = new SimpleMailMessage(simpleMailMessage);
		message.setText(String.format(simpleMailMessage.getText(), name, link)); //Adding parameters to message template
		message.setTo(to);
		message.setSubject(subject);
		mailSender.send(message);
		
	}
}
