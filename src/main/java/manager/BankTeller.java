package manager;

import java.util.ArrayList;
import java.util.Scanner;

public class BankTeller {
	private ArrayList<Customer> database = new ArrayList<>();
	private final Scanner s = new Scanner(System.in);
	// Transactions
	
	public BankTeller() {
	}

	public void withdraw() {
		System.out.println("ENTER NAME: ");
		String user = s.next();
		System.out.print("WITHDRAW AMOUNT: ");
		float amount = s.nextFloat();
		for (Customer customer : database) {
			if (customer.isName(user)) {
				customer.withdraw(amount);
				break;
			} else {
				System.out.println("NOT FOUND");
			}
		}
		Menus.transactionMenu();
	}
	
	public void lodge() {
		System.out.println("ENTER NAME: ");
		String user = s.next();
		System.out.print("LODGE AMOUNT: ");
		float amount = s.nextFloat();
		for (Customer customer : database) {
			if (customer.isName(user)) {
				customer.lodge(amount);
				break;
			} else {
				System.out.println("NOT FOUND");
			}
		}
		Menus.transactionMenu();
	}
	
	public void transfer() {
		System.out.println("ENTER ORIGIN NAME: ");
		String userOrig = s.next();
		System.out.println("ENTER ORIGIN NAME: ");
		String userTarget = s.next();
		float transferAmount = s.nextFloat();
		if (containsName(userOrig) && containsName(userTarget)) {
			//add transfer functionality
		}
		
	}
	
	
	// manager.Account Management
	
	public void createAccount() {
		System.out.println("Name: ");
		String name = s.next();
		System.out.println("Address: ");
		String addr = s.next();
		System.out.println("STARTING BALANCE ($0.00): ");
		float startingBalance = s.nextFloat();
		database.add(new Customer(name, addr, startingBalance));
		Menus.accountMenu();
	}

	private void printName() {
		System.out.println("ENTER NAME: ");
		String name = s.next();
		for (Customer customer : database) {
			if (customer.isName(name)) {
				customer.getInfo();
				Menus.accountMenu();
			}
		}
		System.out.println("NOT FOUND");
		Menus.accountMenu();
	}


	public void deleteAccount() {
		System.out.println("Enter user to delete: ");
		String user = s.next();
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).isName(user)) {
			database.remove(i);
			}
		}
		Menus.accountMenu();
	}
	
	public void viewBalance() {
		printName();
	}
	
	public boolean containsName(final String name){
	    return database.stream().anyMatch(o -> name.equals(o.getName()));
	}

	public void findAccount(){
		//TODO: implement
	}
	
	
	
	

}
