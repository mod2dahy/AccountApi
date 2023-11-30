package com.capgemini.accountapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import com.capgemini.accountapi.exception.AccountAlreadyActiveException;
import com.capgemini.accountapi.exception.AccountNotFoundException;
import com.capgemini.accountapi.model.Account;
import com.capgemini.accountapi.model.Transaction;
import com.capgemini.accountapi.repository.AccountRepository;

@Service
public class AccountService implements IAccountService {

	private static final Logger LOGGER = Logger.getLogger(AccountService.class.getName());

	private final ITransactionService transactionService;
	private final AccountRepository accountRepository;

	@Autowired
	public AccountService(TransactionService transactionService, AccountRepository accountRepository) {
		this.transactionService = transactionService;
		this.accountRepository = accountRepository;
	}

	public Account openNewAccount(String customerId, double initialCredit) {
		Account account = accountRepository.findByCustomerId(customerId);

		if (account == null) {
			LOGGER.warning("Account not found for customerId:" + customerId);
			throw new AccountNotFoundException(customerId);
		}
		if (account.isActive()) {
			LOGGER.warning("Account is already  Activated for customerId:" + customerId);
			throw new AccountAlreadyActiveException("Cannot create account. Account is already activated.");
		}

		account.setActive(true);
		LOGGER.info("Account is now active for customerId:" + customerId);

		if (initialCredit != 0) {
			Transaction transaction = transactionService.createTransaction(customerId, initialCredit);
			account.getTransactions().add(transaction);
			account.setBalance(initialCredit);
		}

		accountRepository.save(account);

		return account;
	}

	public Account getAccountInformation(String customerId) {
		LOGGER.info("Fetching account information forcustomerId:" + customerId);
		return accountRepository.findByCustomerId(customerId);
	}
}
