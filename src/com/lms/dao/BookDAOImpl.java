package com.lms.dao;

import java.sql.*;

import com.lms.util.DBConnection;
import com.lms.model.Books;

public class BookDAOImpl {

	Connection con = null;

	public void addBook(Books book) {
		con = DBConnection.getConnection();
		String query = "INSERT INTO books (title, author, yearPublished, publisher, genre, availability) VALUES(?,?,?,?,?,?)";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setInt(3, book.getYear());
			ps.setString(4, book.getPublisherName());
			ps.setString(5, book.getGenre());
			ps.setInt(6, convertAvailability(book.getAvailability()));
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

	public int convertAvailability(String availability) {
		if (availability.toLowerCase().equals("yes")) {
			return 1;
		}
		return 0;
	}
	
	public String convertAvailabilityToString(int availability) {
		if(availability==1) {
			return "Yes";
		}
		return "No";
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
	
	public void updateBookAvailability(int bookId,Object value) {
		con = DBConnection.getConnection();
		String query = "UPDATE books SET availability =? WHERE id =?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			int availability = convertAvailability((String) value);
			ps.setInt(1, availability);
			ps.setInt(2, bookId);
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
		String query = "DELETE FROM books WHERE id = ?";
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
						+ rs.getString("genre")+" | "+ convertAvailabilityToString(rs.getInt("availability"))  );
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
		String query = "SELECT * FROM books ORDER BY id";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(rs.getInt("id") + " | " + rs.getString("title") + " | " + rs.getString("author")
						+ " | " + rs.getInt("yearPublished") + " | " + rs.getString("publisher") + " | "
						+ rs.getString("genre")+" | "+ convertAvailabilityToString(rs.getInt("availability")) );
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
		String query = "SELECT * FROM books WHERE yearPublished > ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, year);
			ResultSet rs = ps.executeQuery();

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(rs.getInt("id") + " | " + rs.getString("title") + " | " + rs.getString("author")
						+ " | " + rs.getInt("yearPublished") + " | " + rs.getString("publisher") + " | "
						+ rs.getString("genre")+" | "+ convertAvailabilityToString(rs.getInt("availability")) );
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
		String query = "SELECT * FROM books WHERE yearPublished BETWEEN ? AND ? ORDER BY yearPublished";
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
						+ rs.getString("genre")+" | "+ convertAvailabilityToString(rs.getInt("availability")));
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
		String query = "SELECT * FROM books WHERE title LIKE ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, "%" + bookName + "%");
			ResultSet rs = ps.executeQuery();

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(rs.getInt("id") + " | " + rs.getString("title") + " | " + rs.getString("author")
						+ " | " + rs.getInt("yearPublished") + " | " + rs.getString("publisher") + " | "
						+ rs.getString("genre")+" | "+ convertAvailabilityToString(rs.getInt("availability")));
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
		String query = "SELECT * FROM books ORDER BY title";
		try {
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(query);

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(rs.getInt("id") + " | " + rs.getString("title") + " | " + rs.getString("author")
						+ " | " + rs.getInt("yearPublished") + " | " + rs.getString("publisher") + " | "
						+ rs.getString("genre")+" | "+ convertAvailabilityToString(rs.getInt("availability")));
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
		String query = "SELECT * FROM books ORDER BY yearPublished";
		try {
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(query);

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(rs.getInt("id") + " | " + rs.getString("title") + " | " + rs.getString("author")
						+ " | " + rs.getInt("yearPublished") + " | " + rs.getString("publisher") + " | "
						+ rs.getString("genre")+" | "+ convertAvailabilityToString(rs.getInt("availability")));
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
		String query = "SELECT title, author, genre FROM books ";
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
	
	public void viewAvailableBooks() {
		con = DBConnection.getConnection();
		String query = "SELECT * FROM books WHERE availability=1";
		try {
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(query);

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(rs.getInt("id") + " | " + rs.getString("title") + " | " + rs.getString("author")
						+ " | " + rs.getInt("yearPublished") + " | " + rs.getString("publisher") + " | "
						+ rs.getString("genre")+" | "+ convertAvailabilityToString(rs.getInt("availability")));
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
	
	public void viewUnAvailableBooks() {
		con = DBConnection.getConnection();
		String query = "SELECT * FROM books WHERE availability=0";
		try {
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(query);

			boolean hasBooks = false;
			while (rs.next()) {
				hasBooks = true;
				System.out.println(rs.getInt("id") + " | " + rs.getString("title") + " | " + rs.getString("author")
						+ " | " + rs.getInt("yearPublished") + " | " + rs.getString("publisher") + " | "
						+ rs.getString("genre")+" | "+ convertAvailabilityToString(rs.getInt("availability")));
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
