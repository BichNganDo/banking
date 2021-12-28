package com.example.Banking.model;

public class BankAccountInfo {
    private int id;
    private String fullName;
    private double balance;

    public BankAccountInfo() {
    }

    public BankAccountInfo(int id, String fullName, double balance) {
        this.id = id;
        this.fullName = fullName;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
