package com.aston.kupriyanov.bankAccountRestApi.exception;

public class PincodeIsNotValidException extends RuntimeException{
    public PincodeIsNotValidException(String message) {
        super(message);
    }
}
