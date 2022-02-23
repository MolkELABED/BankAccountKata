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
}
