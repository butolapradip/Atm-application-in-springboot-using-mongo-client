package com.example.ReadingText;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account")
public class Account {
	@Id
	@Size(min = 14, message = "Name should have atleast 14 characters")
	private long accountNo;
	@Size(min = 4, message = "Name should have atleast 4 characters")
	private int pin;
	private int balance;

	public long getAccountNo() {
		return accountNo;
	}
	public Account()
	{
		
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Account(long accountNo, int pin) {
		super();
		this.accountNo = accountNo;
		this.pin = pin;
	}
	public Account(int balance) {
		//super();
		this.balance = balance;
	}
}
