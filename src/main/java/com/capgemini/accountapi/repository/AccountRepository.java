package com.capgemini.accountapi.repository;


import org.springframework.stereotype.Repository;

import com.capgemini.accountapi.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class AccountRepository {

    // In-memory storage using a List (simulating a repository)
    private final List<Account> customersAccounts = new ArrayList<>();

    public AccountRepository() {
        // Hardcoded customer accounts (for simulation purposes)
        customersAccounts.add(new Account("100001", "CustomerName1", "Surname1", false));
        customersAccounts.add(new Account("100002", "CustomerName2", "Surname2", false));
        customersAccounts.add(new Account("100003", "CustomerName3", "Surname3", false));
    }

    public Account findByCustomerId(String customerId) {
    	
        Optional< Account>  searchAccount= customersAccounts.stream()
                .filter(account -> account.getCustomerID().equals(customerId))
                .findFirst();
        if ( searchAccount.isPresent())
        	return searchAccount.get();
        else 
        	return null;
    }

    public void save(Account account) {
        customersAccounts.add(account);
    }
}
