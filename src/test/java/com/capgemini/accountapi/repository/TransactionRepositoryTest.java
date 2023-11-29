package com.capgemini.accountapi.repository;

import com.capgemini.accountapi.model.Transaction;
import com.capgemini.accountapi.model.TransactionType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TransactionRepositoryTest {

    @Test
    void save() {
        TransactionRepository transactionRepository = new TransactionRepository();
        Transaction transaction = new Transaction("100001", TransactionType.CREDIT, 500.0);
        Transaction savedTransaction = transactionRepository.save(transaction);

        Assertions.assertNotNull(savedTransaction);
        Assertions.assertEquals("100001", savedTransaction.getUserCustomerId());
        Assertions.assertEquals(TransactionType.CREDIT, savedTransaction.getTransactionType());
        Assertions.assertEquals(500.0, savedTransaction.getAmount());

        List<Transaction> transactions = transactionRepository.findByCustomerId("100001");
        Assertions.assertEquals(1, transactions.size());
        Assertions.assertEquals(savedTransaction, transactions.get(0));
    }

    @Test
    void findByCustomerId_existingCustomer() {
        TransactionRepository transactionRepository = new TransactionRepository();
        Transaction transaction1 = new Transaction("100001", TransactionType.CREDIT, 500.0);
        Transaction transaction2 = new Transaction("100001", TransactionType.DEBIT, 200.0);

        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);

        List<Transaction> transactions = transactionRepository.findByCustomerId("100001");

        Assertions.assertEquals(2, transactions.size());
        Assertions.assertEquals(transaction1, transactions.get(0));
        Assertions.assertEquals(transaction2, transactions.get(1));
    }

    @Test
    void findByCustomerId_nonExistingCustomer() {
        TransactionRepository transactionRepository = new TransactionRepository();
        List<Transaction> transactions = transactionRepository.findByCustomerId("NonExistingCustomer");
        Assertions.assertNotNull(transactions);
        Assertions.assertTrue(transactions.isEmpty());
    }
}
