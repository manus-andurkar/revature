package com.revature.jdbcutil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCConnection {
	
	public static Connection getConnection() {
		
		InputStream in = null;
		
		try {
			Properties props = new Properties();
			in = new FileInputStream("src/main/resources/connection.properties");
			props.load(in);
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = null;
			
//			String endpoint = props.getProperty("jdbc.url");
//			String username = props.getProperty("jdbc.username");
//			String password = props.getProperty("jdbc.password");
			
			String endpoint = "jdbc:oracle:thin:@newdbinstance.ctly377z2znq.us-east-1.rds.amazonaws.com:1521:ORCL";
			String username = "manus8";
			String password = "835?4fXw";
			
			conn = DriverManager.getConnection(endpoint, username, password);
			
			return conn;
			
		} catch (Exception e) {
			e.getMessage();
		}
		
		return null;
	}

}
