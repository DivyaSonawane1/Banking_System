package com.BankingSystem.service;

import com.BankingSystem.model.User;
import com.BankingSystem.util.IdGenerator;
import com.BankingSystem.util.LoggerUtil;

import java.util.HashMap;
import java.util.Map;

public class UserService{

    //in-memory storage for users........can be replaced by db in the future....

    private final Map<String, User> userStore = new HashMap<>();

    //to create new user....
    //function....

    public User createUser(String name, String email){
        if(name == null || name.trim().length() < 3){
            LoggerUtil.log("Failed to create user: Invalid name");
            throw new IllegalArgumentException("Invalid User Name");
        }

        String userId = IdGenerator.generateUserId();
        User user = new User(userId, name, email);

        userStore.put(userId, user);

        //log these events....
        LoggerUtil.log("User Created successfully with ID: " + userId);

        return user;
    }

    //get user id.....
    //function.....

    public User getUser(String userId){
        User user = userStore.get(userId);

        if (user == null){
            LoggerUtil.log("User lookup failed. User not found: " + userId);
            throw  new IllegalArgumentException("User Not found: " + userId);
        }

        return user;
    }
}
