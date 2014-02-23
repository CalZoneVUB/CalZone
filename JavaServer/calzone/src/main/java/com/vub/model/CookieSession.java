package com.vub.model;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;

public class CookieSession {
	private Session session = new Session();
	private User user = new User();
	public CookieSession(Cookie[] cookies){
		if (cookies == null) {
			System.out.println("No cookies found in browser");
			setSession(null);
		} else {
			for (Cookie retrieveCookie : cookies) {
				String cookieName = retrieveCookie.getName();
				String cookieValue = retrieveCookie.getValue();
				if (cookieName.equals("CalzoneSessionKey")) {
					System.out.println("Calzone Cookie with name: " + cookieName + " and value: " + cookieValue);
					SessionDao sessionDao = new SessionDao();
					String userName = sessionDao.findBySessionKey(cookieValue).getUserName();
					UserDao userDao = new UserDao();
					this.user = userDao.findByUserName(userName);	
					this.session.setSessionKey(cookieValue);
					this.session.setUserName(userName);
				}
				else {
					System.out.println("Other Cookie with name: " + cookieName + " and value: " + cookieValue);
				}
			}
		}
	}

	public boolean hasAccess(String userName) {
		System.out.println("Autentication of session: " + this.session.getUserName() + " with user: " + userName);
		if (this.session.getUserName().equals(userName)) {
			System.out.println("Access Granted");
			return true;
		}
		else {
			System.out.println("Access Denied");
			return false;
		}
	}
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
