package com.BankingSystem.model;


import java.util.ArrayList;
import java.util.List;

public class User {

    //declaring the fields.....

    private String userId;
    private String name;
    private String email;
    private List<Account> accounts;

    //the constructor.......

    public User(String userId,String name,String email){
        this.userId=userId;
        this.name=name;
        this.email=email;
        this.accounts= new ArrayList<>();
    }

    //the private fields to be used by using a getter.....

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    //add account to user......

    public void addAccount(Account account){
        this.accounts.add(account);
    }

    @Override
    public String toString() {
        return "User { "+
                " UserId = " + userId +'\'' +
                ", name = '" + name + '\'' +
                ", email = '" + email + '\'' +
                ", totalAccounts = '" + accounts.size() + '}';
    }
}
