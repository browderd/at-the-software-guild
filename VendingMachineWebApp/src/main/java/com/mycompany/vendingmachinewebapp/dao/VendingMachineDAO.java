/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachinewebapp.dao;


import com.mycompany.vendingmachinewebapp.dto.Change;
import com.mycompany.vendingmachinewebapp.dto.Item;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface VendingMachineDAO {
    
     
    public Item addItem (Item x);
    public Item getItemByID (int itemID);
    public List<Item> returnAll();
    public void updateItem (Item x);
    public Change purchase(int item);
    public double getBalance();
    public void setBalance(double balance);
}
