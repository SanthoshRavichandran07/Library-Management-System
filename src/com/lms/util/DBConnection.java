package com.lms.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import io.github.cdimascio.dotenv.Dotenv;

public class DBConnection {

	private static final HikariDataSource dataSource;
	
	// * Method 1: using .properties file.
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

	// Method 2: using .env file
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

	// Method 3: using The HikariConfig for Database Connection Pool.
	static {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();      
        String db_url = dotenv.get("DB_URL");
        String db_user = dotenv.get("DB_USERNAME");
        String db_pass = dotenv.get("DB_PASSWORD");
        
        if (db_url == null || db_user == null || db_pass == null) {
            throw new IllegalStateException("Database configuration missing. Please set DB_URL, DB_USER, and DB_PASS as environment variables.");
        }
        
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(db_url);
        config.setUsername(db_user);
        config.setPassword(db_pass);
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(30000);
        
        dataSource = new HikariDataSource(config);
    }
    
    public static DataSource getDataSource() {
        return dataSource;
    }

	/// NOTE: This main method is only for Testing the Local Database Connection.
	public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
		Connection c = null;
		try {
			System.out.println("Connecting to DB");
			Thread.sleep(1000);
			c = getDataSource().getConnection();
			System.out.println("Connected to Database");
			Thread.sleep(1000);
		} catch (SQLException e) {
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
