package manager;

import database.QueryWrapper;

import java.util.Scanner;

public class Menus {
	private static Scanner scanner = new Scanner(System.in);
	private static QueryWrapper qw;
	private static RecordManager rm;
	
	public Menus() {
		qw = new QueryWrapper();
		rm = new RecordManager();
	}

	public static void mainMenu() {
		UserInterface.showMainMenu();
		int userChoice = Integer.parseInt(scanner.next()); 
		switch (userChoice) {
			case 1 -> tellerMenu();
			case 2 -> adminMenu();
			case 3 -> System.exit(0);
		}
		scanner.close();
	}

	public static void adminMenu() {
		boolean validate = validateUser("Admin");
		if (validate){
			// TODO
			UserInterface.showAdminMenu();
			int userChoice = Integer.parseInt(scanner.next());
			switch (userChoice) {
				case 1 -> rm.createTeller();
				case 2 -> rm.changeTellerPassword();
				case 3 -> rm.deleteTeller();
			}
		}	else{
			System.out.println("Invalid credentials - Try again");
			adminMenu();
		}
	}

	public static void tellerMenu() {
		boolean validate = validateUser("Teller");
		if (validate){
			// TODO
			UserInterface.showTellerMenu();
		}	else{
			System.out.println("Wrong Username or Password");
			mainMenu();
		}
	}

	public static boolean validateUser(String role) {
		System.out.println(role+" Username: ");
		String userName = scanner.next();
		System.out.println(role+" Password: ");
		String passWord = scanner.next();
		if (qw.checkUserLogin(userName, passWord)){
			return true;
		}	else return false;
	}
}
