
import java.util.Scanner;

import com.lms.book.BookDAO;
import com.lms.book.BookInput;

public class MainMenu {
	
	static Scanner input = new Scanner (System.in);
	public static void main(String[] args) {
		BookDAO book = new BookInput();
		
		System.out.println("Library Management System");
		System.out.println("-------------------------");
		
		boolean entry = true;
		while(entry) {
			System.out.print("1. Add Book\n2. Update Book\n3. Search Book\n4. View All Books \n5. Delete Book\n6. Exit\nChoose Operations:");
			int choose = input.nextInt();
			switch(choose) {
				case 1 ->{
					book.addBook();
					System.out.println("----------");
				}
				case 2 ->{
					book.updateBook();
					System.out.println("----------");
				}
				case 3 ->{
					book.searchBook();
					System.out.println("----------");
				}
				case 4 ->{
					book.viewBooks();
					System.out.println("----------");
				}
				case 5 ->{
					book.deleteBook();
					System.out.println("----------");	
				}
				case 6 ->{
					System.out.println("Good Bye...!");
					entry = false;
				}
				default ->{			
					System.out.println("Invalid Choice....!");
				}
			}
		}
	}

}
