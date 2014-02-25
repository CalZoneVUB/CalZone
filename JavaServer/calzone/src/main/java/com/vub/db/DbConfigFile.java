package com.vub.db;

import java.io.*;

import javax.servlet.ServletContext;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/* The .txt file has to be in this format:
 * 
 * ---------------------------------------
 * db-user \n
 * db-password \n
 * db-url eof
 * ---------------------------------------
 * 
 * note: after the db-url nothing may be added ( no newline ! )
 * 
 */

public class DbConfigFile {

	public static void setUser(String user) {
		DbConfigFile.user = user;
	}
	public static void setPassword(String password) {
		DbConfigFile.password = password;
	}
	public static void setUrl(String url) {
		DbConfigFile.url = url;
	}

	private static String user = "";
	private static String password = "";
	private static String url = "";
	
	public DbConfigFile () {
		
	}
	public DbConfigFile(String fileName) {
		
		InputStream in = getClass().getResourceAsStream(fileName);
		int Byte;       // Byte because byte is keyword!
		
		try {
			
			// Byte = 10 = NEWLINE
			
			// Getting user
			
			while ((Byte = in.read()) != 10 ) {
				user = user+(char) Byte;
			}
			
			// Getting password
			
			while ((Byte = in.read()) != 10 ) {
				password = password+(char) Byte;
			}
			
			// Geting url
			
			while ((Byte = in.read()) != -1 ) {
				url = url+(char) Byte;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
        }
	}


	public static String getUser() {
		return user;
	}


	public static String getPassword() {
		return password;
	}


	public static String getUrl() {
		return url;
	}
	
	
	public static void main(String[] args){
	
	}

	@Override
	public String toString() {
		return "dbConfigFile [getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
