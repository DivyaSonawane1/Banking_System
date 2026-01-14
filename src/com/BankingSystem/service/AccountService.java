package com.BankingSystem.service;

import com.BankingSystem.model.Account;
import com.BankingSystem.exception.*;
import com.BankingSystem.util.IdGenerator;
import com.BankingSystem.util.LoggerUtil;

import java.util.HashMap;
import java.util.Map;

public class AccountService {

    //in-memory storage of accounts.....can be replaced by db in the future....

    private final Map<String,Account> accountStore = new HashMap<>();

    //create a new account for a user.....
    //function....

    public Account createAccount(String userId,String accountType){
        if(userId == null|| userId.trim().isEmpty()){
            LoggerUtil.log("Account creation failed: invalid userId");
            throw new InvalidAccountException("Invalid User Id");
        }

        if(accountType == null || accountType.trim().isEmpty()){
            LoggerUtil.log("Account creation failed: invalid account type");
            throw new InvalidAccountException("Invalid account type");
        }

        String accountNumber = IdGenerator.generateAccountNumber();
        Account account = new Account(accountNumber, userId, accountType);

        accountStore.put(accountNumber, account);

        LoggerUtil.log("Account created successfully: "+ accountNumber);

        return account;
    }

    //get account by account number....
    //function.....

    public Account getAccount(String accountNumber){
        Account account = accountStore.get(accountNumber);

        if (account == null){
            LoggerUtil.log("Account not found: " + accountNumber);
            throw new AccountNotFoundException("Account not found: " + accountNumber);
        }
        return account;
    }

    //deposit money......
    public void deposit(String accountNumber, double amount){

        if(amount <= 0){
            throw new InvalidAccountException("Deposit amount must be positive");
        }

        Account account = getAccount(accountNumber);

        account.getLock().lock();
        try {
            account.credit(amount);
            LoggerUtil.log("Deposited " + amount + "into account " + accountNumber);
        }finally {
            account.getLock().unlock();
        }
    }

    //withdraw money.....
    public void withdraw(String accountNumber, double amount){

        if(amount <= 0){
            throw new InvalidAccountException("Withdrawal amount must be positive");
        }

        Account account = getAccount(accountNumber);

        account.getLock().lock();
        try {
            if (account.getBalance() < amount){
                LoggerUtil.log("Insufficient balance in account " + accountNumber);
                throw new InsufficientBalanceException("Insufficient balance");
            }
            account.debit(amount);
            LoggerUtil.log("Withdrawn " + amount + "from account " + accountNumber);
        }finally {
            account.getLock().unlock();
        }
    }

    public double getBalance(String accountNumber) {
        Account account = accountStore.get(accountNumber);

        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        return account.getBalance();
    }
}
