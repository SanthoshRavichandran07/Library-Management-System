package com.lms.book;

import java.sql.*;

import com.lms.util.DBConnection;
import com.lms.model.Book;

public class BookDAOImpl {

	Connection con = null;

	public void addBook(Book book) {
		con = DBConnection.getConnection();
		String query = "INSERT INTO book VALUES(?,?,?,?,?)";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setInt(3, book.getYear());
			ps.setString(4, book.getPublisherName());
			ps.setString(5, book.getGenre());
			// 4.Execute Query
			ps.executeUpdate();
			System.out.println("Book Added Successfully");
			System.out.println("Thank you...!");
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

	public void updateBook(int id, Object value, String query) {
		con = DBConnection.getConnection();

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

		System.out.println("Book Updated..!");

	}

	public void deleteBook(int id) {

		con = DBConnection.getConnection();
		String query = "DELETE FROM book WHERE id = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, id);

			ps.executeUpdate();

			System.out.println("Book Deleted..!");
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

	public void searchBook(Object value, String query) {

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
				System.out.println(rs.getInt("id") + " | " + rs.getString("title") + " | " + rs.getString("author")
						+ " | " + rs.getInt("yearPublished") + " | " + rs.getString("publisher") + " | "
						+ rs.getString("genre"));
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

	public void viewAllBooks() {
		con = DBConnection.getConnection();
		String query = "SELECT * FROM book ORDER BY id";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(rs.getInt("id") + " | " + rs.getString("title") + " | " + rs.getString("author")
						+ " | " + rs.getInt("yearPublished") + " | " + rs.getString("publisher") + " | "
						+ rs.getString("genre"));
			}

			if (!hasBooks) {
				System.out.println("No books found.");
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

	public void viewBooksAfterYear(int year) {
		con = DBConnection.getConnection();
		String query = "SELECT * FROM book WHERE yearPublished > ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, year);
			ResultSet rs = ps.executeQuery();

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(rs.getInt("id") + " | " + rs.getString("title") + " | " + rs.getString("author")
						+ " | " + rs.getInt("yearPublished") + " | " + rs.getString("publisher") + " | "
						+ rs.getString("genre"));
			}

			if (!hasBooks) {
				System.out.println("No books found.");
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

	public void viewBooksBetweenSomeYear(int start, int end) {
		con = DBConnection.getConnection();
		String query = "SELECT * FROM book WHERE yearPublished BETWEEN ? AND ? ORDER BY yearPublished";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(rs.getInt("id") + " | " + rs.getString("title") + " | " + rs.getString("author")
						+ " | " + rs.getInt("yearPublished") + " | " + rs.getString("publisher") + " | "
						+ rs.getString("genre"));
			}

			if (!hasBooks) {
				System.out.println("No books found.");
			}
//			con.close();
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

	public void viewBookBasedOnName(String bookName) {
		con = DBConnection.getConnection();
		String query = "SELECT * FROM book WHERE title LIKE ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, "%" + bookName + "%");
			ResultSet rs = ps.executeQuery();

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(rs.getInt("id") + " | " + rs.getString("title") + " | " + rs.getString("author")
						+ " | " + rs.getInt("yearPublished") + " | " + rs.getString("publisher") + " | "
						+ rs.getString("genre"));
			}

			if (!hasBooks) {
				System.out.println("No books found.");
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

	public void viewBooksByTitle() {
		con = DBConnection.getConnection();
		String query = "SELECT * FROM book ORDER BY title";
		try {
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(query);

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(rs.getInt("id") + " | " + rs.getString("title") + " | " + rs.getString("author")
						+ " | " + rs.getInt("yearPublished") + " | " + rs.getString("publisher") + " | "
						+ rs.getString("genre"));
			}

			if (!hasBooks) {
				System.out.println("No books found.");
			}
//			con.close();
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

	public void viewBooksByYear() {
		con = DBConnection.getConnection();
		String query = "SELECT * FROM book ORDER BY yearPublished";
		try {
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(query);

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(rs.getInt("id") + " | " + rs.getString("title") + " | " + rs.getString("author")
						+ " | " + rs.getInt("yearPublished") + " | " + rs.getString("publisher") + " | "
						+ rs.getString("genre"));
			}

			if (!hasBooks) {
				System.out.println("No books found.");
			}
//			con.close();
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

	public void viewBooksByTitleAuthorGenre() {
		con = DBConnection.getConnection();
		String query = "SELECT title, author, genre FROM book ";
		try {
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(query);

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(
						rs.getString("title") + " | " + rs.getString("author") + " | " + rs.getString("genre"));
			}

			if (!hasBooks) {
				System.out.println("No books found.");
			}
//			con.close();
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
