package com.capgemini.accountapi.repository;

import org.springframework.stereotype.Repository;

import com.capgemini.accountapi.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {

    private final List<Account> customersAccounts = new ArrayList<>();

    // Constructor to initialize some sample accounts
    public AccountRepository() {
        initializeSampleAccounts();
    }

    // Method to initialize sample accounts
    private void initializeSampleAccounts() {
        save(new Account("100001", "CustomerName1", "Surname1", false));
        save(new Account("100002", "CustomerName2", "Surname2", false));
        save(new Account("100003", "CustomerName3", "Surname3", false));
    }

    // Method to get the total number of accounts
    public int getTotalAccounts() {
        return customersAccounts.size();
    }

    // Method to find an account by customer ID
    public Account findByCustomerId(String customerId) {
        Optional<Account> searchAccount = customersAccounts.stream()
                .filter(account -> account.getCustomerID().equals(customerId))
                .findFirst();
        return searchAccount.orElse(null);
    }

    // Method to save an account
    public void save(Account account) {
        customersAccounts.add(account);
    }
}
