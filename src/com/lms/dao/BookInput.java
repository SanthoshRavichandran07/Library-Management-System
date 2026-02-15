package com.lms.dao;

import java.util.Scanner;

import com.lms.model.Books;
import com.lms.validation.InputValidator;

public class BookInput implements BookDAO{
	static Scanner input = new Scanner(System.in);
	BookDAOImpl setInput = new BookDAOImpl();
	InputValidator validate = new InputValidator();
	
	public static void main (String [] args) {
		BookInput bIn = new BookInput();
		bIn.addBook();
	}

	@Override
	public void addBook() { 
		System.out.println("---- Add Book ----");		
		System.out.print("Enter Book Title: ");
		String title = input.nextLine();
		if(!validate.validTitlePattern(title)) {
			System.err.println("Id only contains whole numbers");
			return;
		}
		System.out.print("Enter Name of Author: ");
		String author = input.nextLine();
		if(!validate.validNamePattern(author)) {
			System.err.println("Author name only contains A - Z and/or a-z");
			return;
		}
		System.out.print("Enter Published Year: ");
		int year = input.nextInt();
		input.nextLine();
		if(!validate.validYear(year)) {
			System.err.println("Ensure the entered year was correct");
			return;
		}
		System.out.print("Enter Publisher Name: ");
		String publisherName = input.nextLine();
		if(!validate.validPublisherPattern(publisherName)) {
			System.err.println("Publisher Name only contains A - Z and/or a-z");
			return;
		}
		System.out.print("Enter Gener Type: ");
		String genre = input.nextLine();
		if(!validate.validGenre(genre)) {
			System.err.println("Genre only contains A - Z and/or a-z");
			return;
		}
		System.out.print("Enter Availability (Yes / No): ");
		String availability = input.nextLine();
		if(!validate.validAvailability(availability)) {
			System.err.println("Availability only contains yes or no");
			return;
		}
		
		setInput.addBook(new Books(title,author,year,publisherName,genre, availability));
	}

