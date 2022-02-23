package org.project.BankAccountKata.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.project.BankAccountKata.repository.Operation;

@Entity
public class Transaction {
  
  public Transaction(Long accountId, LocalDateTime date, Operation operationName, Double amount) {
	super();
	this.id = accountId;
	this.date = date;
	this.operationName = operationName;
	this.amount = amount;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public LocalDateTime getDate() {
	return date;
}

public void setDate(LocalDateTime date) {
	this.date = date;
}

public Operation getOperationName() {
	return operationName;
}

public void setOperationName(Operation operationName) {
	this.operationName = operationName;
}

public Double getAmount() {
	return amount;
}

public void setAmount(Double amount) {
	this.amount = amount;
}

  @Id
  Long id;

  LocalDateTime date;
  
  Operation operationName;
  
  Double amount;
  
  @ManyToOne(fetch = FetchType.LAZY)
  Account account;
}
