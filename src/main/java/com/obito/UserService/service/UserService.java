package com.obito.UserService.service;

import com.obito.UserService.entity.UserEntity;
import com.obito.UserService.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public UserEntity addUser(UserEntity user)
    {
        return userRepo.save(user);
    }
    public UserEntity getUser(int userId)
    {
        return userRepo.findById(userId).orElseThrow(()->new RuntimeException("Invalid User..."));
    }
    public UserEntity updateUserAmount(int userId,double amount){
        UserEntity user=getUser(userId);
        user.setAmount(user.getAmount()-amount);
        return userRepo.save(user);
    }
}
