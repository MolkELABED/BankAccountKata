package org.project.BankAccountKata.repository;

import java.util.Arrays;
import java.util.List;

import org.project.BankAccountKata.entity.Account;
import org.project.BankAccountKata.entity.Customer;
import org.project.BankAccountKata.exception.AccountOperationsException;

public class CustomerRepository {
	public static List<Account> accounts = AccountRepository.accounts;
	public static List<Account> customer1Accounts = Arrays.asList(accounts.get(0));
	public static List<Account> customer2Accounts = Arrays.asList(accounts.get(1), accounts.get(2));
	public static List<Account> customer3Accounts = Arrays.asList(accounts.get(3), accounts.get(4), accounts.get(5));
	public static List<Customer> customers =  Arrays.asList(new Customer(1L, "customer1", customer1Accounts), 
			new Customer(2L, "customer2", customer2Accounts),
			new Customer(3L, "customer3", customer3Accounts));

	public CustomerRepository() {
	}
	
	public Customer findCustomer(Long customerId) {
		return customers.stream()
				.filter(customer -> customer.getId() == customerId)
				.findAny()
				.orElseThrow(() -> new AccountOperationsException("Customer Not found"));
	}
	
	//get the list of accounts associated to a customer using the id of customer
	public List<Account> customerAccountsList(Long customerId) {
		List<Account> accountsList = null;
		List<Account> customerAccountsList = findCustomer(customerId).getAccounts();
		if (customerAccountsList.size() > 0) {
			accountsList = customerAccountsList;
		} else {
			throw new AccountOperationsException("Account not found");
		}
		return accountsList;
	}
	
	//display the balance of an account using the id of the customer and the id of the account
	public Double displayBalance(Long customerId, Long accountId) {
		return findCustomer(customerId).getAccounts().stream()
				.filter(account -> account.getId() == accountId)
				.findAny()
				.map(Account::getBalance)
				.orElseThrow(() -> new AccountOperationsException("Account not found"));
	}
}
