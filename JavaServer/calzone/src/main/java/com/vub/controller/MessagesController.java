package com.vub.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vub.exception.UserNotFoundException;
import com.vub.model.CourseComponent;
import com.vub.model.Entry;
import com.vub.model.Notification;
import com.vub.model.NotificationType;
import com.vub.model.ProfileSlot;
import com.vub.model.ProfileSlot.Badge;
import com.vub.model.ProfileSlot.Color;
import com.vub.model.ProfileSlotComparator;
import com.vub.model.User;
import com.vub.service.EntryService;
import com.vub.service.NotificationService;
import com.vub.service.RoomService;
import com.vub.service.TrajectService;
import com.vub.service.UserService;

@Controller 
public class MessagesController implements MessageSourceAware {
	@Autowired
	private UserService userService;

	@Autowired
	private TrajectService trajectService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private EntryService entryService;

	@Autowired
	private NotificationService notificationService;

	private MessageSource messageSource;

	@RequestMapping(value = "/messages")
	public String sayHello(Model model, Principal principal, Locale locale) {



		try {
			User user = userService.findUserByUsername(principal.getName());
			Set<Entry> entries = userService.getAllEntries(user);
			//TODO SLECT ONLY TODAY

			List<ProfileSlot> profileSlots = new ArrayList<ProfileSlot>();
			for (Entry e : entries) {
				SimpleDateFormat df = new SimpleDateFormat("D");
				Date today = new Date();
				if ((today.getTime() - (1*60*60*1000)) < e.getStartingDate().getTime() && (today.getTime() + (30*60*60*1000)) > e.getStartingDate().getTime()) {
					ProfileSlot profileSlot = new ProfileSlot();
					Date date = e.getStartingDate();
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
					profileSlot.setIconText(sdf.format(date));
					profileSlot.setTitle(e.getCourseComponent().getCourse().getCourseName());
					List<String> strings = new ArrayList<String>();
					strings.add(e.getCourseComponent().getTeachers().iterator().next().getPerson().getFirstName() + " " + e.getCourseComponent().getTeachers().iterator().next().getPerson().getLastName());
					strings.add(e.getRoom().getVubNotation());
					profileSlot.setDescriptions(strings);
					profileSlot.setColor(Color.success);
					profileSlot.setDate(e.getStartingDate());
					profileSlots.add(profileSlot);
				}
			}

			for (Notification n : user.getNotifications()) {
				SimpleDateFormat sdf = new SimpleDateFormat("D");
				Date today = new Date();
				//System.out.println(sdf.format(today) + " and " + sdf.format(n.getDate()));
				if ((today.getTime() - (1*60*60*1000)) < n.getDate().getTime() && (today.getTime() + (30*60*60*1000)) > n.getDate().getTime()) {
					ProfileSlot profileSlot = new ProfileSlot();
					profileSlot.setBadge(Badge.bullhorn);
					profileSlot.setColor(Color.warning);
					profileSlot.setTitle("Notification");
					profileSlot.setDate(n.getDate());

					

					if (n.getType() == NotificationType.Time) {
						String str = n.getMessage()[0] + " " + messageSource.getMessage("notification.time1.text", new Object[] {}, locale) + " "
								+	n.getMessage()[1] + " " +  messageSource.getMessage("notification.time2.text" , new Object[] {}, locale) + " " 
								+	n.getMessage()[2];
						List<String> strings = new ArrayList<String>();
						strings.add(str);
						profileSlot.setDescriptions(strings);

					} else if (n.getType() == NotificationType.Room) {
						List<String> strings = new ArrayList<String>();
						String str = n.getMessage()[0] + " from room " + n.getMessage()[1] + " to " + n.getMessage()[2];
						strings.add(str);
						profileSlot.setDescriptions(strings);
					} else if (n.getType() == NotificationType.System) {
						List<String> strings = new ArrayList<String>();
						strings.add(n.getMessage()[0]);
						profileSlot.setColor(Color.danger);
						profileSlot.setDescriptions(strings);
					}

					profileSlots.add(profileSlot);}
			}



			Collections.sort(profileSlots, new ProfileSlotComparator());

			model.addAttribute("profileSlots", profileSlots);



		} catch (UserNotFoundException e) {
			model.addAttribute("profileSlots", null);
			e.printStackTrace();
		}

		return "messages";
	}

	@Override
	public void setMessageSource(MessageSource arg0) {
		this.messageSource = arg0;

	}
}
