package com.capgemini.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

import com.capgemini.model.Account;
import com.capgemini.services.AccountService;

@RestController
@RequestMapping("/api/accounts")

public class AccountController {
	@Autowired
	private AccountService accountService;

	
	@Operation(operationId = "createAccount", summary = "Create a new user account", description = "Creates a new user account with the specified initial credit.", tags = {
			"Account" })
	@ApiResponse(responseCode = "404", description = "Account not found")

	@PostMapping
	public ResponseEntity<Account> createAccount(
			@RequestParam(name = "customerId", required = true) @Parameter(description = "Customer ID") String customerId,
			@RequestParam(name = "initialCredit", required = true) @Parameter(description = "Initial credit for the account") double initialCredit)

	{
		Account account = accountService.openNewAccount(customerId, initialCredit);
		if (account != null)
			return ResponseEntity.status(HttpStatus.OK).body(account);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	
	/**
	 * GET /accounts : Get user account information Retrieves information about a
	 * user account.
	 *
	 * @param accountId Account ID (required)
	 * @return User account information retrieved successfully (status code 200) or
	 *         Account not found (status code 404)
	 */

	

	@ApiResponse(responseCode = "404", description = "Not Found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)) })
	@ApiResponse(responseCode = "202", description = "Account Not Acitve")

	@GetMapping
	public ResponseEntity<Account> getAccount(@RequestParam String accountId) {

		Account account = accountService.getAccountInformation(accountId);
		if (account == null)

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

		if (account.isActive())

			return ResponseEntity.status(HttpStatus.OK).body(account);
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
	}

}
