package com.lms.dao;

import java.sql.*;
import java.sql.SQLException;

import com.lms.model.Transactions;
import com.lms.util.DBConnection;

public class TransactionDAOImpl implements TransactionDAO {

	Connection con = null;

	@Override
	public void issueBook(Transactions transaction) {
		con = DBConnection.getConnection();
		String query = "INSERT INTO transactions(book_id, member_id, issue_date, return_date, status) VALUES(?,?,NOW(),DATE_ADD(NOW(), INTERVAL 15 DAY),'ISSUED')";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, transaction.getBookId());
			ps.setInt(2, transaction.getMemberId());
			ps.executeUpdate();
			System.out.println("Book issued successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void returnBook(Transactions transaction) {
		con = DBConnection.getConnection();
		String query = "UPDATE transactions SET return_date=NOW(), status='RETURNED' WHERE id=?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, transaction.getId());
			ps.executeUpdate();
			System.out.println("Book returned successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void viewTransactions() {
		con = DBConnection.getConnection();
		String query = "SELECT * FROM transactions";
		try {
			Statement st = con.createStatement();
			boolean hasValue = false;
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				hasValue = true;
				System.out.println(rs.getInt("id") + " | " + 
								   rs.getInt("book_id") + " | " + 
								   rs.getInt("member_id")+ " | " + 
								   rs.getString("issue_date") + " | " + 
								   rs.getString("return_date") + " | " +
						           rs.getString("status"));
			}
			if (!hasValue) {
				System.out.println("No Transactions done...!");
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

	@Override
	public void viewMemberTransactions(int id) {

		con = DBConnection.getConnection();
		String query = "SELECT t.id As transaction_id, b.id As book_id, b.title, b.author, b.yearPublished, t.issue_date, t.return_date, t.status\r\n"
				+ "FROM transactions t \r\n" + "inner join books b on t.book_id = b.id \r\n"
				+ "inner join members m on t.member_id = m.id\r\n" + "where t.member_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,id);
			boolean hasValue = false;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				hasValue = true;
				System.out.println(rs.getInt("transaction_id") + " | " + rs.getInt("book_id") + " | " +  
				rs.getString("title")+ " | " + rs.getString("author") + " | "+
				rs.getInt("yearPublished") + " | "+ rs.getDate("issue_date") + " | " + 
				rs.getDate("return_date") + " | " + rs.getString("status"));
			}
			if (!hasValue) {
				System.out.println("No Transactions done...!");
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
