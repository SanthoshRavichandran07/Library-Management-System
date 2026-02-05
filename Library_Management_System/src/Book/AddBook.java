package Book;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class AddBook {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		AddBook addBook = new AddBook();
		addBook.addBook();
	}

	public void addBook() {
		System.out.println("---- Add Book ----");
		System.out.print("Enter Book Id: ");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter Book Title: ");
		String title = sc.nextLine();
		System.out.print("Enter Name of Author: ");
		String author = sc.nextLine();
		System.out.print("Enter Published Year: ");
		int year = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter Publisher Name: ");
		String pubName = sc.nextLine();
		System.out.print("Enter Gener Type: ");
		String genre = sc.nextLine();

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
			String query = "INSERT INTO book VALUES(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, title);
			ps.setString(3, author);
			ps.setInt(4, year);
			ps.setString(5, pubName);
			ps.setString(6, genre);
			// 4.Execute Query
			ps.executeUpdate();
			System.out.println("Book Added..!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}

//
//https://www.google.com/search?q=my+business&mat=Cc-wSR4TJK1oEkwBTVDHnltJzhbERNruvutB5mr4d_8IWJL-olWtA1CQ672utwOT_eF9MiUBPCnqYDVU3fOAN_0jNMhg9b82z_D8VISXPpdCmSIdag5w&hl=en-US&authuser=0&sei=Fut9acjJB4uUseMP-NOToAY