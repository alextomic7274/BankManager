package manager;

import database.QueryWrapper;

import java.util.Scanner;

public class Menus {
	private static final Scanner scanner = new Scanner(System.in);
	private static Teller m;
	private static PrintMenus pm;

	public Menus() {
		m = new Teller();
		pm = new PrintMenus();
	}

	public static void mainMenu() {
		pm.showMainMenu();
		int userChoice = Integer.parseInt(scanner.next());
		switch (userChoice) {
			case 1 -> ;// TODO: Add teller log in/password prompts, print invalid if teller not found in DB
			case 2 -> accountMenu(); // TODO: Add admin login/password prompt
			case 3 -> System.exit(0);
			case 4 -> testDB();
		}
		scanner.close();
	}
	
	public static void transactionMenu() {
		pm.showTransactionMenu();
		int userChoice = Integer.parseInt(scanner.next()); 
		switch (userChoice) {
			// TODO
		}
		scanner.close();
	}
	
	public static void accountMenu() {
		pm.showAccountMenu();
		int userChoice = Integer.parseInt(scanner.next()); 
		switch (userChoice) {
			// TODO
		}
		scanner.close();
	}
