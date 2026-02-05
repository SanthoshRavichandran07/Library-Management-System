package book;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

class DeleteBook {

	static Scanner sc = new Scanner(System.in);

	public void deleteBook() {
		System.out.println("---- Delete Book ----");
		System.out.print("Enter Book Id: ");
		int id = sc.nextInt();

		try {
			// 1. Load or Register Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Establish Connection
			Properties props = new Properties();
			try (FileInputStream fileInput = new FileInputStream("db.properties");){
				props.load(fileInput);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String url = props.getProperty("db.url");
			String username = props.getProperty("db.username");
			String password = props.getProperty("db.password");
			
			Connection con = DriverManager.getConnection(url, username, password);

			// 3. Create Statement
			String query = "DELETE FROM book WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);

			// 4.Execute Query
			ps.executeUpdate();

			System.out.println("Book Deleted..!");
			System.out.println("Thank you...!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
