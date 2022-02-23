package org.project.BankAccountKata.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
