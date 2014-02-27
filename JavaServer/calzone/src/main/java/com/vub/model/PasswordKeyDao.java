package com.vub.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class PasswordKeyDao {

	private DataSource dataSource;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(PasswordKey passwordKey){
		//Based on Example
		//http://www.mkyong.com/spring/maven-spring-jdbc-example/ 
		String sql = "INSERT INTO PasswordKeys " +
				"(Identifier, CreatedOn, KeyString) VALUES (?, ?, ?)";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, passwordKey.getIdentifier());
			ps.setDate(2, passwordKey.getCreatedOn());
			ps.setString(3, passwordKey.getKeyString());
			ps.executeUpdate();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
}
