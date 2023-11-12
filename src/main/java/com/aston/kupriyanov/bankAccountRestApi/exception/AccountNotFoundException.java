package com.aston.kupriyanov.bankAccountRestApi.exception;

public class AccountNotFoundException extends CommonNotFoundException{
    public AccountNotFoundException(String message) {
        super(message);
    }
}
