/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmasteryproject.dao;

import com.mycompany.flooringmasteryproject.dto.Order;
import com.mycompany.flooringmasteryproject.dto.Product;
import com.mycompany.flooringmasteryproject.dto.Taxes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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
public class DAOInMemoryImplTest {

    DAOInMemoryImpl dao;
    HashMap<String, ArrayList<Order>> orders;
    ArrayList<Order> ordTest;
    ArrayList<Order> ordTest2;
    ArrayList<Product> invTest;
    ArrayList<Taxes> taxTest;
    DateTimeFormatter formatter;
    String today;

    public DAOInMemoryImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dao = new DAOInMemoryImpl("TestTax.txt", "TestProducts.txt");
        orders = new HashMap<>();
        ordTest = new ArrayList<>();
        ordTest2 = new ArrayList<>();
        invTest = new ArrayList<Product>();
        taxTest = new ArrayList<Taxes>();
        formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        today = "Orders_" + LocalDate.now().format(formatter) + ".txt";
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of displayOrders method, of class DAOInMemoryImpl.
     */
    @Test
    public void testDisplayOrders() {
        System.out.println("displayOrders");
        int expResult = 1;
        int result = dao.displayOrders(today).size();
        assertEquals(expResult, result);
        
        String date2 = "Orders_06062016.txt";
        ArrayList<Order> result2 = dao.displayOrders(date2);
        assertNull(result2);
    }

    /**
     * Test of addOrder method, of class DAOInMemoryImpl.
     */
    @Test
    public void testAddOrder() {
        System.out.println("addOrder");
        ordTest = dao.displayOrders(today);
        Order x = new Order (2, "Bob Sagget", 204.4, dao.passTaxTable().get(1), dao.passInv().get(1));
        String msg = dao.addOrder(x);
        ordTest2 = dao.displayOrders(today);
        
        int expResult = 2;
        int result = ordTest.size(); 
        assertEquals(expResult, result);
        
        int expResult2 = 2;
        int result2 = ordTest2.size(); 
        assertEquals(expResult2, result2);
        
        int expResult3 = 2;
        int result3 = ordTest2.get(1).getOrderNumber(); 
        assertEquals(expResult3, result3);
    }

    /**
     * Test of editOrder method, of class DAOInMemoryImpl.
     */
    @Test
    public void testEditOrder() {
        System.out.println("editOrder");
        String date = today;
        int index = 0;
        ordTest = dao.displayOrders(date);
        Order y = ordTest.get(0).clone();
        y.setOrderNumber(2);
        y.setCustomerName("Joe Bob");
        Order z = dao.editOrder(date, index, y);
        ordTest2 = dao.displayOrders(date);
        
        String expResult = y.getCustomerName();
        String result = z.getCustomerName();
        assertEquals(expResult, result);
        
        int expResult2 = 2;
        int result2 = ordTest2.get(0).getOrderNumber();
        assertEquals(expResult2, result2);

    }

    /**
     * Test of removeOrder method, of class DAOInMemoryImpl.
     */
    @Test
    public void testRemoveOrder() {
        System.out.println("removeOrder");
        String orderDate = today;
        int index = 0;
        ordTest = dao.displayOrders(today);
        Order a = ordTest.get(0);
        Order b = dao.removeOrder(orderDate, index);
        assertEquals(a.getCustomerName(), b.getCustomerName());
        
        ordTest2 = dao.displayOrders(today);
        assertNull(ordTest.get(0));

    }

    /**
     * Test of generateID method, of class DAOInMemoryImpl.
     */
    @Test
    public void testGenerateID() {
        System.out.println("generateID");
        String date = today;
        int expResult = 2;
        int result = dao.generateID(date);
        assertEquals(expResult, result);

    }

    /**
     * Test of returnOrder method, of class DAOInMemoryImpl.
     */
    @Test
    public void testReturnOrder() {
        System.out.println("returnOrder");
        String orderDate = today;
        int index = 0;
        ordTest = dao.displayOrders(today);
        
        assertEquals(1, ordTest.get(0).getOrderNumber());
        assertEquals("Wise", ordTest.get(0).getCustomerName());
    }
    
}
