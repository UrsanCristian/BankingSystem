package com.ursancristian.bankingsystem.exception;

public class BankAccountNotFoundException extends RuntimeException{
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
