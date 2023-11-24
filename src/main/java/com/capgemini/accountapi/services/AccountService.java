package com.capgemini.accountapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.accountapi.exception.AccountNotFoundException;
import com.capgemini.accountapi.model.Account;
import com.capgemini.accountapi.model.Transaction;
import com.capgemini.accountapi.repository.AccountRepository;

@Service
public class AccountService {

    private final TransactionService transactionService;
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(TransactionService transactionService, AccountRepository accountRepository) {
        this.transactionService = transactionService;
        this.accountRepository = accountRepository;

    }
    
   
 
    public Account openNewAccount(String customerId, double initialCredit) {
    	
        Account account = accountRepository.findByCustomerId(customerId);
        if (account ==null)
        	return null;

        account.setActive(true);
        System.err.println("account is active");

        if (initialCredit != 0) {
            Transaction transaction = transactionService.createTransaction(customerId, initialCredit);
            account.getTransactions().add(transaction);
            account.setBalance(initialCredit);
        }

        accountRepository.save(account);

        return account;
    }

    public Account getAccountInformation(String customerId) {
    	
    	
        return accountRepository.findByCustomerId(customerId);
                
    }
}