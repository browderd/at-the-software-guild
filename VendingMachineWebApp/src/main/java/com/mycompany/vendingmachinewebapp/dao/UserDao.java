/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachinewebapp.dao;


import com.mycompany.vendingmachinewebapp.dto.User;
import java.util.List;

/**
 *
 * @author mgaffney
 */
public interface UserDao {

    public void addUser(User newUser);

    public void deleteUser(String username);
    
    public List<User> getAllUsers();
}
