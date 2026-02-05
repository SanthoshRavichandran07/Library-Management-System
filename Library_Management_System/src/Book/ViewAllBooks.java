package Book;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ViewAllBooks {
	public static void main(String[] args) {

		ViewAllBooks vb = new ViewAllBooks();
		vb.viewAllBooks();
	}

	public void viewAllBooks() {
		System.out.println("---- View Books ----");

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

		String query = "SELECT * FROM book ORDER BY id";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(query)) {

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
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}
