package com.example;

import java.sql.Date; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
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
        
        jdbcTemplate.execute("DROP TABLE IF EXISTS customers");
        jdbcTemplate.execute("CREATE TABLE customers(id serial PRIMARY KEY, username VARCHAR(100) UNIQUE NOT NULL, password VARCHAR(100) NOT NULL)");
        jdbcTemplate.execute("insert into customers(username, password) values('Siva','foooo')");
        jdbcTemplate.execute("insert into customers(username, password) values('Lula','boooo')");
        log.info("@@ > Done.");
    }
    

    /**
     * @return returns of the table <customers> in the DB
     * This method will use the jdbcTemplate instance and the query method
     * (that accepts a SQL syntax) to get all the data; it will return a collection of Journal
     * instances.
     * 
     */
    public List<MyUser> findAll(){
        log.info("@@ Querying");
        List<MyUser> entries = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM customers",new Object[]{},(rs,row) -> new MyUser(rs.getLong("id"),
        rs.getString("username"), rs.getString("password"))).forEach(entry -> entries.add(entry));
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