package com.BankingSystem.main;

import com.BankingSystem.model.Account;
import com.BankingSystem.model.User;
import com.BankingSystem.service.AccountService;
import com.BankingSystem.service.TransactionService;
import com.BankingSystem.service.UserService;
import com.BankingSystem.multithreading.TransactionTask;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserService userService = new UserService();
        AccountService accountService = new AccountService();
        TransactionService transactionService =
                new TransactionService(accountService);

        while (true) {
            System.out.println("\n===== Banking System =====");
            System.out.println("1. Create User");
            System.out.println("2. Create Account");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Transfer Money");
            System.out.println("6. Show Account Balance");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {

                    case 1: // Create User
                        System.out.print("Enter User Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();

                        User user = userService.createUser(name, email);
                        System.out.println("User created. User ID: " + user.getUserId());
                        break;

                    case 2: // Create Account
                        System.out.print("Enter User ID: ");
                        String userId = sc.nextLine();

                        System.out.print("Enter Account Type (SAVINGS/CURRENT): ");
                        String accountType = sc.nextLine();

                        Account account =
                                accountService.createAccount(userId, accountType);

                        System.out.println("Account created. Account Number: "
                                + account.getAccountNumber());
                        break;

                    case 3: // Deposit
                        System.out.print("Enter Account Number: ");
                        String depAcc = sc.nextLine();

                        System.out.print("Enter Amount to Deposit: ");
                        double depAmount = sc.nextDouble();

                        accountService.deposit(depAcc, depAmount);
                        System.out.println("Deposit successful.");
                        break;

                    case 4: // Withdraw
                        System.out.print("Enter Account Number: ");
                        String witAcc = sc.nextLine();

                        System.out.print("Enter Amount to Withdraw: ");
                        double witAmount = sc.nextDouble();

                        accountService.withdraw(witAcc, witAmount);
                        System.out.println("Withdrawal successful.");
                        break;

                    case 5: // Transfer (MULTITHREADED)
                        System.out.print("Enter FROM Account Number: ");
                        String fromAcc = sc.nextLine();

                        System.out.print("Enter TO Account Number: ");
                        String toAcc = sc.nextLine();

                        System.out.print("Enter Amount: ");
                        double amount = sc.nextDouble();

                        TransactionTask task = new TransactionTask(
                                transactionService,
                                fromAcc,
                                toAcc,
                                amount
                        );

                        Thread t = new Thread(task);
                        t.start();
                        t.join();

                        System.out.println("Transfer completed.");
                        break;

                    case 6: // Show Balance
                        System.out.print("Enter Account Number: ");
                        String balAcc = sc.nextLine();

                        double balance = accountService.getBalance(balAcc);
                        System.out.println("Balance: " + balance);
                        break;

                    case 7: // Exit
                        System.out.println("Thank you for using Banking System.");
                        sc.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice.");

                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
