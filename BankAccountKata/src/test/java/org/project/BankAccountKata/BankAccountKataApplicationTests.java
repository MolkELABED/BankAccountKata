package org.project.BankAccountKata;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.project.BankAccountKata.entity.Account;
import org.project.BankAccountKata.exception.AccountOperationsException;
import org.project.BankAccountKata.repository.AccountRepository;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BankAccountKataApplicationTests {

	AccountRepository accountRepository = new AccountRepository();

	@Test
	@Order(1)
	public void should_throw_exception_when_account_not_found() {
		//arrange
		Long accountId = 10L;
		
		//assert
		assertThrows(AccountOperationsException.class, () -> {
			accountRepository.findAccount(accountId);
		});
	}
	
	@Test
	@Order(2)
	public void should_find_account() {
		//arrange
		Long accountId = 1L;
		Double balance = 1000D;
		Account expectedAccount = new Account(accountId, balance);

		//act
		Account actualAccount = accountRepository.findAccount(accountId);

		//assert
		assertThat(actualAccount.getId()).isEqualTo(expectedAccount.getId());
	}
	
	@Test
	void contextLoads() {
	}

}
