package main;

public class Enfrentamiento {
	private int balance;
	public Enfrentamiento (){
		this.balance=0;
	}
	public void actBalance(int res) {
		balance = balance+res;
	}
	public int getBalance() {
		return balance;
	}
}
