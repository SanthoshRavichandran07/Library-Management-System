package com.lms.validation;

import java.sql.*;

import com.lms.util.DBConnection;

public class InputValidator {
	
	private static final int MIN_YEAR = 100;
	private static final int MAX_YEAR = 2026;

	private static final String NAME_PATTERN = "[A-Za-z {3,}]";
	private static final String GENRE_PATTERN = "[A-Za-z, {3,}]";
	
	 
	public boolean verifyId(int id) {
		Connection con = DBConnection.getConnection();
		String query = "SELECT id FROM book WHERE id = ?;";
		try {
			PreparedStatement prepareStatement = con.prepareStatement(query);
			prepareStatement.setInt(1,id);
			
			ResultSet result = prepareStatement.executeQuery();
			int isAvailable = 0;
			while(result.next()) {
				isAvailable = result.getInt("id");
			}
			if(isAvailable == id) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("End");
		return false;
	}
	
	public boolean validId(int id) {
		return id >0;
	}
	
	public boolean validNamePattern(String title) {		
		return isValidString(title,NAME_PATTERN);
	}
	public boolean validYear(int year) {
		return year != 0  && year >MIN_YEAR && year<MAX_YEAR; 
	}
	public boolean validGenre(String genre) {
		return isValidString(genre,GENRE_PATTERN);
	}	
	private boolean isValidString(String value,String regex) {
		return value!=null && value.matches(regex);
	}
}
