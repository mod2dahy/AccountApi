package com.capgemini.accountapi.repository;

import com.capgemini.accountapi.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountRepositoryTest {

    @Test
    void getTotalAccounts() {
        AccountRepository accountRepository = new AccountRepository();
        int totalAccounts = accountRepository.getTotalAccounts();
        Assertions.assertEquals(3, totalAccounts);
    }

    @Test
    void findByCustomerId_existingCustomer() {
        AccountRepository accountRepository = new AccountRepository();
        Account foundAccount = accountRepository.findByCustomerId("100001");
        Assertions.assertNotNull(foundAccount);
        Assertions.assertEquals("CustomerName1", foundAccount.getName());
    }

    @Test
    void findByCustomerId_nonExistingCustomer() {
        AccountRepository accountRepository = new AccountRepository();
        Account foundAccount = accountRepository.findByCustomerId("NonExistingCustomer");
        Assertions.assertNull(foundAccount);
    }

    @Test
    void save() {
        AccountRepository accountRepository = new AccountRepository();
        Account newAccount = new Account("100004", "NewCustomer", "NewSurname", false);
        accountRepository.save(newAccount);

        int totalAccounts = accountRepository.getTotalAccounts();
        Assertions.assertEquals(4, totalAccounts);

        Account retrievedAccount = accountRepository.findByCustomerId("100004");
        Assertions.assertNotNull(retrievedAccount);
        Assertions.assertEquals("NewCustomer", retrievedAccount.getName());
    }
}
