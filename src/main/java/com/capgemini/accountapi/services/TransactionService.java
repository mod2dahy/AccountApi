package com.capgemini.accountapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.accountapi.model.Transaction;
import com.capgemini.accountapi.model.TransactionType;
import com.capgemini.accountapi.repository.TransactionRepository;

import java.util.List;


@Service
public class TransactionService implements ITransactionService{

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(String customerId, double amount) {
        Transaction transaction = new Transaction(customerId,TransactionType.DEBIT,amount);        
        // You may want to set other transaction details such as timestamp, description, etc.
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByCustomerId(String customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }
}
