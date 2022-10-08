package manager;

public class userInterface {
	
	public static void showMainMenu() {
		System.out.println("----------------------");
		System.out.println("---- BANK MANAGER ----");
		System.out.println("----- Main Menu ------");
		System.out.println("[1] Teller Log In");
		System.out.println("[2] Admin Log in");
		System.out.println("[3] Exit");
		System.out.print("-> ");
	}
	
	public static void showTellerMenu() {
		System.out.println("----- Teller Menu -----");
		System.out.println("[1] Create Account");
		System.out.println("[2] View Account Information");
		System.out.println("[3] Transfer Between Accounts");
		System.out.println("[4] Withdraw From Account");
		System.out.println("[5] Lodge to Account");
		System.out.println("[6] Terminate Account");
		System.out.println("[7] Exit");
		System.out.print("-> ");
	}

	public static void showAdminMenu() {
		System.out.println("----- Admin Menu -----");
		System.out.println("[1] Create Teller");
		System.out.println("[2] Change Teller Password");
		System.out.println("[3] Delete Teller");
		System.out.print("-> ");
	}



}
