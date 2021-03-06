package org.project.BankAccountKata.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.project.BankAccountKata.entity.Transaction;

public class TransactionRepository {
	public static List<Transaction> transactions = new ArrayList<>();
	
	public void save(Long accountId, Operation operation, Double amount) {
		Transaction transaction = new Transaction(accountId, LocalDateTime.now(), operation, amount);
		transactions.add(transaction);
	}
	
	public List<Transaction> findTransactionByAccountId(Long accountId) {
		return transactions.stream()
				.filter(transaction -> transaction.getId() == accountId)
				.collect(Collectors.toList());
	}
}
