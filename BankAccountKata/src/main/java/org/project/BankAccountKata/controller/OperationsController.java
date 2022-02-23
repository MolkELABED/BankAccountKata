package org.project.BankAccountKata.controller;

import org.project.BankAccountKata.entity.Account;
import org.project.BankAccountKata.exception.AccountOperationsException;
import org.project.BankAccountKata.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperationsController {
	@Autowired(required = false)
	TransactionService transactionService = new TransactionService();
	
	//find account by id
	@GetMapping(value=("/accounts/{accountId}"))
	public ResponseEntity<?> findAccount(@PathVariable("accountId") Long accountId) {
		try {
			return new ResponseEntity<Account>(transactionService.findAccount(accountId), HttpStatus.OK);
		} catch(AccountOperationsException aoe) {
			return new ResponseEntity<String>(aoe.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	//find list of account associated to a customer by id of the customer
	@GetMapping(value=("/customers/{customerId}/accounts"))
	public ResponseEntity<?> customerAccountsList(@PathVariable("customerId") Long customerId) {
		try {
			return new ResponseEntity<>(transactionService.customerAccountsList(customerId), HttpStatus.OK);
		} catch(AccountOperationsException aoe) {
			return new ResponseEntity<>(aoe.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	//display the balance of an account by the customer id and the account id
	@GetMapping(value="/accounts/{customerId}/{accountId}/balance")
	public ResponseEntity<?> displayBalance(@PathVariable("accountId") Long accountId, @PathVariable("customerId") Long customerId) {
		try {
			return new ResponseEntity<>(transactionService.displayBalance(accountId, customerId), HttpStatus.OK);
		} catch(AccountOperationsException aoe) {
			return new ResponseEntity<>(aoe.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
