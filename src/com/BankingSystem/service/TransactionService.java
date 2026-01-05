package com.BankingSystem.service;

import com.BankingSystem.exception.InvalidAccountException;
import com.BankingSystem.model.Account;
import com.BankingSystem.model.Transaction;
import com.BankingSystem.util.FileUtil;
import com.BankingSystem.util.IdGenerator;
import com.BankingSystem.util.LoggerUtil;

public class TransactionService {

    private final AccountService accountService;

    // constructor........

    public TransactionService(AccountService accountService) {
        this.accountService = accountService;
    }

    // transfer money between two accounts....

    public Transaction transfer(String fromAccount, String toAccount, double amount) {

        if (fromAccount == null || toAccount == null) {
            throw new InvalidAccountException("Account number cannot be null");
        }
        if (fromAccount.equals(toAccount)) {
            throw new InvalidAccountException("Source and destination cannot be same");
        }
        if (amount <= 0) {
            throw new InvalidAccountException("Amount must be positive");
        }

        Account source = accountService.getAccount(fromAccount);
        Account destination = accountService.getAccount(toAccount);

        // lock ordering to prevent deadlock......

        Account firstLock = fromAccount.compareTo(toAccount) < 0 ? source : destination;
        Account secondLock = fromAccount.compareTo(toAccount) < 0 ? destination : source;

        firstLock.getLock().lock();
        secondLock.getLock().lock();

        try {
            accountService.withdraw(fromAccount, amount);
            accountService.deposit(toAccount, amount);

            String transactionId = IdGenerator.generateTransactionId();
            Transaction transaction =
                    new Transaction(transactionId, fromAccount, toAccount, amount, "TRANSFER");

            FileUtil.saveTransaction(transaction);
            LoggerUtil.log("Transfer successful: " + transactionId);

            return transaction;

        } finally {
            secondLock.getLock().unlock();
            firstLock.getLock().unlock();
        }
    }
}
