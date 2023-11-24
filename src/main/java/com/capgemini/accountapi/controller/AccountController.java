package com.capgemini.accountapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@Operation(summary = "Create a new user account", description = "Creates a new user account with the specified initial credit.", tags = {
			"Account" })
	@ApiResponse(responseCode = "200", description = "User account created successfully", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Account.class)) })
	@ApiResponse(responseCode = "404", description = "Account not found")

	@PostMapping
	public ResponseEntity<Object> createAccount(
			@RequestParam(name = "customerId", required = true) @Parameter(description = "Customer ID") String customerId,
			@RequestParam(name = "initialCredit", required = true) @Parameter(description = "Initial credit for the account") double initialCredit) {
		System.err.println("test");
		Account account = accountService.openNewAccount(customerId, initialCredit);
		if (account != null) {
			return ResponseEntity.status(HttpStatus.OK).body(account);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account Not Found");
	}

	@Operation(summary = "Get user account information", description = "Retrieves information about a user account.", tags = {
			"Account" }, responses = {
					@ApiResponse(responseCode = "200", description = "User account information retrieved successfully", content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Account.class)) }),
					@ApiResponse(responseCode = "404", description = "Account not found"),
					@ApiResponse(responseCode = "202", description = "Account not active")})
	@GetMapping
	public ResponseEntity<Account> getAccount(@RequestParam String accountId) {
		Account account = accountService.getAccountInformation(accountId);
		if (account == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		if (account.isActive()) {
			return ResponseEntity.status(HttpStatus.OK).body(account);
		} else {
			System.err.println(" Not Active  ");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
		}
	}
}
