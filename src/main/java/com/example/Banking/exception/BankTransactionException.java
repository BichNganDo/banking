package com.example.Banking.exception;

public class BankTransactionException extends Exception {
    private static final int serialVersionUID = 1;

    public BankTransactionException(String message) {
        super(message);
    }
}
