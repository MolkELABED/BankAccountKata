package org.project.BankAccountKata.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Account {

public Account(Long id, Double balance) {
		super();
		this.id = id;
		this.balance = balance;
	}

  @Id
  private Long id;
  
  private Double balance;

  @ManyToOne(fetch = FetchType.LAZY)
  private Customer customer;
  
  @OneToMany(fetch = FetchType.LAZY)
  private List<Transaction> transactions;
  
  public Long getId() {
	  return id;
  }

  public void setId(Long id) {
	  this.id = id;
  }

  public Double getBalance() {
	  return balance;
  }

  public void setBalance(Double balance) {
	  this.balance = balance;
  }

}
