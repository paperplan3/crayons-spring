//package com.example;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import org.springframework.jdbc.core.RowMapper;
//
//
//class UserRowMapper implements RowMapper<MyUser> {
//    @Override
//    public MyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
//        MyUser user = new MyUser(rs.getInt("id"),rs.getString("username"),rs.getString("password"));
//        
//        return user;
//    }
//}