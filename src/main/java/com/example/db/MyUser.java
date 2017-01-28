package com.example.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUser extends User{

    

    public MyUser(Long id,String username, String password,String email, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.email = email;
    }
    private long id;
    private String email;
   
    private List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, username='%s', password='%s', email='%s']",
                id, getUsername(), getPassword(), email);
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public List<GrantedAuthority> getAuth() {
        return auth;
    }
    public void setAuth(List<GrantedAuthority> auth) {
        this.auth = auth;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

   
   
}
