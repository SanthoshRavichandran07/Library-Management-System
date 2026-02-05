package Book;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class UpdateBook {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
    	UpdateBook ub = new UpdateBook();
    	ub.updateBook();
    	
    }
    public void updateBook() {
    	System.out.println("Update book");
        System.out.print("Enter Book Id: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.println("1. Title\n2. Author Name\n3. Published Year\n4. Publisher Name\n5. Genre");
        System.out.println("Choose what you want to update...");
        int input = sc.nextInt();
        sc.nextLine(); // consume newline

        String query = null;
        Object value = null;

        switch (input) {
            case 1 -> {
                System.out.print("Enter Book Title: ");
                value = sc.nextLine();
                query = "UPDATE book SET title = ? WHERE id=?";
            }
            case 2 -> {
                System.out.print("Enter Name of Author: ");
                value = sc.nextLine();
                query = "UPDATE book SET author = ? WHERE id=?";
            }
            case 3 -> {
                System.out.print("Enter Published Year: ");
                value = sc.nextInt();
                query = "UPDATE book SET yearPublished = ? WHERE id=?";
            }
            case 4 -> {
                System.out.print("Enter Publisher Name: ");
                value = sc.nextLine();
                query = "UPDATE book SET publisher = ? WHERE id=?";
            }
            case 5 -> {
                System.out.print("Enter Genre Type: ");
                value = sc.nextLine();
                query = "UPDATE book SET genre = ? WHERE id=?";
            }
            default -> {
                System.out.println("Invalid Input...!");
                return;
            }
        }

        try {
        	//1. Load or 
//            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. Establish Connection
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

            try (Connection con = DriverManager.getConnection(url, username, password);
                 PreparedStatement ps = con.prepareStatement(query)) {

                if (value instanceof String) {
                    ps.setString(1, (String) value);
                } else if (value instanceof Integer) {
                    ps.setInt(1, (Integer) value);
                }
                ps.setInt(2, id);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    System.out.println("Book Updated..!");
                } else {
                    System.out.println("No book found with given ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
