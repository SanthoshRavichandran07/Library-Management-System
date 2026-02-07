package com.lms.validation;

import java.sql.*;

import com.lms.util.DBConnection;

public class InputValidator {
//	public static void main(String[] args) {
//		InputValidator iv = new InputValidator();
//		iv.verifyId(10);
//	}
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

}
