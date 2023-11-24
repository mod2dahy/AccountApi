package com.capgemini.accountapi.repository;



import org.springframework.stereotype.Repository;

import com.capgemini.accountapi.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Repository
public class TransactionRepository {

    // In-memory storage using a HashMap
    private final Map<String, List<Transaction>> transactionsByCustomerId = new HashMap<>();

    public Transaction save(Transaction transaction) {
        String customerId = transaction.getUserCustomerId();
        transactionsByCustomerId.computeIfAbsent(customerId, k -> new ArrayList<>()).add(transaction);
        return transaction;
    }

    public List<Transaction> findByCustomerId(String customerId) {
        return transactionsByCustomerId.getOrDefault(customerId, new ArrayList<>());
    }
}
