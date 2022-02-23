package org.project.BankAccountKata.repository;

import java.util.Arrays;
import java.util.List;

import org.project.BankAccountKata.entity.Account;
import org.project.BankAccountKata.exception.AccountOperationsException;
import static org.project.BankAccountKata.exception.AccountOperationsException.ACCOUNT_NOT_FOUND;

public class AccountRepository {
	public static List<Account> accounts = Arrays.asList(new Account(1L, 1000D),
			                                             new Account(2L, 2000D),
			                                             new Account(3L, 3000D),
			                                             new Account(4L, 4000D),
			                                             new Account(5L, 5000D),
			                                             new Account(6L, 6000D));
	
	public AccountRepository() {
	}
	
	public Account findAccount(Long accountId) {
		return accounts.stream()
				.filter(account -> account.getId() == accountId)
				.findAny()
				.orElseThrow(() -> new AccountOperationsException(ACCOUNT_NOT_FOUND));
	}
}
