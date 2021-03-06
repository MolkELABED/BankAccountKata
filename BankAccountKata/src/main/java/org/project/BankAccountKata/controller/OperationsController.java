package org.project.BankAccountKata.controller;

import org.project.BankAccountKata.entity.Account;
import org.project.BankAccountKata.exception.AccountOperationsException;
import org.project.BankAccountKata.service.TransactionService;
import org.project.BankAccountKata.entity.Customer;
import org.project.BankAccountKata.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperationsController {
	@Autowired(required = false)
	TransactionService transactionService = new TransactionService();
	
	//Find account by id
	@GetMapping(value=("/accounts/{accountId}"))
	public ResponseEntity<?> findAccount(@PathVariable("accountId") Long accountId) {
		try {
			return new ResponseEntity<Account>(transactionService.findAccount(accountId), HttpStatus.OK);
		} catch(AccountOperationsException aoe) {
			return new ResponseEntity<String>(aoe.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	//Find customer by id
	@GetMapping(value=("/customers/{customerId}"))
	public ResponseEntity<?> findCustomer(@PathVariable("customerId") Long customerId) {
		try {
			return new ResponseEntity<Customer>(transactionService.findCustomer(customerId), HttpStatus.OK);
		} catch(AccountOperationsException aoe) {
			return new ResponseEntity<String>(aoe.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	//Find list of accounts associated to a customer using id of the customer
	@GetMapping(value=("/customers/{customerId}/accounts"))
	public ResponseEntity<?> customerAccountsList(@PathVariable("customerId") Long customerId) {
		try {
			return new ResponseEntity<>(transactionService.customerAccountsList(customerId), HttpStatus.OK);
		} catch(AccountOperationsException aoe) {
			return new ResponseEntity<>(aoe.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	//Display the balance of an account using the customer id and the account id
	@GetMapping(value="/accounts/{customerId}/{accountId}/balance")
	public ResponseEntity<?> displayBalance(@PathVariable("accountId") Long accountId, @PathVariable("customerId") Long customerId) {
		try {
			return new ResponseEntity<>(transactionService.displayBalance(accountId, customerId), HttpStatus.OK);
		} catch(AccountOperationsException aoe) {
			return new ResponseEntity<>(aoe.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//Save an operation(deposit or withdraw) using id of account
	@PostMapping(value="/transactions/{accountId}")
	public ResponseEntity<?> operation(@RequestBody Transaction transaction) {
		try {
			return new ResponseEntity<>(transactionService.operation(transaction.getOperationName(), transaction.getAmount(), transaction.getId()), HttpStatus.OK);
		} catch(AccountOperationsException aoe) {
			return new ResponseEntity<>(aoe.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//Find an operation using the id of the account
	@GetMapping(value="/transactions/{accountId}")
	public ResponseEntity<?> accountOperationsList(@PathVariable("accountId") Long accountId) {
		try {
			return new ResponseEntity<>(transactionService.accountOperationsList(accountId), HttpStatus.OK);
		} catch(AccountOperationsException aoe) {
			return new ResponseEntity<>(aoe.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