	@Override
	public void updateBook() {
		System.out.println("---- Update book ----");
		System.out.print("Enter Book Id: ");
		int id = input.nextInt();
		input.nextLine(); // consume 
		if(!validate.validId(id)) {
			System.err.println("Id only contains whole numbers");
			return;
		}
		if (validate.verifyBookId(id)) {
			boolean flag = true;
			while (flag) {
				System.out.println(
						"1. Title\n2. Author Name\n3. Published Year\n4. Publisher Name\n5. Genre\n6. Availability\n7. Go Back");
				System.out.println("Choose what you want to update...");
				int userInput = input.nextInt();
				input.nextLine(); // consume newline
				
				Object value = null;
				String query = null;
				
				switch (userInput) {
				case 1 -> {
					System.out.print("Enter Book Title: ");
					value = input.nextLine();
					if(!validate.validTitlePattern((String)value)) {
						System.err.println("Book Title only contains set of characters and/or sentences.");
						break;
					}
					query = "UPDATE books SET title = ? WHERE id=?";
				}
				case 2 -> {
					System.out.print("Enter Name of Author: ");
					value = input.nextLine();
					if(!validate.validNamePattern((String)value)) {
						System.err.println("Author name only contains A - Z and/or a-z");
						break;
					}
					query = "UPDATE books SET author = ? WHERE id=?";
				}
				case 3 -> {
					System.out.print("Enter Published Year: ");
					value = input.nextInt();
					if(!validate.validYear((int) value)) {
						System.err.println("Ensure the entered year was correct");
						break;
					}
					query = "UPDATE books SET yearPublished = ? WHERE id=?";
				}
				case 4 -> {
					System.out.print("Enter Publisher Name: ");
					value = input.nextLine();
					if(!validate.validPublisherPattern((String)value)) {
						System.err.println("Publisher Name only contains A - Z and/or a-z");
						break;
					}
					query = "UPDATE books SET publisher = ? WHERE id=?";
				}
				case 5 -> {
					System.out.print("Enter Genre Type: ");
					value = input.nextLine();
					if(!validate.validGenre((String) value)) {
						System.err.println("Genre only contains A - Z and/or a-z");
						break;
					}
					query = "UPDATE books SET genre = ? WHERE id=?";
					System.out.println("----------");
				}
				case 6->{
					System.out.print("Enter Availability (Yes / No): ");
					value = input.nextLine();
					if(!validate.validAvailability((String) value)) {
						System.err.println("Availability only contains yes or no");
						return;
					}
					
					
					setInput.updateBookAvailability(id, value);
					return;
				}
				case 7 -> {
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
	public void searchBook() {
		boolean searchBook = true;
		while (searchBook) {
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
					if(!validate.validId((int)value)) {
						System.err.println("Id only contains whole numbers");
						break;
					}
					query = "SELECT * FROM books WHERE id=?";
				}
				case 2 -> {
					System.out.print("Enter Book Title: ");
					value = input.nextLine();
					if(!validate.validTitlePattern((String)value)) {
						System.err.println("Book Title only contains set of characters and/or sentences");
						break;
					}
					query = "SELECT * FROM books WHERE title=?";
				}
				case 3 -> {
					System.out.print("Enter Name of Author: ");
					value = input.nextLine();
					if(!validate.validNamePattern((String)value)) {
						System.err.println("Author name only contains A - Z and/or a-z");
						break;
					}
					query = "SELECT * FROM book WHERE author=?";
								}
				case 4 -> {
					System.out.print("Enter Published Year: ");
					value = input.nextInt();
					if(!validate.validYear((int) value)) {
						System.err.println("Ensure the entered year was correct");
						break;
					}
					query = "SELECT * FROM books WHERE year=?";
					
				}
				case 5 -> {
					System.out.print("Enter Publisher Name: ");
					value = input.nextLine();
					if(!validate.validPublisherPattern((String)value)) {
						System.err.println("Publisher name only contains A - Z and/or a-z");
						break;
					}
					query = "SELECT * FROM books WHERE publisher=?";
				}
				case 6 -> {
					System.out.print("Enter Genre Type: ");
					value = input.nextLine();
					if(!validate.validGenre((String)value)) {
						System.err.println("Genre only contains set of characters and/or words and allowed symbol is ',' (comma)");
						break;
					}
					query = "SELECT * FROM books WHERE genre=?";
				}
				case 7 -> {
					System.out.println("Thank you...!");
					searchBook = false;
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
			System.out.print("1. View All Books \n2. View Books after a year\n3. View Books between some year\n4. View Books based on title\n5. View Books by Title \n6. View Books by Year\n7. View Title, Author & Genre\n8. View Availble Books\n9. View Unavailable Books\n10. Go Back <-\nChoose Options:");
			int viewBookInput = input.nextInt();
			switch(viewBookInput) {
				case 1->{
					setInput.viewAllBooks();
					System.out.println("----------");
				}
				case 2->{
					System.out.print("Enter the Year:");
					int year = input.nextInt();
					if(!validate.validYear(year)) {
						System.err.println("Ensure the entered year was correct");
						break;
					}
					
					setInput.viewBooksAfterYear(year);
					System.out.println("----------");
					
				}
				case 3->{
					System.out.print("Enter the Start Year:");
					int start = input.nextInt();		
					if(!validate.validYear(start)) {
						System.err.println("Ensure the entered year was correct");
						break;
					}
					System.out.print("Enter the End Year:");
					int end = input.nextInt();
					if(!validate.validYear(start)) {
						System.err.println("Ensure the entered year was correct");
						break;
					}
					setInput.viewBooksBetweenSomeYear(start,end);
					System.out.println("----------");
				}
				case 4->{
					input.nextLine();
					System.out.println("Enter Title");
					String title = input.nextLine();
					if(!validate.validTitlePattern(title)) {
						System.err.println("Title only have set of characters or sentence");
						return;
					}
					
					setInput.viewBookBasedOnName(title);
					System.out.println("----------");
				}
				case 5->{
					setInput.viewBooksByTitle();
					System.out.println("----------");
					
				}
				case 6->{
					setInput.viewBooksByYear();
					System.out.println("----------");
					
				}
				case 7->{
					setInput.viewBooksByTitleAuthorGenre();
					System.out.println("----------");
					
				}
				case 8->{
					setInput.viewAvailableBooks();
				}
				case 9->{
					setInput.viewUnAvailableBooks();
				}
				case 10->{
					System.out.println("Thank you...");
					viewBook = false;
					
				}
				default ->{
					System.out.println("Invalid Choice...!");
					System.out.println("----------");
				}
			}
		
		}
	}
	
	@Override
	public void deleteBook() {
		System.out.println("---- Delete Book ----");
		System.out.print("Enter Book Id: ");
		int id = input.nextInt();
		if(!validate.validId(id)) {
			System.err.println("Id only contains whole numbers");
			return;
		}
		if(validate.verifyBookId(id)) {
			setInput.deleteBook(id);
		}else {
			System.out.println("Given Book not in Library");
		}
		
	}

}
