package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUser{

    public MyUser(Long id,String username, String password) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    private long id;
    private String username;
    private String password;
   
    private List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, username='%s', password='%s']",
                id, username, password);
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public List<GrantedAuthority> getAuth() {
        return auth;
    }
    public void setAuth(List<GrantedAuthority> auth) {
        this.auth = auth;
    }

   
   
}
