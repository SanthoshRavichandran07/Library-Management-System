package com.lms.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
public class DBConnection {
	
	public static Connection getConnection() {
		
		Connection con = null;
		
		Properties properties = new Properties();
		try {
			
			// Achieving soft coding (getting values from db.properties file)  
			FileInputStream fileInput = new FileInputStream("db.properties");
			properties.load(fileInput);
			
			String url = properties.getProperty("db.url");
			String username = properties.getProperty("db.username");
			String password = properties.getProperty("db.password");
			
			// 1. Load or Register Driver 
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. Establish Connection
			con = DriverManager.getConnection(url,username,password);
			
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
		return con;
	}

}
