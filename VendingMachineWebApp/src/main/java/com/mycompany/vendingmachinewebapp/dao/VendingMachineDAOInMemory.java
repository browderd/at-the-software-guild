/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachinewebapp.dao;

import com.mycompany.vendingmachinewebapp.dto.Change;
import com.mycompany.vendingmachinewebapp.dto.Item;
import java.util.HashMap;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author apprentice
 */
public class VendingMachineDAOInMemory implements VendingMachineDAO {

    private HashMap<Integer, Item> inventory = popMap();
    private static int itemIdCounter = 6;
    private double balance;

    private HashMap popMap() {
        HashMap<Integer, Item> pop = new HashMap<>();

        Item a = new Item();
        a.setItemID(0);
        a.setName("Coke");
        a.setCost(1.50);
        a.setQuantity(20);
        a.setInfo("Mmmm high fructose corn syrup!");
        pop.put(0, a);

        Item b = new Item();
        b.setItemID(1);
        b.setName("Popcorn");
        b.setCost(3.00);
        b.setQuantity(50);
        b.setInfo("Unimaginative.");
        pop.put(1, b);

        Item c = new Item();
        c.setItemID(2);
        c.setName("Hotdog");
        c.setCost(1.25);
        c.setQuantity(14);
        c.setInfo("These are bad. Mmkay?");
        pop.put(2, c);
        
        Item d = new Item();
        d.setItemID(3);
        d.setName("Twizzlers");
        d.setCost(1.75);
        d.setQuantity(34);
        d.setInfo("Straws2.0");
        pop.put(3, d);
        
        Item e = new Item();
        e.setItemID(4);
        e.setName("Reeses Pieces");
        e.setCost(1.00);
        e.setQuantity(24);
        e.setInfo("The only real movie candy.");
        pop.put(4, e);
        
        Item f = new Item();
        f.setItemID(5);
        f.setName("Nachos");
        f.setCost(2.25);
        f.setQuantity(5);
        f.setInfo("Insert words here.");
        pop.put(5, f);

        return pop;
    }

    @Override
    public Item addItem(Item x) {
        x.setItemID(itemIdCounter);
        itemIdCounter++;
        inventory.put(x.getItemID(), x);
        return x;
    }

    @Override
    public Item getItemByID(int itemID) {
        return inventory.get(itemID);
    }

    @Override
    public List<Item> returnAll() {
        return inventory.values().stream()
                .filter(i -> i.getQuantity() != 0)
                .collect(toList());
    }

    @Override
    public void updateItem(Item x) {
        inventory.put(x.getItemID(), x);
    }
    
    @Override
    public Change purchase(int itemID){
        if (inventory.get(itemID).getCost() > balance){
            Change x = new Change();
            x.setIsEnough(false);
            return x;
        } else{
        
        inventory.get(itemID).setQuantity(inventory.get(itemID).getQuantity() - 1);
        
        int p = (int) ((balance - inventory.get(itemID).getCost() + .005) * 100);
        int quarters = p/25;
        int dimes = (p - quarters*25)/10;
        int nickels = (p - quarters*25 - dimes*10)/5;
        int pennies = p - quarters*25 - dimes*10 - nickels*5;
        balance = 0;
        return new Change(quarters, dimes, nickels, pennies);
    }
    }

    /**
     * @return the balance
     */
    @Override
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
