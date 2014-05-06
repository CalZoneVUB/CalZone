package com.vub.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "NOTIFICATION")
public class Notification {
	@Id
	@GeneratedValue
	@Column(name = "NotificationID")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name = "UserID")
	private User user;
	
	@Column(name="NotificationType")
	private NotificationType type;
	
	@Column(name="Date")
	private Date date;

	@Column(name="Message")
	private String[] message;
	
	public Notification() {
		
	}
	
	public Notification(User u, NotificationType type) {
		this.user = u;
		this.type = type;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 * @return User of the corresponding notification
	 */
	public User getUser() {
		return user;
	}
	/**
	 * Sets user of notification
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Get type of notification
	 * @return NotificationType
	 */
	public NotificationType getType() {
		return type;
	}
	/**
	 * Set type of notification
	 * @param type
	 */
	public void setType(NotificationType type) {
		this.type = type;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the message
	 */
	public String[] getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String[] message) {
		this.message = message;
	}
}
