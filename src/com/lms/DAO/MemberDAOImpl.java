package com.lms.DAO;

import com.lms.util.DBConnection;

import java.sql.*;

import com.lms.model.Members;

public class MemberDAOImpl{

	Connection con = null;

	public void addMember(Members member) {
		con = DBConnection.getConnection();
		String query = "INSERT INTO members (name, email, role) VALUES (?,?,?)";
		try (PreparedStatement ps = con.prepareStatement(query);) {
			ps.setString(1, member.getName());
			ps.setString(2, member.getEmail());
			ps.setString(3, member.getRole());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateMember(int id, Object value, String query) {
		try {
			PreparedStatement ps = con.prepareStatement(query);
			if (value instanceof String) {
				ps.setString(1, (String) value);
			} else if (value instanceof Integer) {
				ps.setInt(1, (Integer) value);
			}
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	
	public void deleteMember(int id) {
		
		
	}

	
	public void searchMember(Object value, String query) {
		con = DBConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(query);
			if (value instanceof String) {
				ps.setString(1, (String) value);
			} else if (value instanceof Integer) {
				ps.setInt(1, (Integer) value);
			}
			ResultSet rs = ps.executeQuery();

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(rs.getInt("id") + " | " + 
								   rs.getString("name") + " | " + 
						           rs.getString("email")+ " | " +  
								   rs.getString("role"));
			}

			if (!hasBooks) {
				System.out.println("No books found.");
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	public void viewAllMembers() {
		con = DBConnection.getConnection();
		String query = "SELECT * FROM members ORDER BY id";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(rs.getInt("id") + " | " + 
								   rs.getString("name") + " | " + 
						           rs.getString("email")+ " | " +  
								   rs.getString("role"));
			}

			if (!hasBooks) {
				System.out.println("No Members found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void viewMemberByName(String name) {
		
		con = DBConnection.getConnection();
		String query = "SELECT * FROM members WHERE name LIKE ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, "%" + name + "%");
			ResultSet rs = ps.executeQuery();

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(rs.getInt("id") + " | " + 
						   rs.getString("name") + " | " + 
				           rs.getString("email")+ " | " +  
						   rs.getString("role"));
			}

			if (!hasBooks) {
				System.out.println("No Members found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void viewMemberByEmail(String email) {
		con = DBConnection.getConnection();
		String query = "SELECT * FROM members WHERE email LIKE ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, "%" + email + "%");
			ResultSet rs = ps.executeQuery();

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(rs.getInt("id") + " | " + 
						   rs.getString("name") + " | " + 
				           rs.getString("email")+ " | " +  
						   rs.getString("role"));
			}

			if (!hasBooks) {
				System.out.println("No Members found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void viewMemberByRole(String role) {
		con = DBConnection.getConnection();
		String query = "SELECT * FROM members WHERE role LIKE ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, "%" + role + "%");
			ResultSet rs = ps.executeQuery();

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(rs.getInt("id") + " | " + 
						   rs.getString("name") + " | " + 
				           rs.getString("email")+ " | " +  
						   rs.getString("role"));
			}

			if (!hasBooks) {
				System.out.println("No Members found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
