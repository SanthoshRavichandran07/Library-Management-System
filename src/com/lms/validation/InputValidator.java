package com.lms.validation;

import java.awt.Taskbar.State;
import java.sql.*;

import com.lms.util.DBConnection;

public class InputValidator {
	
	private static final int MIN_YEAR = 100;
	private static final int MAX_YEAR = 2026;
		
	private static final String YES = "yes";
	private static final String NO = "no";
	
	private static final String NAME_PATTERN = "^[A-Za-z0-9., ]{3,}$";
	private static final String GENRE_PATTERN = "^[A-Za-z,/ ]{3,}$";
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	 
	Connection con = null;
	public boolean verifyBookId(int id) {
		con = DBConnection.getConnection();
		String query = "SELECT id FROM books WHERE id = ?;";
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
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean verifyMemberId(int id) {
		con = DBConnection.getConnection();
		String query = "SELECT id FROM members WHERE id = ?;";
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
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean verifyTransactionId(int transactionId) {
		con = DBConnection.getConnection();
		String query = "SELECT id FROM transactions WHERE id = ?;";
		try {
			PreparedStatement prepareStatement = con.prepareStatement(query);
			prepareStatement.setInt(1,transactionId);
			
			ResultSet result = prepareStatement.executeQuery();
			int isAvailable = 0;
			while(result.next()) {
				isAvailable = result.getInt("id");
			}
			if(isAvailable == transactionId) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null) {
					con.close();
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public boolean checkBookAvailability(int bookId) {
		con = DBConnection.getConnection();
		String query = "SELECT availability FROM books WHERE id =?";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1,bookId);
			ResultSet rs = st.executeQuery();
			int availability = 0;
			while(rs.next()) {
				availability = rs.getInt("availability");
			}
			if(availability == bookId) {
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean validAvailability(String availability) {
		return (availability.toLowerCase().equals(YES) || availability.toLowerCase().equals(NO));
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
	
	public boolean validEmail(String email) {
		return email != null && email.matches(EMAIL_PATTERN);
	}
}
