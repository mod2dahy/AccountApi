package com.capgemini.services;

import org.springframework.stereotype.Service;

import com.capgemini.model.Transaction;
import com.capgemini.model.TransactionType;

@Service
public class TransactionService {

	public Transaction createTransaction(String customerId, double initialCredit) {
		Transaction transaction = new Transaction();
		transaction.setAmount(initialCredit);
		transaction.setUserCustomerId(customerId);
		transaction.setTransactionType(TransactionType.DEBIT);
		return transaction;
	}

}
