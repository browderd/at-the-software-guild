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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class DAOToFileImplTest {
    DAO instance;
    HashMap<String, ArrayList<Order>> orders;
    ArrayList<Order> ordTest;
    ArrayList<Order> ordTest2;
    ArrayList<Product> invTest;
    ArrayList<Taxes> taxTest;
    DateTimeFormatter formatter;
    String today;
    String fileTax = "TestTax.txt";
    String fileInv = "TestProducts.txt";
    
    public DAOToFileImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {

       
        instance = new DAOToFileImpl("TestTax.txt", "TestInv.txt");
        orders = new HashMap<>();
        ordTest = new ArrayList<>();
        ordTest2 = new ArrayList<>();
        invTest = new ArrayList<Product>();
        taxTest = new ArrayList<Taxes>();
        formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        today = "Orders_" + LocalDate.now().format(formatter) + ".txt";
                   
        orders.put(today, new ArrayList<>());
        orders.get(today).add(new Order(1, "Wise",100.00, new Taxes("OH", 6.25), new Product("Wood", 5.15, 4.75)));
        orders.get(today).add(new Order(1, "Dave",100.00, new Taxes("NY", 6.25), new Product("Steel", 5.15, 4.75)));
        
        taxTest.add(new Taxes("OH", 6.25));
        taxTest.add(new Taxes("PA", 6.75));
        taxTest.add(new Taxes("MI", 5.75));
        taxTest.add(new Taxes("IN", 6.00));
        instance.writeTaxes(fileTax, taxTest);
        
        invTest.add(new Product("Carpet", 2.25, 2.10));
        invTest.add(new Product("Laminate", 1.75, 2.10));
        invTest.add(new Product("Tile", 3.50, 4.15));
        invTest.add(new Product("Wood", 5.15, 4.75));
        instance.writeProducts(fileInv, invTest);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of displayOrders method, of class DAOToFileImpl.
     */
    @Test
    public void testDisplayOrders() { //null pointer
        System.out.println("displayOrders");
        int expResult =2;
        int result = instance.displayOrders(today).size();
        assertEquals(expResult, result);
        
        String date2 = "Orders_06062016.txt";
        ArrayList<Order> result2 = instance.displayOrders(date2);
        assertNull(result2);
    }

    /**
     * Test of addOrder method, of class DAOToFileImpl.
     */
    @Test
    public void testAddOrder() {//null pointer
        System.out.println("addOrder");
         ordTest = instance.displayOrders(today);
        Order x = new Order (2, "Bob Sagget", 204.4,new Taxes("NY", 6.25), 
                new Product("Steel", 5.15, 4.75));
        String msg = instance.addOrder(x);
        ordTest2 = instance.displayOrders(today);
        
        int expResult = 3;
        int result = ordTest.size(); 
        assertEquals(expResult, result);
        
        int expResult2 = 3;
        int result2 = ordTest2.size(); 
        assertEquals(expResult2, result2);
        
        int expResult3 = 1;
        int result3 = ordTest2.get(1).getOrderNumber(); 
        assertEquals(expResult3, result3);
    }

    /**
     * Test of editOrder method, of class DAOToFileImpl.
     */
    @Test
    public void testEditOrder() {
        System.out.println("editOrder");
        System.out.println("editOrder");
        String date = today;
        int index = 0;
        ordTest = instance.displayOrders(date);
        Order y = ordTest.get(0).clone();
        y.setOrderNumber(2);
        y.setCustomerName("Joe Bob");
        Order z = instance.editOrder(date, index, y);
        ordTest2 = instance.displayOrders(date);
        
        String expResult = y.getCustomerName();
        String result = z.getCustomerName();
        assertEquals(expResult, result);
        
        int expResult2 = 2;
        int result2 = ordTest2.get(0).getOrderNumber();
        assertEquals(expResult2, result2);
    }

    /**
     * Test of removeOrder method, of class DAOToFileImpl.
     */
    @Test
    public void testRemoveOrder() {
        System.out.println("removeOrder");
        String orderDate = today;
        int index = 0;
        ordTest = instance.displayOrders(today);
        Order a = ordTest.get(0);
        Order b = instance.removeOrder(orderDate, index);
        assertEquals(a.getCustomerName(), b.getCustomerName());
        
        ordTest2 = instance.displayOrders(today);
        assertNull(ordTest.get(0));
    }
    /**
     * Test of generateID method, of class DAOToFileImpl.
     */
    @Test
    public void testGenerateID() {
        System.out.println("generateID");
        String date = today;
        int expResult = 2;
        int result = instance.generateID(date);
        assertEquals(expResult, result);
    }

    /**
     * Test of returnOrder method, of class DAOToFileImpl.
     */
    @Test
    public void testReturnOrder() {
        System.out.println("returnOrder");
        System.out.println("returnOrder");
        String orderDate = today;
        int index = 0;
        ordTest = instance.displayOrders(today);
        
        assertEquals(1, ordTest.get(0).getOrderNumber());
        assertEquals("Wise", ordTest.get(0).getCustomerName());
    }

    /**
     * Test of readOrders method, of class DAOToFileImpl.
     */
    @Test
    public void testReadOrders() {
        System.out.println("readOrders");
        String day2 = "Orders_0606206.txt";
        try {
            PrintWriter out = new PrintWriter(new FileWriter(today));
            out.flush();
            out.close();

        } catch (IOException e) {
            //no action 
        }
 
        PrintWriter out;
        ArrayList<Order> ord = new ArrayList<>();
        try {
            for (String key : orders.keySet()) {
                out = new PrintWriter(new FileWriter(key));
                ord = orders.get(key);
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
        ordTest = new ArrayList<>();
        ordTest = instance. readOrders(today);
        
        assertEquals(orders.get(today).size(), ordTest.size());
        assertEquals(orders.get(today).get(0).getCustomerName(), ordTest.get(0).getCustomerName());
    }
    
    

    /**
     * Test of writeOrders method, of class DAOToFileImpl.
     */
    @Test
    public void testWriteOrders() {
        System.out.println("writeOrders");
        String day2 = "Orders_0606206.txt";
        try {
            PrintWriter out = new PrintWriter(new FileWriter(today));
            out.flush();
            out.close();

        } catch (IOException e) {
            //no action 
        }
        try {
            PrintWriter out = new PrintWriter(new FileWriter(day2));
            out.flush();
            out.close();

        } catch (IOException e) {
            //no action 
        }
        ordTest = new ArrayList<>();
        ordTest.add(new Order(1, "Wise", 100.00, new Taxes("OH", 6.25), new Product("Wood", 5.15, 4.75)));
        
        ordTest2 = new ArrayList<>();
        ordTest2.add(new Order(1, "Bob", 100.00, new Taxes("OH", 6.25), new Product("Steel", 5.15, 4.75)));
        orders = new HashMap<>();
        
        orders.put(today, ordTest);
        orders.put(day2, ordTest2);
        instance.writeOrders(orders);
       
        HashMap<String, ArrayList<Order>> orders2 = new HashMap<>();
        ArrayList<Order> ordList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(today)));
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
        }
        orders2.put(today, ordList);
        
        ArrayList<Order> ordList2 = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(day2)));
            String temp;
            String[] tempStrings;

            while (sc.hasNextLine()) {
                temp = sc.nextLine();
                tempStrings = temp.split(",");
                ordList2.add(new Order(Integer.parseInt(tempStrings[0]), tempStrings[1],
                        Double.parseDouble(tempStrings[2]), tempStrings[3], Double.parseDouble(tempStrings[4]),
                        tempStrings[5], Double.parseDouble(tempStrings[6]), Double.parseDouble(tempStrings[7]),
                        Double.parseDouble(tempStrings[8]), Double.parseDouble(tempStrings[9]),
                        Double.parseDouble(tempStrings[10]), Double.parseDouble(tempStrings[11])));
            }
        } catch (FileNotFoundException e) {
   
        }
        orders2.put(day2, ordList2);
        
        assertEquals(orders2.get(today).size(), ordTest.size());
        assertEquals(orders2.get(day2).get(0).getCustomerName(), ordTest2.get(0).getCustomerName());
    }

    /**
     * Test of readProducts method, of class DAOToFileImpl.
     */
    @Test
    public void testReadProducts() {
        System.out.println("readProducts");
        try {
            PrintWriter out = new PrintWriter(new FileWriter(fileInv));
            out.flush();
            out.close();

        } catch (IOException e) {
            //no action 
        }
       
        try {
            PrintWriter out = new PrintWriter(new FileWriter(fileInv));
            invTest.stream().forEach(p -> {
                out.println(p.getProductType()
                        + "," + p.getCostPerSqFt()
                        + "," + p.getLaborCostPerSqFt());
            });
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println("Write failed.");
        }
        
        ArrayList<Product> result = instance.readProducts(fileInv);

        assertEquals(invTest.size(), result.size());
        assertEquals(invTest.get(1).getProductType(), result.get(1).getProductType());
    }

    /**
     * Test of writeProducts method, of class DAOToFileImpl.
     */
    @Test
    public void testWriteProducts() {
        System.out.println("writeProducts");
        try {
            PrintWriter out = new PrintWriter(new FileWriter(fileInv));
            out.flush();
            out.close();

        } catch (IOException e) {
            //no action 
        }
        invTest = new ArrayList<>();
        invTest.add(new Product("Carpet", 2.25, 2.10));
        invTest.add(new Product("Laminate", 1.75, 2.10));
        invTest.add(new Product("Tile", 3.50, 4.15));
        invTest.add(new Product("Wood", 5.15, 4.75));
        instance.writeProducts(fileInv, invTest);
        
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
        
        assertEquals(invTest.size(), InvList.size());
        assertEquals(invTest.get(2).getProductType(), (InvList.get(2).getProductType()));
    }

    /**
     * Test of readTaxes method, of class DAOToFileImpl.
     */
    @Test
    public void testReadTaxes() {
        System.out.println("readTaxes");
        try {
            PrintWriter out = new PrintWriter(new FileWriter(fileTax));
            out.flush();
            out.close();

        } catch (IOException e) {
            //no action 
        }
       
        try {
            PrintWriter out = new PrintWriter(new FileWriter(fileTax));
            taxTest.stream().forEach(p -> {
                out.println(p.getState()
                        + "," + p.getTaxRate());
            });
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println("Write failed.");
        }
        
        ArrayList<Taxes> result = instance.readTaxes(fileTax);

        assertEquals(taxTest.size(), result.size());
        assertEquals(taxTest.get(1).getState(), result.get(1).getState());
    }

    /**
     * Test of writeTaxes method, of class DAOToFileImpl.
     */
    @Test
    public void testWriteTaxes() {
        System.out.println("writeTaxes");
        try {
            PrintWriter out = new PrintWriter(new FileWriter(fileTax));
            out.flush();
            out.close();

        } catch (IOException e) {
            //no action 
        }
        taxTest = new ArrayList<>();
        taxTest.add(new Taxes("OH", 6.25));
        taxTest.add(new Taxes("PA", 6.75));
        taxTest.add(new Taxes("MI", 5.75));
        taxTest.add(new Taxes("IN", 6.00));
        instance.writeTaxes(fileTax, taxTest);
        
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
        
        assertEquals(taxTest.size(), TaxList.size());
        assertEquals(taxTest.get(2).getState(), (TaxList.get(2).getState()));

    } 
}
