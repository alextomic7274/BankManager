package manager;

public class PrintMenus {
	
	public static void showMainMenu() {
		System.out.println("----------------------");
		System.out.println("---- BANK CASHIER ----");
		System.out.println("----- Main Menu ------");
		System.out.println("[1] Bank Transaction");
		System.out.println("[2] Account Management");
		System.out.println("[3] Exit");
		System.out.print("-> ");
	}
	
	public static void showTransactionMenu() {
		System.out.println("----- Transaction -----");
		System.out.println("[1] Withdraw");
		System.out.println("[2] Lodge");
		System.out.println("[3] Transfer");
		System.out.println("[4] View Balance");
		System.out.println("[5] Go Back");
		System.out.println("[6] Exit");
		System.out.print("-> ");
	}
	
	public static void showAccountMenu() {
		System.out.println("----- manager.Account Menu -----");
		System.out.println("[1] Create manager.Account");
		System.out.println("[2] View manager.Account Info");
		System.out.println("[3] Terminate manager.Account");
		System.out.println("[4] Go Back");
		System.out.println("[5] Exit");
		System.out.print("-> ");
	}

}
