package com.obito.UserService.controller;

import com.obito.UserService.entity.UserEntity;
import com.obito.UserService.entity.UserEntity;
import com.obito.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userController
{
    @Autowired
    UserService userService;
    @PostMapping("/add")
    public UserEntity add(@RequestBody UserEntity user){
        return userService.addUser(user);
    }
    @GetMapping("/get")
    public UserEntity getUser(@RequestParam int userId){
        return userService.getUser(userId);
    }
}
