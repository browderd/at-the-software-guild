/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachinewebapp.dao;


import com.mycompany.vendingmachinewebapp.dto.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserDaoDbImpl implements UserDao {
    // #1 - We need to update both the users and authorities tables

    private static final String SQL_INSERT_USER
            = "insert into users (username, password, enabled) values (?, ?, 1)";
    private static final String SQL_INSERT_AUTHORITY
            = "insert into authorities (username, authority) values (?, ?)";
    private static final String SQL_DELETE_USER
            = "delete from users where username = ?";
    private static final String SQL_DELETE_AUTHORITIES
            = "delete from authorities where username = ?";
    private static final String SQL_GET_USERS
            = "select username, password from users";
    private static final String SQL_GET_USER_AUTHORITIES
            = "select authority from authorities where username = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addUser(User newUser) {
        // #2 - First insert user data into the users table and then insert data into
        // the authorities table (failing to do so will result in foreign key
        // constraint errors)
        jdbcTemplate.update(SQL_INSERT_USER, newUser.getUsername(), newUser.getPassword());

        // now insert user's roles
        List<String> authorities = newUser.getAuthorities();
        for (String authority : authorities) {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY, newUser.getUsername(), authority);
        }


    }

    @Override
    public void deleteUser(String username) {
        // #3 - First delete all authorities for this user
        jdbcTemplate.update(SQL_DELETE_AUTHORITIES, username);
        // #3 - Second delete the user - failing to do so will result in foreign
        // key constraint errors
        jdbcTemplate.update(SQL_DELETE_USER, username);
    }
    
    @Override
    public List<User> getAllUsers() {
        List<User> users = jdbcTemplate.query(SQL_GET_USERS, new UserMapper());
        
        for(User u : users) {
            u.setAuthorities(jdbcTemplate.query(SQL_GET_USER_AUTHORITIES, new StringMapper(), u.getUsername()));   
        }
        
        return users;
    }
    
    private static final class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }
    
    private static final class StringMapper implements RowMapper<String> {
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getString("authority");
        }
    }
}
