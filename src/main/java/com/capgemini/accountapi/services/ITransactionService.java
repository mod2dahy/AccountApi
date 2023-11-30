package com.capgemini.accountapi.services;

import java.util.List;

import com.capgemini.accountapi.model.Transaction;

public interface ITransactionService {
	public Transaction createTransaction(String customerId, double amount);

	public List<Transaction> getTransactionsByCustomerId(String customerId);
}
