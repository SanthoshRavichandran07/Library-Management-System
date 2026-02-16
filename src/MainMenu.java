
import java.util.Scanner;


import com.lms.services.LibraryService;
import com.lms.services.LibraryServiceImpl;
import com.lms.validation.InputValidator;

public class MainMenu {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		InputValidator validate = new InputValidator();
		
		LibraryService library = new LibraryServiceImpl();
		System.out.println("Library Management System");
		System.out.println("-------------------------");
		
		boolean login = true;
		while(login) {
			
			System.out.print("Welcome\nPress 0 to Exit\nEnter User ID:");
			int userId = input.nextInt();
			input.nextLine();
			if (userId ==0) {
				System.err.println("Finished...!");
				break;
			}
			if(!validate.verifyMemberId(userId)) {
				System.err.println("The User ID not present in library");
				
			}
			if(validate.verifyAdmin(userId)) {
				System.out.println("Logined as Admin");
				library.admin(userId);
				System.out.println("----------");
			}
			if(validate.verifyUser(userId)) {
				System.out.println("Logined as User");
				library.user(userId);
				System.out.println("----------");
			}
		}

}

}
