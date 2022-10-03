package manager;

import java.util.Scanner;

public class Menus {
	private static Scanner scanner = new Scanner(System.in);
	private static BankTeller m;
	
	public Menus() {
		m = new BankTeller();
	}

	public static void mainMenu() {
		PrintMenus.showMainMenu();
		int userChoice = Integer.parseInt(scanner.next()); 
		switch (userChoice) {
			case 1 -> transactionMenu();
			case 2 -> accountMenu();
			case 3 -> System.exit(0);
		}
		scanner.close();
	}
	
	public static void transactionMenu() {
		PrintMenus.showTransactionMenu();
		int userChoice = Integer.parseInt(scanner.next()); 
		switch (userChoice) {
			case 1 -> m.withdraw();
			case 2 -> accountMenu();
			case 3 -> System.exit(0);
			case 4 -> m.viewBalance();
			case 5 -> mainMenu();
			case 6 -> System.exit(0);

		}
		scanner.close();
	}
	
	public static void accountMenu() {
		PrintMenus.showAccountMenu();
		int userChoice = Integer.parseInt(scanner.next()); 
		switch (userChoice) {
			case 1 -> m.createAccount();
			case 2 -> m.findAccount();
			case 3 -> m.deleteAccount();
			case 4 -> mainMenu();
			case 5 -> System.exit(0);
		}
		scanner.close();
	}
}
