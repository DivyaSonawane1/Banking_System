package com.BankingSystem.model;

import java.util.concurrent.locks.ReentrantLock;

public class Account {

    //the fields.....

    private String accountNumber;
    private String userId;
    private  String accountType;
    private  double balance;
    private final ReentrantLock lock;

    //the constructor.....

    public Account(String accountNumber, String userId,String accountType){
        this.accountNumber=accountNumber;
        this.userId=userId;
        this.accountType=accountType;
        this.balance=0.0;
        this.lock=new ReentrantLock();
    }

    //Getters.....


    public String getAccountNumber() {
        return accountNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    //balance update methods......


    public void credit(double amount) {
        this.balance += amount;
    }

    public void debit(double amount){
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", userId='" + userId + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                '}';
    }
}
