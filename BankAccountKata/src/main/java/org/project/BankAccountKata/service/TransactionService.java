package org.project.BankAccountKata.service;

import java.util.List;

import org.project.BankAccountKata.entity.Account;
import org.project.BankAccountKata.entity.Customer;
import org.project.BankAccountKata.exception.AccountOperationsException;
import org.project.BankAccountKata.repository.AccountRepository;
import org.project.BankAccountKata.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionService {
	List<Account> accounts = AccountRepository.accounts;
	List<Customer> customers = CustomerRepository.customers;

	@Autowired(required = false)
	AccountRepository accountRepository = new AccountRepository();
		
	@Autowired(required = false)
	CustomerRepository customerRepository = new CustomerRepository();
	
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
				.orElseThrow(() -> new AccountOperationsException("Account not found"));
	}
	
	//get the list of accounts associated to a customer using the id of customer
	public List<Account> customerAccountsList(Long customerId) {
		List<Account> accountsList = null;
		List<Account> customerAccountsList = customerRepository.findCustomer(customerId).getAccounts()
;
		if (customerAccountsList.size() > 0) {
			accountsList = customerAccountsList;
		} else {
			throw new AccountOperationsException("Account not found");
		}
		return accountsList;
	}
}
