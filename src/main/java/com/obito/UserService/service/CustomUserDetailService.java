package com.obito.UserService.service;

import com.obito.UserService.entity.UserEntity;
import com.obito.UserService.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity= userRepo.findByName(username);
        if(userEntity==null){
            throw new RuntimeException("User not found with username:"+username);
        }
        return new CustomUserDetails(username, userEntity.getPassword());

    }
}
