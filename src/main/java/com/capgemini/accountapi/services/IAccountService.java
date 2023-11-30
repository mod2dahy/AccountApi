package com.capgemini.accountapi.services;

import com.capgemini.accountapi.model.Account;

public interface IAccountService {

    Account openNewAccount(String customerId, double initialCredit);

    Account getAccountInformation(String customerId);
}
