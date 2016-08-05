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
public class DAOToFileImpl implements DAO {

    String fileTax, fileInv, file;
    HashMap<String, ArrayList<Order>> orderHistory;
    ArrayList<Product> inventory;
    ArrayList<Taxes> taxTable;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
    private String today = "Orders_" + LocalDate.now().format(formatter) + ".txt"; //create a get and implement it in the controller

    public DAOToFileImpl(String fileTax, String fileInv) {
        this.fileTax = fileTax;
        this.fileInv = fileInv;
        inventory = readProducts(fileInv);
        taxTable = readTaxes(fileTax);
        orderHistory = new HashMap<>();
        orderHistory.put(today, readOrders(today));
    }

    @Override
    public ArrayList<Order> displayOrders(String date) {
        if (orderHistory.keySet().contains(date)) {
            return orderHistory.get(date);
        } else {
            orderHistory.put(date, readOrders(date));
        }
            return orderHistory.get(date);
    }

    @Override
    public String addOrder(Order x) {
        if (orderHistory.containsKey(getToday())) {
            orderHistory.get(getToday()).add(x);
        } else {
            orderHistory.put(today, readOrders(today));
        }
        if (orderHistory.get(today).isEmpty()) {
            orderHistory.get(getToday()).add(x);
        }
        return "Your order has been placed successfully.";
    }

    @Override
    public Order editOrder(String date, int index, Order x) {
        Order y = x.clone();
        if (orderHistory.keySet().contains(date)) {
            orderHistory.get(date).set(index, x);
        } else {
            orderHistory.put(date, readOrders(date));
        }
        if (orderHistory.get(date).isEmpty()) {
            return null;
        } else {
            orderHistory.get(date).set(index, x);
        }
        return y;
    }

    @Override
    public Order removeOrder(String orderDate, int index) {
        if (orderHistory.keySet().contains(orderDate)) {
            Order x = orderHistory.get(orderDate).get(index).clone();
            orderHistory.get(orderDate).set(index, null);
            return x;
        } else {
            orderHistory.get(orderDate).remove(index);
        }
        if (orderHistory.get(orderDate).isEmpty()) {
            return null;
        } else {
            Order x = orderHistory.get(orderDate).get(index).clone();
            orderHistory.get(orderDate).remove(index);
            return x;
        }
    }

    public HashMap<String, ArrayList<Order>> returnMap() {
        return orderHistory;
    }

    @Override
    public void save() {
        writeOrders(orderHistory);
        writeTaxes(fileTax, taxTable);
        writeProducts(fileInv, inventory);
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
        ArrayList<Order> ord = readOrders(date);
        if (ord != null) {
            return ord.get(ord.size()-1).getOrderNumber() + 1;
        } else {
            orderHistory.put(date, new ArrayList<>());
            return 1;
        }
    }

    @Override
    public Order returnOrder(String orderDate, int index) {
        ArrayList<Order> ord = readOrders(orderDate);

        if (ord != null) {
            return ord.get(index);
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
        ArrayList<Order> ordList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(file)));
            String temp;
            String[] tempStrings;

            while (sc.hasNextLine()) {
                temp = sc.nextLine();
                tempStrings = temp.split(",");
                ordList.add(new Order(Integer.parseInt(tempStrings[0]), tempStrings[1],
                        Double.parseDouble(tempStrings[2]), tempStrings[3], Double.parseDouble(tempStrings[4]),
                        tempStrings[5], Double.parseDouble(tempStrings[6]), Double.parseDouble(tempStrings[7]),
                        Double.parseDouble(tempStrings[8]), Double.parseDouble(tempStrings[9]),
                        Double.parseDouble(tempStrings[10]), Double.parseDouble(tempStrings[11])));
            }
        } catch (FileNotFoundException e) {
            return null;
        }
        return ordList;
    }

    @Override
    public void writeOrders(HashMap<String, ArrayList<Order>> orderHistory) {
        PrintWriter out;
        ArrayList<Order> ord;
        try {
            for (String key : orderHistory.keySet()) {
                out = new PrintWriter(new FileWriter(key));
                ord = orderHistory.get(key);
                for (Order v : ord) {
                    out.println(v.getOrderNumber() + ","
                            + v.getCustomerName() + "," + v.getArea() + ","
                            + v.getState() + "," + v.getTaxRate() + ","
                            + v.getProductType() + "," + v.getCostPerSqFt() + ","
                            + v.getLaborCostPerSqFt() + "," + v.getMaterialCost()
                            + "," + v.getLaborCost() + "," + v.getTax() + ","
                            + v.getTotalCost());
                }
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            System.out.println("Write failed.");
        }
    }

    @Override
    public ArrayList<Product> readProducts(String fileInv
    ) {
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
    public void writeProducts(String fileInv, ArrayList<Product> x
    ) {
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
    public ArrayList<Taxes> readTaxes(String fileTax
    ) {
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
