package manager;

public class Customer {
	private String name = null;
	private String address = null;
	private float startingBalance = 0;

	public Customer(String name, String address, float startingBalance) {
		this.name = name;
		this.address = address;
		this.startingBalance = startingBalance;
	}

	public void withdraw(float amount) {
		if (amount <= startingBalance) {
			startingBalance -= amount;
		}	
	}
	
	public void lodge(float amount) {
		startingBalance += amount;
	}
	
	public void transfer(String user, float amount) {
		
	}
	
	public void getInfo() {
		System.out.println("ACCOUNT NAME: "+name);
		System.out.println("BALANCE: $"+startingBalance);
	}
	
	public boolean isName(String n) {
		return name.equals(n);
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	
	
	
}