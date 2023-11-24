package com.capgemini.accountapi.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
   
   

	public Account(String customerID, String name, String surname,boolean active) {
		super();
		this.customerId = customerID;
		this.name = name;
		this.surname = surname;
		this.active=active;
		
	}


	public String getCustomerID() {
		return customerId;
	}


	public void setCustomerID(String customerID) {
		this.customerId = customerID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public List<Transaction> getTransactions() {
		return transactions;
	}


	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	private boolean active;
	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}

	private String customerId;
    private String name;
    private String surname;
    private double balance;

    
    private List<Transaction> transactions = new ArrayList<>();

    // getters and setters
}