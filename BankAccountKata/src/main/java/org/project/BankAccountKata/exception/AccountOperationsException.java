package org.project.BankAccountKata.exception;

public class AccountOperationsException extends RuntimeException {
	
	public static final String DEPOSIT_LESS_THAN_MINIMUM_DEPOSIT = "Deposit is less than 0,01.";
	public static final String CUSTOMER_NOT_FOUND = "Customer not found.";
	public static final String ACCOUNT_NOT_FOUND = "Account not found.";
	public static final String INSUFFICIENT_CREDIT = "Insufficient credit to withdraw money.";
	public static final String NO_OPERATION_FOUND = "No operation found.";
	
    public AccountOperationsException(String message) { 
        super(message);
    }
}
