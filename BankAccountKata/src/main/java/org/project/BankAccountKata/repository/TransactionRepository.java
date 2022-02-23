package org.project.BankAccountKata.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.project.BankAccountKata.entity.Transaction;

public class TransactionRepository {
	public static List<Transaction> transactions = new ArrayList<>();
	
	public void save(Long accountId, Operation operation, Double amount) {
		Transaction transaction = new Transaction(accountId, LocalDateTime.now(), operation, amount);
		transactions.add(transaction);
	}
}
