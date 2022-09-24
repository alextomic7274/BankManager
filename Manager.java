import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
	private ArrayList<Account> database = new ArrayList<>();
	private Scanner s = new Scanner(System.in);
	
	// Transactions
	
	public Manager() {
	}

	public void withdraw() {
		System.out.println("ENTER NAME: ");
		String user = s.next();
		System.out.print("WITHDRAW AMOUNT: ");
		float amount = s.nextFloat();
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).isName(user)) {
				database.get(i).withdraw(amount);
				break;
			}	else {
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
		for (int i = 0; i < database.size(); i++) {
			if (database.get(i).isName(user)) {
				database.get(i).lodge(amount);
				break;
			}	else {
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
			
		}
		
	}
	
	
	// Account Management
	
	public void createAccount() {
		System.out.println("NAME: ");
		String name = s.next();
		System.out.println("STARTING BALANCE ($0.00): ");
		float startingBalance = s.nextFloat();
		database.add(new Account(name, startingBalance));
		Menus.accountMenu();
	}
	
	public void findAccount() {
		System.out.println("ENTER NAME: ");
		String name = s.next();
		for (int i = 0; i < database.size(); i++) {
				if (database.get(i).isName(name)) {
					database.get(i).getInfo();
					Menus.accountMenu();
				}	
		}
		System.out.println("NOT FOUND");
		Menus.accountMenu();
	}
	
	public void print() {
		for (int i = 0; i < database.size(); i++) {
			database.get(i).getInfo();
		}
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
		System.out.println("ENTER NAME: ");
		String name = s.next();
		for (int i = 0; i < database.size(); i++) {
				if (database.get(i).isName(name)) {
					database.get(i).getInfo();
					Menus.accountMenu();
				}	
		}
		System.out.println("NOT FOUND");
		Menus.accountMenu();
	}
	
	public boolean containsName(final String name){
	    return database.stream().anyMatch(o -> name.equals(o.getName()));
	}
	
	
	
	

}
