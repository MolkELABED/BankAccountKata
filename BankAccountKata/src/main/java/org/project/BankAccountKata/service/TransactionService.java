package org.project.BankAccountKata.service;

import java.util.List;

import org.project.BankAccountKata.entity.Account;
import org.project.BankAccountKata.entity.Customer;
import org.project.BankAccountKata.exception.AccountOperationsException;
import static org.project.BankAccountKata.exception.AccountOperationsException.ACCOUNT_NOT_FOUND;
import static org.project.BankAccountKata.exception.AccountOperationsException.INSUFFICIENT_CREDIT;
import static org.project.BankAccountKata.exception.AccountOperationsException.DEPOSIT_LESS_THAN_MINIMUM_DEPOSIT;
import static org.project.BankAccountKata.exception.AccountOperationsException.NO_OPERATION_FOUND;
import org.project.BankAccountKata.repository.AccountRepository;
import org.project.BankAccountKata.repository.CustomerRepository;
import org.project.BankAccountKata.repository.TransactionRepository;
import org.project.BankAccountKata.entity.Transaction;
import org.project.BankAccountKata.repository.Operation;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionService {
	static final Double MIN_DEPOSIT = 0.01;

	@Autowired(required = false)
	AccountRepository accountRepository = new AccountRepository();
		
	@Autowired(required = false)
	CustomerRepository customerRepository = new CustomerRepository();
	
	@Autowired(required = false)
	TransactionRepository transactionRepository= new TransactionRepository();
	
	public Account findAccount(Long accountId) {
		return accountRepository.findAccount(accountId);
	}
	
	public Customer findCustomer(Long customerId) {
		return customerRepository.findCustomer(customerId);
	}
	
	//display the balance of an account using the id of the customer and the id of the account
	public Double displayBalance(Long customerId, Long accountId) {
		return customerRepository.findCustomer(customerId).getAccounts().stream()
				.filter(account -> account.getId() == accountId)
				.findAny()
				.map(Account::getBalance)
				.orElseThrow(() -> new AccountOperationsException(ACCOUNT_NOT_FOUND));
	}
	
	//Return the list of accounts associated to a customer using the id of customer
	public List<Account> customerAccountsList(Long customerId) {
		List<Account> accountsList = null;
		List<Account> customerAccountsList = customerRepository.findCustomer(customerId).getAccounts();
		
		if (customerAccountsList.size() > 0) {
			accountsList = customerAccountsList;
		} else {
			throw new AccountOperationsException(ACCOUNT_NOT_FOUND);
		}
		return accountsList;
	}
	
	//Save or retrieve money from an account using the operationName=DEPOSIT/WITHDRAW, amount and id of the account
	public Account operation(Operation operationName, Double amount, Long acountId) {
		Account account = accountRepository.findAccount(acountId);
		
		switch(operationName) {
		case WITHDRAW:
			if (account.getBalance() > amount) {
				account.setBalance(account.getBalance() - amount);
				transactionRepository.save(acountId, operationName, amount);
				return account;
			} else {
				throw new AccountOperationsException(INSUFFICIENT_CREDIT);
			}
		case DEPOSIT:
			if (amount > MIN_DEPOSIT) {
				account.setBalance(account.getBalance() + amount);
				transactionRepository.save(acountId, operationName, amount);
				return account;
			} else {
				throw new AccountOperationsException(DEPOSIT_LESS_THAN_MINIMUM_DEPOSIT);
			}
		}
		return null;
	}
	
	//Return the list of operations of an account using the id of the account
	public List<Transaction> accountOperationsList(Long accountId) {
		List<Transaction> transactionList = null;
		List<Transaction> accountTransactionList = transactionRepository.findTransactionByAccountId(accountId);
		
		if (accountTransactionList.size() > 0) {
			transactionList = transactionRepository.findTransactionByAccountId(accountId);
		} else {
			throw new AccountOperationsException(NO_OPERATION_FOUND);
		}
		
		return transactionList;
	}
}
