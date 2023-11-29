package com.capgemini.accountapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.accountapi.exception.AccountNotFoundException;
import com.capgemini.accountapi.model.Account;
import com.capgemini.accountapi.services.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    private static final HttpStatus OK = HttpStatus.OK;
    private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;
    private static final HttpStatus ACCEPTED = HttpStatus.ACCEPTED;

    private <T> ResponseEntity<T> response(HttpStatus status, T body) {
        
    	return ResponseEntity.status(status).body(body);
    }

    @Operation(summary = "Get user account information", description = "Retrieve information about a user account.", tags = {"Account"})
    @ApiResponse(responseCode = "200", description = "User account information retrieved successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))})
    @ApiResponse(responseCode = "404", description = "Account not found")
    @ApiResponse(responseCode = "202", description = "Account not active")
    @GetMapping
    public ResponseEntity<Account> getAccount(
            @RequestParam(name = "customerId", required = true) @Parameter(description = "Customer Id") String customerId) {
        Account account = accountService.getAccountInformation(customerId);

        if (account == null) {
            return response(NOT_FOUND, null);
        }

        return account.isActive() ? response(OK, account) : response(ACCEPTED, null);
    }

    @Operation(summary = "Create a new user account", description = "Creates a new user account with the specified initial credit.", tags = {"Account"})
    @ApiResponse(responseCode = "200", description = "User account created successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))})
    @ApiResponse(responseCode = "404", description = "Account not found")
    @PostMapping
    public ResponseEntity<Object> createAccount(
            @RequestParam(name = "customerId", required = true) @Parameter(description = "Customer ID") String customerId,
            @RequestParam(name = "initialCredit", required = true) @Parameter(description = "Initial credit for the account") double initialCredit) {
    	 try {
             Account account = accountService.openNewAccount(customerId, initialCredit);
             return (account != null) ? response(OK, account) : response(NOT_FOUND, null);
         } catch (AccountNotFoundException ex) {
             // Handle AccountNotFoundException and return appropriate response
             return handleAccountNotFoundException(ex);
         }
    }
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
