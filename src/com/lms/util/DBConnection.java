package com.lms.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import io.github.cdimascio.dotenv.Dotenv;

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

	/**
	 * Retrieving the Database Configurations from Environment file for security purpose.
	 * Using the Database Connection for better efficiency.
	 */
	public static Connection connectDB() throws ClassNotFoundException, SQLException {
		Dotenv dotenv = Dotenv.load();
		String URL = dotenv.get("DB_URL");
		String USER = dotenv.get("DB_USERNAME");
		String PASS = dotenv.get("DB_PASSWORD");
		// System.out.println(URL + " " + USER + " " + PASS); // * Uncomment this line for logging purpose.
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, PASS);
	}

	/// NOTE: This main method is only for Testing the Local Database Connection.
	public static void main(String[] args) throws InterruptedException {
		Connection c = null;
		try {
			System.out.println("Connecting to DB");
			Thread.sleep(1000);
			c = connectDB();
			System.out.println("Connected to Database");
			Thread.sleep(1000);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if ( c != null ) {
					c.close();
				    System.out.println("Database Disconnected!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
