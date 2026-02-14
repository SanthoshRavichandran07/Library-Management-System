package com.lms.DAO;

import com.lms.model.Members;

import java.util.Scanner;

import com.lms.DAO.MemberDAOImpl;
import com.lms.validation.InputValidator;

public class MemberInput implements MemberDAO {
	static Scanner input = new Scanner(System.in);
	InputValidator validate = new InputValidator();
	MemberDAOImpl setMember = new MemberDAOImpl();
	
	@Override
	public void addMember() {
		System.out.println("---- Add Member ----");
		System.out.print("Enter Member Name: ");
		String name = input.nextLine();
		if (!validate.validNamePattern(name)) {
			System.err.println("Ensure the Name...!");
			return;
		}
		System.out.print("Enter Email: ");
		String email = input.nextLine();
		if (!validate.validEmail(email)) {
			System.err.println("Ensure the Email");
			return;
		}
		System.out.print("Enter Role: ");
		String role = input.nextLine();
		if (!validate.validNamePattern(role)) {
			System.err.println("Ensure the Role");
			return;
		}
		setMember.addMember(new Members(name, email, role));
	}

	@Override
	public void updateMember() {
		System.out.println("---- Update Member ----");
		System.out.print("Enter Member Id: ");
		int id = input.nextInt();
		input.nextLine(); // consume
		if (!validate.validId(id)) {
			System.err.println("Id only contains whole numbers");
			return;
		}
		if (validate.verifyMemberId(id)) {
			boolean flag = true;
			while (flag) {
				System.out.println("1. Name\n2. Email \n3. Role\n\n4. Go Back");
				System.out.print("Choose what you want to update:");
				int userInput = input.nextInt();
				input.nextLine(); // consume newline

				Object value = null;
				String query = null;

				switch (userInput) {
				case 1 -> {
					System.out.print("Enter Name: ");
					value = input.nextLine();
					if (!validate.validNamePattern((String) value)) {
						System.err.println("Ensure the entered Name was correct!");
						break;
					}
					query = "UPDATE members SET name = ? WHERE id=?";
				}
				case 2 -> {
					System.out.print("Enter Email: ");
					value = input.nextLine();
					if (!validate.validEmail((String) value)) {
						System.err.println("Ensure the entered Email was correct!");
						break;
					}
					query = "UPDATE members SET email = ? WHERE id=?";
				}
				case 3 -> {
					System.out.print("Enter Role: ");
					value = input.nextInt();
					if (!validate.validYear((int) value)) {
						System.err.println("Ensure the entered role was correct");
						break;
					}
					query = "UPDATE members SET role = ? WHERE id=?";
				}
				case 4 -> {
					System.out.println("Thank you!");
					flag = false;
				}
				default -> {
					System.out.println("Invalid Input...!");
					return;
				}
				}
				setMember.updateMember(id, value, query);

			}

		} else {
			System.out.println("Given Book Not present in Library...!");

		}
	}

	@Override
	public void deleteMember() {
		System.out.println("---- Delete Member ----");
		System.out.print("Enter Member Id: ");
		int id = input.nextInt();
		if (!validate.validId(id)) {
			System.err.println("Id only contains whole numbers");
			return;
		}
		if (validate.verifyMemberId(id)) {
			setMember.deleteMember(id);
		} else {
			System.out.println("Given Member not in Library");
		}
	}

	@Override
	public void searchMember() {
		boolean searchMember = true;
		while (searchMember) {
			System.out.println("---- Search Member ----");
			System.out.println("1. ID\n2. Name\n3. Email\n4. Role\n5. Go Back <-Search by:");
			int userInput = input.nextInt();
			input.nextLine(); // consume newline
			Object value = null;
			String query = null;
			switch (userInput) {
			case 1 -> {
				System.out.print("Enter Member ID: ");
				value = input.nextLine();
				if (!validate.validId((int) value)) {
					System.err.println("Id only contains whole numbers");
					break;
				}
				query = "SELECT * FROM members WHERE id=?";
			}
			case 2 -> {
				System.out.print("Enter Member Name: ");
				value = input.nextLine();
				if (!validate.validNamePattern((String) value)) {
					System.err.println("Ensure the entered Member Name was correct!");
					break;
				}
				query = "SELECT * FROM members WHERE name=?";
			}
			case 3 -> {
				System.out.print("Enter the Email: ");
				value = input.nextInt();
				if (!validate.validYear((int) value)) {
					System.err.println("Ensure the entered Email was correct");
					break;
				}
				query = "SELECT * FROM members WHERE email=?";
				
			}
			case 4 -> {
				System.out.print("Enter the Role: ");
				value = input.nextLine();
				if (!validate.validNamePattern((String) value)) {
					System.err.println("Ensure the entered Role was correct!");
					break;
				}
				query = "SELECT * FROM members WHERE role=?";
				

			}
			
			case 5 -> {
				System.out.println("Thank you...!");
				searchMember = false;
			}
			default -> {
				System.out.println("Invalid Input...!");
				return;
			}
			}
			if (query != null) {
				setMember.searchMember(value, query);
			}
		}
	}

	@Override
	public void viewMember() {
		boolean viewMember = true;
		while (viewMember) {
			System.out.print(
					"1. View All Members \n2. View Members By Name\n3. View Members By Email\n4. View Members By Role\n5. Go Back <-\nChoose Options:");
			int viewBookInput = input.nextInt();
			input.nextLine(); // consume new Line
			switch (viewBookInput) {
			case 1 -> {
				setMember.viewAllMembers();
				System.out.println("----------");
			}
			case 2 -> {
				System.out.print("Enter the Name:");
				String name = input.nextLine();
				if (!validate.validNamePattern(name)) {
					System.err.println("Ensure the entered Name was correct");
					break;
				}

				setMember.viewMemberByName(name);
				System.out.println("----------");

			}
			case 3 -> {
				System.out.print("Enter the Email:");
				String email = input.nextLine();
				if (!validate.validEmail(email)) {
					System.err.println("Ensure the entered Email was correct");
					break;
				}

				setMember.viewMemberByEmail(email);
				System.out.println("----------");
			}
			case 4 -> {
				System.out.print("Enter the Role:");
				String role = input.nextLine();
				if (!validate.validNamePattern(role)) {
					System.err.println("Ensure the entered year was correct");
					break;
				}
				setMember.viewMemberByRole(role);
				System.out.println("----------");
			}
			case 5 -> {
				System.out.println("Thank you...");
				viewMember = false;

			}
			default -> {
				System.out.println("Invalid Choice...!");
				System.out.println("----------");
			}
			}

		}
	}

}
