
public class Account {
	double balance;
	
	public Account() {
		this.balance = 0;
	}

	public double getBalance() {
		return this.balance;
	}
	
	public void deposit_money(double money) {
		if(money <= 0) {
			throw new IllegalArgumentException("you can't deposit minus or zero!");
		}
		
		this.balance += money;
	}
}



