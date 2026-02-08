package book;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class SearchBook {
	static Scanner sc = new Scanner(System.in);

	String query = null;
	Object value = null;
	public void searchBook() {
	
		boolean flag = true;
		while (flag) {
			System.out.println("---- Search Book ----");
			System.out.println("1. ID\n2. Title\n3. Author Name\n4. Published Year\n5. Publisher Name\n6. Genre\n7. Go Back");
			System.out.println("Search by:");
			int input = sc.nextInt();
			sc.nextLine(); // consume newline

			switch (input) {
			case 1 -> {
				System.out.print("Enter Book ID: ");
				value = sc.nextLine();
				query = "SELECT * FROM book WHERE id=?";
				perform(value,query);
				System.out.println("----------");
			}
			case 2 -> {
				System.out.print("Enter Book Title: ");
				value = sc.nextLine();
				query = "SELECT * FROM book WHERE title=?";
				perform(value,query);
				System.out.println("----------");
			}
			case 3 -> {
				System.out.print("Enter Name of Author: ");
				value = sc.nextLine();
				query = "SELECT * FROM book WHERE author=?";
				perform(value,query);
				System.out.println("----------");
			}
			case 4 -> {
				System.out.print("Enter Published Year: ");
				value = sc.nextInt();
				query = "SELECT * FROM book WHERE year=?";
				perform(value,query);
				System.out.println("----------");
			}
			case 5 -> {
				System.out.print("Enter Publisher Name: ");
				value = sc.nextLine();
				query = "SELECT * FROM book WHERE publisher=?";
				perform(value,query);
				System.out.println("----------");
			}
			case 6 -> {
				System.out.print("Enter Genre Type: ");
				value = sc.nextLine();
				query = "SELECT * FROM book WHERE genre=?";
				perform(value,query);
				System.out.println("----------");
			}
			case 7 -> {
				System.out.println("Thank you...!");
				flag = false;
			}
			default -> {
				System.out.println("Invalid Input...!");
				return;
			}
			}
		}
	}
	public void perform(Object value, String query) {
		Properties props = new Properties();
		try (FileInputStream fileInput = new FileInputStream("db.properties");) {
			props.load(fileInput);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String url = props.getProperty("db.url");
		String username = props.getProperty("db.username");
		String password = props.getProperty("db.password");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, username, password);
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
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}