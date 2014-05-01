package com.vub.model;

public class Notification {
	private User user;
	private NotificationType type;
	private String[] message;

	public Notification(User user, NotificationType type) {
		this.user = user;
		this.type = type;
	}
	public String[] getMessage() {
		return message;
	}
	public void setMessage(String[] message) {
		this.message = message;
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
}
