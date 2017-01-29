package com.example.db;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;


/**
 * @author Windows VM
 *
 */
@Repository
public class UserRepository implements CommandLineRunner {

    /**
     * Logger . A log instance that prints out what is going on in the method calls.
     */
    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

   
    private void insertData() {
        log.info("@@ Creating tables");
        
        jdbcTemplate.execute("DROP TABLE IF EXISTS users");
        jdbcTemplate.execute("CREATE TABLE users(id serial PRIMARY KEY, username VARCHAR(100) UNIQUE NOT NULL, password VARCHAR(100) NOT NULL, email VARCHAR(100) UNIQUE NOT NULL)");
        jdbcTemplate.execute("insert into users(username, password, email) values('admin','pass','admin@crayons.de')");
        jdbcTemplate.execute("insert into users(username, password, email) values('user','pass','user@crayons.de')");
        log.info("@@ > Done.");
    }
    

    /**
     * @return returns of the table <users> in the DB
     * This method will use the jdbcTemplate instance and the query method
     * (that accepts a SQL syntax) to get all the data; it will return a collection of Journal
     * instances.
     * 
     */
    public List<CrayonsUser> findAll(){
        log.info("@@ Querying");
        List<CrayonsUser> entries = new ArrayList<>();
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        jdbcTemplate.query("SELECT * FROM users",new Object[]{},(rs,row) -> new CrayonsUser(rs.getLong("id"),
        rs.getString("username"), rs.getString("password"), rs.getString("email"),authorities)).forEach(entry -> entries.add(entry));
        log.info("> Done.");
        return entries;
        
    }
    


    @Override
    public void run(String... arg0) throws Exception {
    
        
        log.info("@@ Inserting Data....");
        insertData();
        log.info("@@ findAll() call...");
        findAll().forEach(entry -> log.info(entry.toString()));
        
    }
    


}