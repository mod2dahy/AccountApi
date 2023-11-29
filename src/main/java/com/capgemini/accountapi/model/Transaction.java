package com.capgemini.accountapi.model;



public class Transaction {
	
	
	
    public Transaction(String userCustomerId, TransactionType transactionType, double amount) {
		super();
		
		this.transactionType = transactionType;
		this.amount = amount;
		this.userCustomerId = userCustomerId;
	}

	private Long id;
    
    public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getUserCustomerId() {
		return userCustomerId;
	}

	public void setUserCustomerId(String userCustomerId) {
		this.userCustomerId = userCustomerId;
	}

	private TransactionType transactionType;

    private double amount;

    private String userCustomerId;

    // getters and setters
}
