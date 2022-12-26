package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		if(connection != null && !connection.isClosed()) { // isClosed could throw a sql exception 
			return connection;
		} else {
			
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}// DONT DELETE COMMENT-> jdbc:postgresql://localhost:5433/projecttwo" this is so I cannot connect to my local db
			// making it 5432 for now    
			String url = "jdbc:postgresql://localhost:5432/postgres";
			// I think this password for the connection is fine for now 
			String username = "postgres"; //
			String password = "password"; // 
										
			
			connection = DriverManager.getConnection(url, username, password);
			
			return connection;
		} 
	}
}
