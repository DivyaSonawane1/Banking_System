package com.BankingSystem.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    //the fields....

    private static final AtomicInteger userCounter = new AtomicInteger(1000);
    private static final AtomicInteger accountCounter = new AtomicInteger(5000);
    private static final AtomicInteger transactionCounter = new AtomicInteger(9000);

    //generating user id....

    public static String generateUserId(){
        return "U" + userCounter.incrementAndGet();
    }

    //generating Account Number.....

    public static String generateAccountNumber(){
        return "A" + accountCounter.incrementAndGet();
    }

    //generating Transaction Id.....

    public static String generateTransactionId(){
        return "A" + transactionCounter.incrementAndGet();
    }
}
