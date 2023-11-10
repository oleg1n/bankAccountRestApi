package com.aston.kupriyanov.bankAccountRestApi.exception;

public class BeneficiaryNotFoundException extends RuntimeException{
    public BeneficiaryNotFoundException(String message) {
        super(message);
    }
}
