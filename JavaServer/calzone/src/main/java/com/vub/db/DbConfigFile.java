package com.vub.db;

import java.io.*;

//import javax.servlet.ServletContext;

//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;

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

	private String user = "";
	private String password = "";
	private String url = "";
	
public DbConfigFile () {
		
	}
	public DbConfigFile(String fileName) {
		
		InputStream in = getClass().getResourceAsStream(fileName);
		int Byte;       // Byte because byte is keyword!
		
		try {
			
			// Byte = 10 = NEWLINE
			
			// Getting user
			
			while ((Byte = in.read()) != 10 ) {
				this.user = this.user+(char) Byte;
			}
			
			// Getting password
			
			while ((Byte = in.read()) != 10 ) {
				this.password = this.password+(char) Byte;
			}
			
			// Geting url
			
			while ((Byte = in.read()) != -1 ) {
				this.url = this.url+(char) Byte;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
        }
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUrl(String url) {
		this.url = url;
	}


	public String getUser() {
		return this.user;
	}


	public String getPassword() {
		return this.password;
	}


	public String getUrl() {
		return this.url;
	}
	
	
	public void main(String[] args){
	
	}

	@Override
	public String toString() {
		return "dbConfigFile [getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
