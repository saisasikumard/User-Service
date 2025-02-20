package com.obito.UserService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import com.obito.UserService.entity.UserEntity;
import com.obito.UserService.repository.UserRepo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Log log = LogFactory.getLog(UserService.class);
    @Autowired
    UserRepo userRepo;
    @Autowired
    JwtService jwtService;
    public UserEntity addUser(UserEntity user)
    {
        return userRepo.save(user);
    }
    public UserEntity getUser(int userId)
    {
        return userRepo.findById(userId).orElseThrow(()->new RuntimeException("Invalid User..."));
    }
    public UserEntity updateUserAmount(int userId,double amount) throws JsonProcessingException {
        UserEntity user=getUser(userId);
        user.setAmount(user.getAmount()-amount);
        log.info(new ObjectMapper().writeValueAsString(user));
        return userRepo.save(user);
    }
    public String generateToken(String username){
        return jwtService.generateToken(username);
    }
    public void validateToken(String token){
        jwtService.validateToken(token);
    }
}
