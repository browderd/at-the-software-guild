/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmasteryproject.dao;

import com.mycompany.flooringmasteryproject.dto.Order;
import com.mycompany.flooringmasteryproject.dto.Product;
import com.mycompany.flooringmasteryproject.dto.Taxes;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author apprentice
 */
public interface DAO {
    
    public ArrayList<Order> displayOrders (String date);
    public String addOrder (Order x);
    public Order editOrder (String date, int index, Order x);
    public Order removeOrder (String orderDate, int index);
    public void save();
    public ArrayList<Taxes> passTaxTable ();
    public ArrayList<Product> passInv ();
    public int generateID (String date);
    public Order returnOrder(String orderDate, int index);
    public ArrayList<Order> readOrders(String file);
    public void writeOrders (HashMap<String, ArrayList<Order>> x);
    public ArrayList<Product> readProducts (String fileInv);
    public void writeProducts(String fileInv, ArrayList<Product> x);
    public ArrayList<Taxes> readTaxes(String fileTax);
    public void writeTaxes(String fileTx, ArrayList<Taxes> x);
    public String getToday();

    
}
