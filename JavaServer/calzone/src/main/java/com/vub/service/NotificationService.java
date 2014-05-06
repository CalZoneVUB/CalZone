package com.vub.service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.model.Notification;
import com.vub.model.NotificationType;
import com.vub.model.User;
import com.vub.repository.NotificationRepository;

/**
 * 
 * @author Nicolas
 *
 */
@Service("notificationService")
public class NotificationService {
	@Autowired
	NotificationRepository notificationRepository;
	
	@Autowired
	UserService userService;
	
	/**
	 * Create (persist) a notification in the database
	 * @param notification
	 */
	@Transactional
	public Notification createNotification(Notification notification) {
		return notificationRepository.save(notification);
	}
	
	/**
	 * Update (persist) a notification in the database
	 * @param notification
	 */
	@Transactional
	public Notification updateNotification(Notification notification) {
		return notificationRepository.save(notification);
	}
	

	/**
	 * Find a Notification object in the database.
	 * @param id	The ID of the Notification which needs to be fetched
	 * @return	A Notification object fetched from the database
	 */
	@Transactional
	public Notification findNotificationById(int id) {
		return notificationRepository.findOne(id);
	}

	/**
	 * Delete a Notification object from the database
	 * @param notification	The Notification object one wishes to delete
	 */
	@Transactional
	public void deleteNotification(Notification notification) {
		notificationRepository.delete(notification);
	}
	
	/**
	 * List all of the entries currently present in the database	
	 * @return	List of Notification objects in the database
	 */
	@Transactional
	public Set<Notification> getEntries() {
		Set<Notification> result = new HashSet<Notification>();
		result.addAll(notificationRepository.findAll());
		return result;
	}
	
	@Transactional
	public void PublishSystemNotification(String message) {
		String[] array = new String[1];
		array[0] = message;
		for(User user : userService.getAllUsers()) {
			Notification notification = new Notification();
			notification.setType(NotificationType.System);
			notification.setDate(Calendar.getInstance().getTime());
			notification.setMessage(array);
			notification = this.createNotification(notification);
			user.getNotifications().add(notification);
			userService.updateUser(user);
		}
	}
}
