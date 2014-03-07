package com.vub.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vub.model.Globals;

public class DbLink {
	
	// private static DbConfigFile dbconfig = new DbConfigFile("/Wilmadbconfig.txt");
	
    static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
	private static DbConfigFile dbconfig = (DbConfigFile) context.getBean("dbConfig");
	private String db_user = dbconfig.getUser();
	private String db_password = dbconfig.getPassword();
	private String url = dbconfig.getUrl();
	
	private Connection db_connection;
	
	public Statement stmt = null;
	public ResultSet rs = null;
	public ResultSetMetaData rsmd = null;
	
	public ResultSet executeSqlQuery(String sql) {
		try {
			return this.stmt.executeQuery(sql);
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			if (Globals.DEBUG == 1) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
            return null;
		}
	}
	
	public void executeSql(String sql) {
		try {
			this.stmt.execute(sql);
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			if (Globals.DEBUG == 1) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
		}
	}
	
	public void closeConnection() {
		try {
			//this.rs.close();
			//this.stmt.close();
			this.db_connection.close();
		} catch (SQLException ex) {
			// handle the error
			if (Globals.DEBUG == 1) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
		}
	}
	
	public void openConnection() {
        try {
        	
        	this.db_connection = DriverManager.getConnection(url,db_user,db_password);
        	this.stmt = this.db_connection.createStatement(ResultSet.FETCH_FORWARD, ResultSet.CONCUR_READ_ONLY);
        	
        	// rs = executeSqlQuery("SELECT * FROM Persons;");
        	// test ( bij elk nieuw gebruik van stmt wordt rs geclosed !! )
        	// while(rs.next()){
        	//  	System.out.println(rs.getString(2)+" "+rs.getString(3));
        	// }
        	// rs.beforeFirst();
        	// rsmd = rs.getMetaData();
        	// int nrOfCol = rsmd.getColumnCount();
        	// System.out.println("Column count of table: "+nrOfCol);

        	
        } catch (Exception ex) {
            // handle the error
        	if (Globals.DEBUG == 1) {
        		System.out.println("SQLException: " + ex.getMessage());
        		System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
        		System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
        	}
        }
    }
	
	public static void main(String[] args) {
		
	}
}