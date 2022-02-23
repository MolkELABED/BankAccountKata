package org.project.BankAccountKata.controller;

import org.project.BankAccountKata.entity.Account;
import org.project.BankAccountKata.exception.AccountOperationsException;
import org.project.BankAccountKata.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperationsController {
	@Autowired(required = false)
	AccountRepository accountRepository = new AccountRepository();
	
	//find account by id
	@GetMapping(value=("/accounts/{accountId}"))
	public ResponseEntity<?> findAccount(@PathVariable("accountId") Long accountId) {
		try {
			return new ResponseEntity<Account>(accountRepository.findAccount(accountId), HttpStatus.OK);
		} catch(AccountOperationsException aoe) {
			System.out.println(aoe.getMessage());
			return new ResponseEntity<String>(aoe.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
