package book;

import java.util.Scanner;
public class MainApp {
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Library Management System");
		System.out.println("-------------------------");
		
		AddBook addBook = new AddBook();
		UpdateBook updateBook = new UpdateBook();
		SearchBook searchBook = new SearchBook();
		ViewAllBooks viewAllBooks = new ViewAllBooks();
		DeleteBook deleteBook = new DeleteBook();
		boolean flag = true;
		while(flag) {
			System.out.println("1. Add Book\n2. Update Book\n3. Search Book\n4. View All Books \n5. Delete Book\n6. Exit\nChoose Operations:");
			int choose = input.nextInt();
			switch(choose) {
			case 1 ->{
				addBook.addBook();
				System.out.println("----------");
			}
			case 2 ->{
				updateBook.updateBook();
				System.out.println("----------");
			}
			case 3 ->{
				searchBook.searchBook();
				System.out.println("----------");
			}
			case 4 ->{
				viewAllBooks.viewAllBooks();
				System.out.println("----------");
			}
			case 5 ->{
				deleteBook.deleteBook();
				System.out.println("----------");
			}
			case 6 ->{
				System.out.println("Good Bye...!");
				flag = false;
			}
			default ->{
				System.out.println("Invalid Input...!");
			}
			}
		}
	}

}
