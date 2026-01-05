package com.BankingSystem.main;

import com.BankingSystem.model.Account;
import com.BankingSystem.model.User;
import com.BankingSystem.service.*;
import com.BankingSystem.multithreading.TransactionTask;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //services......
        UserService userService = new UserService();
        AccountService accountService = new AccountService();
        TransactionService transactionService = new TransactionService(accountService);

        //creating users.....

        //user1.....
        System.out.print("Enter NAME for User 1 : ");
        String name1 = scanner.nextLine();
        System.out.print("Enter EMAIL for User 1 : ");
        String email1 = scanner.nextLine();

        User user1 = userService.createUser(name1, email1);

        //user2.....
        System.out.print("Enter NAME for User 2 : ");
        String name2 = scanner.nextLine();
        System.out.print("Enter EMAIL for User 2 : ");
        String email2 = scanner.nextLine();

        User user2 = userService.createUser(name2, email2);

        //creating accounts....
        System.out.print("Enter type of account for user 1(SAVINGS/CURRENT): ");
        String type1 = scanner.nextLine();

        System.out.print("Enter type of account for user2(SAVINGS/CURRENT): ");
        String type2 = scanner.nextLine();

        Account acc1 = accountService.createAccount(user1.getUserId(), type1);
        Account acc2 = accountService.createAccount(user2.getUserId(), type2);

        //the amount ......
        System.out.print("Enter Initial deposit for user : " + " \" " +name1 + "\" : " );
        double deposit1 = scanner.nextDouble();
        scanner.nextLine();

        accountService.deposit(acc1.getAccountNumber(), deposit1);

        System.out.print("Enter Initial deposit for user : " + " \" " +name2 + "\" : ");
        double deposit2 = scanner.nextDouble();
        scanner.nextLine();

        accountService.deposit(acc2.getAccountNumber(), deposit2);

        //showcase initial balances.....

        System.out.println("Initial Balances:");
        System.out.println(acc1.getAccountNumber() + " belongs to user: " + name1 + "\nCurrent balance: " + acc1.getBalance() + " Rupees");
        System.out.println(acc2.getAccountNumber() + " belongs to user: " + name2 + "\nCurrent balance: " + acc2.getBalance() + " Rupees");

        //inputs for transferring......

        System.out.print("\nEnter transfer amount from user: " + "\"" + name1 + "\"" + "to user: " + "\"" + name2 + "\" =" );
        double transferAmount = scanner.nextDouble();

        //concurrent transactions.....
        Thread t1 = new Thread(
                new TransactionTask(
                        transactionService,
                        acc1.getAccountNumber(),
                        acc2.getAccountNumber(),
                        transferAmount),
                "Transfer-Thread");

        //starting the threads.....
        t1.start();

        //waiting for completion.......
        try{
            t1.join();
        }catch (InterruptedException e){
            System.err.println("Main Thread Interrupted");
        }

        System.out.print("\nFinal Balances:");
        System.out.println(acc1.getAccountNumber() + " belongs to user: " + name1 + " has " + acc1.getBalance() + " Rupees");
        System.out.println(acc2.getAccountNumber() + " belongs to user: " + name2 + " has " + acc2.getBalance() + " Rupees");

        scanner.close();

    }
}
