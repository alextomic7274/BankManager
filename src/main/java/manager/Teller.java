package manager;

import database.QueryWrapper;

import java.util.ArrayList;
import java.util.Scanner;

public class Teller {
	private final Scanner s = new Scanner(System.in);
	private QueryWrapper qw;
	// Transactions
	
	public Teller() {
		qw = new QueryWrapper();
	}

	public void withdraw() {
		System.out.println("ENTER NAME: ");
		String user = s.nextLine();
		System.out.print("WITHDRAW AMOUNT: ");

		float amount = s.nextFloat();
		//TODO
	}
	
	public void lodge() {
		System.out.println("ENTER NAME: ");
		String user = s.next();
		System.out.print("LODGE AMOUNT: ");
		//TODO
	}
	
	public void transfer() {
		System.out.println("Origin Customer: ");
		String userOrig = s.next();
		System.out.println("Destination Customer: ");
		String userTarget = s.next();
		System.out.println("Transfer Amount (0.00): ");
		float transferAmount = s.nextFloat();
		//TODO
		
	}
	
	
	// manager.Account Management
	
	public void createAccount() {
		System.out.println("Name: ");
		String name = s.next();
		System.out.println("Address: ");
		String addr = s.next();
		System.out.println("STARTING BALANCE ($0.00): ");
		float startingBalance = s.nextFloat();
		// TODO
	}

	private void printName() {
		System.out.println("ENTER NAME: ");
		String name = s.next();
		//TODO
	}


	public void deleteAccount() {
		System.out.println("Enter user to delete: ");
		String user = s.next();
		//TODO
	}

	public void findAccount(){
		//TODO: implement
	}
	
	
	
	

}
