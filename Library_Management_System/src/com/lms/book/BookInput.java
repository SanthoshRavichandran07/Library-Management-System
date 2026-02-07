package com.lms.book;

import java.util.Scanner;

import com.lms.model.Book;
import com.lms.validation.InputValidator;

public class BookInput implements BookDAO{
	static Scanner input = new Scanner(System.in);
	BookDAOImpl setInput = new BookDAOImpl();
	InputValidator validate = new InputValidator();

	@Override
	public void addBook() {
		System.out.println("---- Add Book ----");
		System.out.print("Enter Book Id: ");
		int id = input.nextInt();
		input.nextLine();
		System.out.print("Enter Book Title: ");
		String title = input.nextLine();
		System.out.print("Enter Name of Author: ");
		String author = input.nextLine();
		System.out.print("Enter Published Year: ");
		int year = input.nextInt();
		input.nextLine();
		System.out.print("Enter Publisher Name: ");
		String publisherName = input.nextLine();
		System.out.print("Enter Gener Type: ");
		String genre = input.nextLine();
		
		
		setInput.addBook(new Book(id,title,author,year,publisherName,genre));
	}

	@Override
	public void updateBook() {
		System.out.println("---- Update book ----");
		System.out.print("Enter Book Id: ");
		int id = input.nextInt();
		input.nextLine(); // consume newline
		if (validate.verifyId(id)) {
			boolean flag = true;
			while (flag) {
				System.out.println(
						"1. Title\n2. Author Name\n3. Published Year\n4. Publisher Name\n5. Genre\n6. Go Back");
				System.out.println("Choose what you want to update...");
				int userInput = input.nextInt();
				input.nextLine(); // consume newline
				
				Object value = null;
				String query = null;
				
				switch (userInput) {
				case 1 -> {
					System.out.print("Enter Book Title: ");
					value = input.nextLine();
					query = "UPDATE book SET title = ? WHERE id=?";
				}
				case 2 -> {
					System.out.print("Enter Name of Author: ");
					value = input.nextLine();
					query = "UPDATE book SET author = ? WHERE id=?";
				}
				case 3 -> {
					System.out.print("Enter Published Year: ");
					value = input.nextInt();
					query = "UPDATE book SET yearPublished = ? WHERE id=?";
				}
				case 4 -> {
					System.out.print("Enter Publisher Name: ");
					value = input.nextLine();
					query = "UPDATE book SET publisher = ? WHERE id=?";
				}
				case 5 -> {
					System.out.print("Enter Genre Type: ");
					value = input.nextLine();
					query = "UPDATE book SET genre = ? WHERE id=?";
					System.out.println("----------");
				}
				case 6 -> {
					System.out.println("Thank you!");
					flag = false;
				}
				default -> {
					System.out.println("Invalid Input...!");
					return;
				}
				}
				setInput.updateBook(id,value, query);
				
			}

		} else {
			System.out.println("Given Book Not present in Library...!");

		}	
		
	}

	@Override
	public void deleteBook() {
		System.out.println("---- Delete Book ----");
		System.out.print("Enter Book Id: ");
		int id = input.nextInt();
		if(validate.verifyId(id)) {
			setInput.deleteBook(id);
		}else {
			System.out.println("Given Book not in Library");
		}
		
	}

	@Override
	public void searchBook() {
		boolean flag = true;
		while (flag) {
			System.out.println("---- Search Book ----");
			System.out.println("1. ID\n2. Title\n3. Author Name\n4. Published Year\n5. Publisher Name\n6. Genre\n7. Go Back");
			System.out.println("Search by:");
			int userInput = input.nextInt();
			input.nextLine(); // consume newline
			Object value = null;
			String query = null;
			switch (userInput) {
				case 1 -> {
					System.out.print("Enter Book ID: ");
					value = input.nextLine();
					query = "SELECT * FROM book WHERE id=?";
				}
				case 2 -> {
					System.out.print("Enter Book Title: ");
					value = input.nextLine();
					query = "SELECT * FROM book WHERE title=?";
				}
				case 3 -> {
					System.out.print("Enter Name of Author: ");
					value = input.nextLine();
					query = "SELECT * FROM book WHERE author=?";
								}
				case 4 -> {
					System.out.print("Enter Published Year: ");
					value = input.nextInt();
					query = "SELECT * FROM book WHERE year=?";
					
				}
				case 5 -> {
					System.out.print("Enter Publisher Name: ");
					value = input.nextLine();
					query = "SELECT * FROM book WHERE publisher=?";
				}
				case 6 -> {
					System.out.print("Enter Genre Type: ");
					value = input.nextLine();
					query = "SELECT * FROM book WHERE genre=?";
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
			if(query !=null) {
				setInput.searchBook(value, query);
			}
		}
	}

	@Override
	public void viewBooks() {
		
		boolean viewBook = true;
		while(viewBook) {
			System.out.println("1. View All Books \n2. View Books after a year\n3. View Books between some year\n4. View Books based on title\n5. View Books by Title \n6. View Books by Year\n7. View Title, Author & Genre\n8. Go Back <-");
			int viewBookInput = input.nextInt();
			switch(viewBookInput) {
				case 1->{
					setInput.viewAllBooks();					
				}
				case 2->{
					System.out.print("Enter the Year:");
					int year = input.nextInt();
					setInput.viewBooksAfterYear(year);
					
				}
				case 3->{
					System.out.print("Enter the Start Year:");
					int start = input.nextInt();		
					System.out.print("Enter the End Year:");
					int end = input.nextInt();
					setInput.viewBooksBetweenSomeYear(start,end);
				}
				case 4->{
					input.nextLine();
					System.out.println("Enter Title");
					String title = input.nextLine();
					setInput.viewBookBasedOnName(title);
				}
				case 5->{
					setInput.viewBooksByTitle();
					
				}
				case 6->{
					setInput.viewBooksByYear();
					
				}
				case 7->{
					setInput.viewBooksByTitleAuthorGenre();
					
				}
				case 8->{
					System.out.println("Thank you...");
					viewBook = false;
					
				}
				default ->{
					System.out.println("Invalid Choice...!");
				}
			}
		
		
		}
	}

}
