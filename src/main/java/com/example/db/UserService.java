package com.example.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
              
        MyUser user = findUserByMail(username);
        return user;
    }

public MyUser findUserByMail(String username) {
        
        List<MyUser> users = new UserRepository().findAll();
        
        for (MyUser tmpUser : users) {
            if (tmpUser.getUsername().equals(username)) {
                return tmpUser;
            }
        }
        throw new UsernameNotFoundException("User with mail:" + username + "doesnt exists!");
    }
}