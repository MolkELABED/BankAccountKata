package org.project.BankAccountKata;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.project.BankAccountKata.entity.Account;
import org.project.BankAccountKata.exception.AccountOperationsException;
import org.project.BankAccountKata.service.TransactionService;
import org.project.BankAccountKata.repository.Operation;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BankAccountKataApplicationTests {

	private TransactionService service = new TransactionService();


	@Test
	@Order(1)
	public void should_throw_exception_when_account_not_found() {
		//arrange
		Long accountId = 10L;
		
		//assert
		assertThrows(AccountOperationsException.class, () -> {
			service.findAccount(accountId);
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
		Account actualAccount = service.findAccount(accountId);

		//assert
		assertThat(actualAccount.getId()).isEqualTo(expectedAccount.getId());
	}
	
	@Test
	public void should_display_list_accounts() {
		//arrange
		Long customerId = 1L;
		List<Account> expectedAccountsList = Arrays.asList(new Account(1L, 1000D));
		
		//act
		List<Account> actualAccountsList = service.customerAccountsList(customerId);

		//assert
		assertThat(actualAccountsList.get(0).getId()).isEqualTo(expectedAccountsList.get(0).getId());
	}
	
	@Test
	public void should_throw_exception_when_customer_not_found() {
		//arrange
		Long customerId = 5L;
		
		//assert
		assertThrows(AccountOperationsException.class, () -> {
			service.customerAccountsList(customerId);
		});
	}
	
	@Test
	public void should_throw_exception_when_account_is_not_associated_to_the_customer() {
		//arrange
		Long accountId = 5L;
		Long customerId = 1L;
		
		//assert
		assertThrows(AccountOperationsException.class, () -> {
			service.displayBalance(customerId, accountId);
		});
	}
	
	@Test
	public void should_display_balance() {
		//arrange
		Long accountId = 1L;
		Long customerId = 1L;
		Double expectedBalance = 1000D;
		
		//act
		Double actualBalance = service.displayBalance(customerId, accountId);
		
		//assert
		assertThat(actualBalance).isEqualTo(expectedBalance);
	}
	
	@Test
	public void should_throw_exception_when_customer_deposit_is_less_than_the_minimum_deposit() {
		//arrange
		Long accountId = 1L;
		Double minimumDeposit = 0.01;
		
		//assert
		assertThrows(AccountOperationsException.class, () -> {
			service.operation(Operation.DEPOSIT, minimumDeposit, accountId);
		});
	}
	
	@Test
	public void should_deposit_money() {
		//arrange
		Long accountId = 1L;
		Account account = service.findAccount(accountId);
		Double amount = 200D;
		Double expectedBalance = 1200D;
		
		//act
		service.operation(Operation.DEPOSIT, amount, accountId);
		
		//assert
		assertThat(account.getBalance()).isEqualTo(expectedBalance);
	}
	
	@Test
	public void should_throw_exception_when_an_overdraft_is_used() {
		//arrange
		Long accountId = 2L;
		Double amount = 2000D;
		
		//assert
		assertThrows(AccountOperationsException.class, () -> {
			service.operation(Operation.WITHDRAW, amount, accountId);
		});
	}
	
	@Test
	public void should_withdraw_money() {
		//arrange
		Long accountId = 1L;
		Account account = service.findAccount(accountId);
		Double amount = 200D;
		Double expectedBalance = 1000D;
		
		//act
		service.operation(Operation.WITHDRAW, amount, accountId);
		
		//assert
		assertThat(account.getBalance()).isEqualTo(expectedBalance);
	}
	
	@Test
	void contextLoads() {
	}

}
