package com.lms.services;

import java.util.Scanner;

import com.lms.DAO.BookDAO;
import com.lms.DAO.BookInput;
import com.lms.DAO.MemberDAO;
import com.lms.DAO.MemberInput;
import com.lms.DAO.TransactionDAO;
import com.lms.DAO.TransactionDAOImpl;
import com.lms.model.Transactions;
import com.lms.validation.InputValidator;

public class LibraryServiceImpl implements LibraryService {
	static Scanner input = new Scanner(System.in);

	BookDAO book = new BookInput();
	MemberDAO member = new MemberInput();
	TransactionDAO transaction = new TransactionDAOImpl();
	InputValidator validate = new InputValidator();

	public static void main(String[] args) {
		LibraryServiceImpl library = new LibraryServiceImpl();

	}

	@Override
	public void books() {
		boolean bookEntry = true;
		while (bookEntry) {
			System.out.print(
					"1. Add Book\n2. Update Book\n3. Search Book\n4. View Books \n5. Delete Book\n6. Go Back <-\nChoose Operations:");
			int choose = input.nextInt();
			switch (choose) {
			case 1 -> {
				book.addBook();
				System.out.println("----------");
			}
			case 2 -> {
				book.updateBook();
				System.out.println("----------");
			}
			case 3 -> {
				book.searchBook();
				System.out.println("----------");
			}
			case 4 -> {
				book.viewBooks();
				System.out.println("----------");
			}
			case 5 -> {
				book.deleteBook();
				System.out.println("----------");
			}
			case 6 -> {
				System.out.println("Good Bye...!");
				bookEntry = false;
			}
			default -> {
				System.out.println("Invalid Choice....!");
			}
			}
		}
	}

	@Override
	public void members() {
		boolean memberEntry = true;
		while (memberEntry) {
			System.out.print(
					"1. Add Member\n2. Update Member\n3. Search Members\n4. View Members \n5. Delete Member\n6. Go Back <-\nChoose Operations:");
			int choose = input.nextInt();
			switch (choose) {
			case 1 -> {
				member.addMember();
				System.out.println("----------");
			}
			case 2 -> {
				member.updateMember();
				System.out.println("----------");

			}
			case 3 -> {
				member.searchMember();
				System.out.println("----------");

			}
			case 4 -> {
				member.viewMember();
				System.out.println("----------");

			}
			case 5 -> {
				member.deleteMember();
				System.out.println("----------");

			}
			case 6 -> {
				System.out.println("Good Bye...!");
				memberEntry = false;
			}
			default -> {
				System.out.println("Invalid Choice....!");
			}
			}
		}
	}

	@Override
	public void transactions() {
		transaction.viewTransactions();
	}

	@Override
	public void issueBook() {
		System.out.println("----- Issue Book -----");
		System.out.print("Enter Book Id:");
		int bookId = input.nextInt();
		input.nextLine(); // consumes new Line
		System.out.print("Enter Member Id:");
		int memberId = input.nextInt();
		input.nextLine(); // consumes new Line
		if (!validate.validId(bookId) && !validate.validId(memberId)) {

			System.err.println("Ensure the entered id is correct");
			return;
		}
		if (!validate.verifyBookId(bookId) && !validate.verifyMemberId(memberId)) {
			System.out.println("No Book or Member was found...!");
			return;
		} 
		if(!validate.checkBookAvailability(bookId)) {
			System.out.println("Sorry, Given book currently unavailable...!");
			return;
		}
		transaction.issueBook(new Transactions(bookId, memberId));
		System.out.println("----------");

	}

	@Override
	public void returnBook() {
		System.out.println("----- Return Book -----");
		System.out.print("Enter Transaction Id:");
		int transactionId = input.nextInt();
		input.nextLine(); // consumes new Line
		if (validate.validId(transactionId)) {
			if (validate.verifyTransactionId(transactionId)) {
				transaction.returnBook(new Transactions(transactionId));
				System.out.println("----------");
			} else {
				System.out.println("No Transactions were found...!");
			}
		} else {
			System.err.println("Ensure the entered id is correct");
		}

	}

}
