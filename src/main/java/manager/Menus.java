package manager;

import database.QueryWrapper;

import java.util.Scanner;

public class Menus {
	private static Scanner scanner = new Scanner(System.in);
	private static QueryWrapper qw;
	
	public Menus() {
		qw = new QueryWrapper();
	}

	public static void mainMenu() {
		userInterface.showMainMenu();
		int userChoice = Integer.parseInt(scanner.next()); 
		switch (userChoice) {
			case 1 -> adminMenu();
			case 2 -> tellerMenu();
			case 3 -> System.exit(0);
		}
		scanner.close();
	}

	public static void adminMenu() {
		if (validateUser("Admin")){
			// TODO
			userInterface.showAdminMenu();
		}	else{
			System.out.println("Wrong Username or Password");
			mainMenu();
		}
	}

	public static void tellerMenu() {
		if (validateUser("Teller")){
			// TODO
			userInterface.showTellerMenu();
		}	else{
			System.out.println("Wrong Username or Password");
			mainMenu();
		}
	}

	public static boolean validateUser(String role) {
		System.out.println(role+" Username: ");
		String userName = scanner.nextLine();
		System.out.println(role+" Password: ");
		String passWord = scanner.nextLine();
		if (qw.checkUserLogin(userName, passWord)){
			return true;
		}	else return false;
	}
}
