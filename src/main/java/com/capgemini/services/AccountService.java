package com.capgemini.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Account;
import com.capgemini.model.Transaction;
import com.capgemini.model.TransactionType;

@Service
public class AccountService {

	@Autowired
	private TransactionService transactionService;

	private final List<Account> customersAccounts = new ArrayList();

	public AccountService() {
		// already existing customers
		/// simulate if you there customer List id with empty account list
		customersAccounts.add(new Account("100001", "CustomerName1", "sureName1", false));
		customersAccounts.add(new Account("100002", "CustomerName2", "sureName2", false));
		customersAccounts.add(new Account("100003", "CustomerName3", "sureName3", false));

	}

	public Account openNewAccount(String customerId, double initialCredit) {

		Optional<Account> foundAccount = customersAccounts.stream()
				.filter(account -> account.getCustomerID().equals(customerId)).findFirst();
		if (foundAccount.isEmpty())
			return null;// return not found
		foundAccount.get().setActive(true);
		if (initialCredit != 0) {
			foundAccount.get().getTransactions().add(transactionService.createTransaction(customerId, initialCredit));
			foundAccount.get().setBalance(initialCredit);
		}

		return foundAccount.get();
	}

	public Account getAccountInformation(String customerId) {
		Optional<Account> foundAccount = customersAccounts.stream()
				.filter(account -> account.getCustomerID().equals(customerId)).findFirst();
		if (foundAccount.isEmpty())
			return null;// return not found

		return foundAccount.get();
	}
}
