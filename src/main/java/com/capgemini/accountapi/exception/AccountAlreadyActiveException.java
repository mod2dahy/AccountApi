package com.capgemini.accountapi.exception;


public class AccountAlreadyActiveException extends RuntimeException {
    public AccountAlreadyActiveException(String message) {
        super(message);
    }
}
