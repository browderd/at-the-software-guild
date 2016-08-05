/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmasteryproject.dao;

import com.mycompany.flooringmasteryproject.dto.Order;
import com.mycompany.flooringmasteryproject.dto.Product;
import com.mycompany.flooringmasteryproject.dto.Taxes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class DAOInMemoryImpl implements DAO {

    HashMap<String, ArrayList<Order>> orderHistory;
    ArrayList<Product> inventory;
    ArrayList<Taxes> taxTable;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
    private String today = "Orders_" + LocalDate.now().format(formatter) + ".txt"; //create a get and implement it in the controller
    String fileTax, fileInv, file;

    public DAOInMemoryImpl(String fileTax, String fileInv) {
        orderHistory = orderHistory = popOrders();
        inventory = readProducts(fileInv);
        taxTable = readTaxes(fileTax);
    }

    @Override
    public ArrayList<Order> displayOrders(String date) {
        if (orderHistory.keySet().contains(date)) {
            return orderHistory.get(date);
        } else {
            return null;
        }
    }

    @Override
    public String addOrder(Order x) {
        if (orderHistory.containsKey(getToday())) {
            orderHistory.get(getToday()).add(x);
        } else {
            orderHistory.put(getToday(), new ArrayList<>());
            orderHistory.get(getToday()).add(x);
        }
        return "Your order has been placed successfully.";
    }

    @Override
    public Order editOrder(String date, int index, Order x) {
        Order y = x.clone();
        orderHistory.get(date).set(index, x);
        return y;
    }

    @Override
    public Order removeOrder(String orderDate, int index) {
        Order x = orderHistory.get(orderDate).get(index).clone();
        orderHistory.get(orderDate).set(index, null);
        return x;
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Taxes> passTaxTable() {
        return new ArrayList<>(taxTable);
    }

    @Override
    public ArrayList<Product> passInv() {
        return new ArrayList<>(inventory);
    }

    @Override
    public int generateID(String date) {
        if (orderHistory.containsKey(date)) {
            return orderHistory.get(date).size() + 1;
        } else {
            orderHistory.put(date, new ArrayList<>());
            return 1;
        }
    }

    public HashMap<String, ArrayList<Order>> returnMap() {
        return orderHistory;
    }

    @Override
    public Order returnOrder(String orderDate, int index) {
        if (orderHistory.containsKey(orderDate)
                && orderHistory.get(orderDate).get(index) != null) {
            return orderHistory.get(orderDate).get(index);
        } else {
            return null;
        }
    }

    public HashMap<String, ArrayList<Order>> popOrders() {
        HashMap<String, ArrayList<Order>> x = new HashMap<>();
        String date = getToday();
        x.put(date, new ArrayList<>());
        x.get(date).add(new Order(1, "Wise", 100.00, new Taxes("OH", 6.25), new Product("Wood", 5.15, 4.75)));
        return x;
    }

    public ArrayList<Product> popProducts() {
        ArrayList<Product> x = new ArrayList<>();
        x.add(new Product("Carpet", 2.25, 2.10));
        x.add(new Product("Laminate", 1.75, 2.10));
        x.add(new Product("Tile", 3.50, 4.15));
        x.add(new Product("Wood", 5.15, 4.75));
        return x;
    }

    public ArrayList<Taxes> popTaxes() {
        ArrayList<Taxes> x = new ArrayList<>();
        x.add(new Taxes("OH", 6.25));
        x.add(new Taxes("PA", 6.75));
        x.add(new Taxes("MI", 5.75));
        x.add(new Taxes("IN", 6.00));
        return x;
    }

    @Override
    public ArrayList<Order> readOrders(String file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeOrders(HashMap<String, ArrayList<Order>> x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Product> readProducts(String fileInv) {
        ArrayList<Product> InvList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(fileInv)));
            String temp;
            String[] tempStrings;

            while (sc.hasNextLine()) {
                temp = sc.nextLine();
                tempStrings = temp.split(",");
                InvList.add(new Product(tempStrings[0], Double.parseDouble(tempStrings[1]), Double.parseDouble(tempStrings[2])));
            }
        } catch (FileNotFoundException e) {
            //No relevant action to be performed
        }

        return InvList;
    }

    @Override
    public void writeProducts(String fileInv, ArrayList<Product> x) {

        try {
            PrintWriter out = new PrintWriter(new FileWriter(fileInv));
            x.stream().forEach(p -> {
                out.println(p.getProductType()
                        + "," + p.getCostPerSqFt()
                        + "," + p.getLaborCostPerSqFt());
            });
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println("Write failed.");
        }

    }

    @Override
    public ArrayList<Taxes> readTaxes(String fileTax) {
        ArrayList<Taxes> TaxList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(fileTax)));
            String temp;
            String[] tempStrings;

            while (sc.hasNextLine()) {
                temp = sc.nextLine();
                tempStrings = temp.split(",");
                TaxList.add(new Taxes(tempStrings[0], Double.parseDouble(tempStrings[1])));
            }
        } catch (FileNotFoundException e) {
            //No relevant action to be performed
        }

        return TaxList;
    }

    @Override
    public void writeTaxes(String fileTax, ArrayList<Taxes> x) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(fileTax));
            x.stream().forEach(p -> {
                out.println(p.getState()
                        + "," + p.getTaxRate());
            });
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println("Write failed.");
        }
    }

    /**
     * @return the today
     */
    @Override
    public String getToday() {
        return today;
    }
}
