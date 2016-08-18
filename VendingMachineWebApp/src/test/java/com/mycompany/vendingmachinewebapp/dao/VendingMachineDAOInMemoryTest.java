/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachinewebapp.dao;

import com.mycompany.vendingmachinewebapp.dto.Change;
import com.mycompany.vendingmachinewebapp.dto.Item;
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
public class VendingMachineDAOInMemoryTest {
private HashMap<Integer, Item> inv;
private int itemIdCounter;
private double balance;
private VendingMachineDAOInMemory instance;
    
public VendingMachineDAOInMemoryTest() {
        
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new VendingMachineDAOInMemory();
        inv = new HashMap();
        itemIdCounter = 6;
        balance = 0;

        Item a = new Item();
        a.setItemID(0);
        a.setName("Coke");
        a.setCost(1.50);
        a.setQuantity(20);
        a.setInfo("Mmmm high fructose corn syrup!");
        inv.put(0, a);

        Item b = new Item();
        b.setItemID(1);
        b.setName("Popcorn");
        b.setCost(3.00);
        b.setQuantity(50);
        b.setInfo("Unimaginative.");
        inv.put(1, b);

        Item c = new Item();
        c.setItemID(2);
        c.setName("Hotdog");
        c.setCost(1.25);
        c.setQuantity(14);
        c.setInfo("These are bad. Mmkay?");
        inv.put(2, c);

        Item d = new Item();
        d.setItemID(3);
        d.setName("Twizzlers");
        d.setCost(1.75);
        d.setQuantity(34);
        d.setInfo("Straws2.0");
        inv.put(3, d);

        Item e = new Item();
        e.setItemID(4);
        e.setName("Reeses Pieces");
        e.setCost(1.00);
        e.setQuantity(24);
        e.setInfo("The only real movie candy.");
        inv.put(4, e);

        Item f = new Item();
        f.setItemID(5);
        f.setName("Nachos");
        f.setCost(2.25);
        f.setQuantity(5);
        f.setInfo("Insert words here.");
        inv.put(5, f);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addItem method, of class VendingMachineDAOInMemory.
     */
    @Test
    public void testAddGetByIDAndReturnAll() {
        Item g = new Item();
        g.setName("Icee");
        g.setCost(2.25);
        g.setQuantity(0);
        g.setInfo("Insert words here.");
        
        Item h = instance.addItem(g);
        
        assertEquals(instance.getItemByID(6), h);
       
        assertFalse(instance.returnAll().contains(h));
    }



    /**
     * Test of updateItem method, of class VendingMachineDAOInMemory.
     */
    @Test
    public void testUpdateItem() {
        System.out.println("updateItem");
        Item g = new Item();
        g.setItemID(5);
        g.setName("Icee");
        g.setCost(2.25);
        g.setQuantity(0);
        g.setInfo("Insert words here.");
        
        assertFalse(instance.getItemByID(5).equals(g));
        instance.updateItem(g);
        assertTrue(instance.getItemByID(5).equals(g));
    }

    /**
     * Test of purchase method, of class VendingMachineDAOInMemory.
     */
    @Test
    public void testPurchase() {
        System.out.println("purchase");
        balance = 1.5;
        
        Change x = instance.purchase(2);
        
        assertEquals(14, instance.getItemByID(2).getQuantity());
        
        assertFalse(instance.purchase(1).isIsEnough());
    }

}
