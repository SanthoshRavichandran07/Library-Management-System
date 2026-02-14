
import java.util.Scanner;


import com.lms.services.LibraryService;
import com.lms.services.LibraryServiceImpl;

public class MainMenu {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		
		LibraryService library = new LibraryServiceImpl();
		System.out.println("Library Management System");
		System.out.println("-------------------------");

		boolean entry = true;
		while (entry) {
			System.out.print(
					"1. Books\n2. Members\n3. Transactions\n4. Issue Book\n5. Return Book\n6. Exit\nChoose Operations:");
			int choose = input.nextInt();
			switch (choose) {
			case 1 -> {
				library.books();
				System.out.println("----------");
			}
			case 2 -> {
				library.members();
				System.out.println("----------");
			}
			case 3 -> {
				library.transactions();
				System.out.println("----------");
			}
			case 4 -> {

				library.issueBook();
				System.out.println("----------");
			}
			case 5 -> {
				library.returnBook();
				System.out.println("----------");
			}
			case 6 -> {
				System.out.println("Good Bye...!");
				entry = false;
			}
			default -> {
				System.out.println("Invalid Choice....!");
			}
			}
		}
	}

	private static LibraryService LibraryServiceImpl() {
		// TODO Auto-generated method stub
		return null;
	}

}
