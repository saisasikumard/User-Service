package com.obito.UserService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.obito.UserService.dto.AuthDto;
import com.obito.UserService.entity.UserEntity;
import com.obito.UserService.entity.UserEntity;
import com.obito.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userController
{
    @Autowired
    UserService userService;
    @Value("${server.port}")
    private int port;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public  UserEntity add(@RequestBody UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.addUser(user);
    }
    @GetMapping("/get")
    public UserEntity getUser(@RequestParam("id") int userId){
        System.out.println("User Service is running onn Port :"+port);
        return userService.getUser(userId);
    }
    @PutMapping("/update/{id}/{amount}")
    public UserEntity updateUser(@PathVariable("id") int userId ,@PathVariable("amount") double amount) throws JsonProcessingException {
        return userService.updateUserAmount(userId,amount);
    }
    @PostMapping("/gettoken")
    public String getToken(@RequestBody AuthDto authDto){
        Authentication authentication=
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getName(),authDto.getPassword()));
        //but authenticationManager is unnablle to fetch from db ,it needs UserDetailService.

        if(authentication.isAuthenticated()) {
            return userService.generateToken(authDto.getName());
        }
        else{
            throw new RuntimeException("Invalid User....");
        }
    }

    @GetMapping("/validatetoken")
    public String validateToken(@RequestParam("token") String token)
    {
        userService.validateToken(token);
        return "Token Validated";
    }
}
