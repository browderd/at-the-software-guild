/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmasteryproject.ops;

import com.mycompany.flooringmasteryproject.dao.DAO;
import com.mycompany.flooringmasteryproject.dto.Order;
import com.mycompany.flooringmasteryproject.dto.Product;
import com.mycompany.flooringmasteryproject.dto.Taxes;
import com.mycompany.flooringmasteryproject.ui.ConsoleIO;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author apprentice
 */
public class OrderController {

    ConsoleIO cons = new ConsoleIO();
    DAO dao;

    public OrderController(DAO dao) {
        this.dao = dao;
    }

    public void run() {
        int option;
        cons.printString("\nWelcome to Floor Masters!\n");

        do {

            cons.printString("\nMain Menu\n\t1: Display Orders\n\t"
                    + "2: Add an Order\n\t3: Edit an Order\n\t4: Remove an order"
                    + "\n\t5: Save Current Work\n\t6:Quit\n\n");
            option = cons.getInt("Please enter the number beside the action you wish to take.");

            switch (option) {
                case 1: //display orders
                    listForDate(askDate());
                    break;
                case 2: // add an order
                    cons.printString("\n" + dao.addOrder(buildOrder()) + "\n");
                    break;
                case 3: //edit order
                    int pick1;

                    do {
                        pick1 = cons.getInt("\nPress 1 to edit an order or 2 to exit.", 1, 2);
                        String date = askDate();
                        listForDate(date);
                        int index = cons.getInt("\nPlease enter the order number you wish to edit.") - 1;
                        Order edit = dao.returnOrder(date, index);
                        if (edit == null) {
                            cons.printString("\nOrder not found.\n");
                        } else {
                            cons.printString("\n\t" + edit + "\n");
                            if (cons.getInt("\nPress 1 to confirm and edit or 2 to cancel.", 1, 2) == 1) {
                                Order z = orderEditor(edit);
                                dao.editOrder(date, index, z);
                                cons.printString("\nThis order has been updated.\n");
                            }
                        }
                    } while (pick1 == 1);
                    break;
                case 4: //remove order
                    int choice;

                    do {
                        choice = cons.getInt("\nPress 1 to remove an order or 2 to exit.", 1, 2);
                        if (choice == 1){
                        String date = askDate();
                        listForDate(date);
                        int index = cons.getInt("\nPlease enter the order number you wish to remove.") - 1;
                        Order del = dao.returnOrder(date, index);
                        if (del == null) {
                            cons.printString("\nOrder not found.\n");
                        } else {
                            cons.printString("\n\t" + del + "\n");
                            int pickRem = cons.getInt("\nPress 1 to confirm and delete or 2 to cancel.", 1, 2);
                            if ( pickRem == 1) {
                                dao.removeOrder(date, index);
                                cons.printString("\nThis order has been removed.\n");
                            }else{
                                run();
                            }
                        }
                        }
                    } while (choice == 1);
                    break;
                case 5: // save     
                    dao.save();
                    break;
                case 6: // exit
                    cons.printString("\nGoodbye!\n");
                    break;
            }
        } while (option != 6);
    }

    public Order buildOrder() {
        //lists products and with an int reference
        dao.passInv().stream()
                .forEach(p -> cons.printString("\n\t"
                        + (dao.passInv().indexOf(p) + 1) + " " + p.getProductType()));
        int pickProd = cons.getInt("\n\nPlease enter the number next to the product "
                + "for the order.", 1, dao.passInv().size())-1;

        //lists taxes and with an int reference
        dao.passTaxTable().stream()
                .forEach(p -> cons.printString("\n\t" + (dao.passTaxTable().indexOf(p) + 1)
                        + " " + p.getState()));
        int pickTax = cons.getInt("\n\nPlease enter the number next to the state for"
                + " the order.", 1, dao.passTaxTable().size())-1;

        Taxes x = dao.passTaxTable().get(pickTax).clone();
        Product y = dao.passInv().get(pickProd).clone();

        //assumes orders are only assigned to the date they are created
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String date = dao.getToday();
        int orderNumber = dao.generateID(date);

        String customerName = cons.getString("Please enter the customer name.");
        double area = cons.getDouble("How many square feet do they need?");

        return new Order(orderNumber, customerName, area, x, y);
    }

    public String askDate() {
        cons.printString("\nPlease enter all values with leading zeroes where applicable.\n");
        String year = cons.getString("Which year?");
        String month = cons.getString("Which month?");
        String day = cons.getString("Which day?");
        String date = month + day + year;

        if (date.length() == 8) {
            return "Orders_" + date + ".txt";
        } else {
            return askDate();
        }
    }

    public void listForDate(String ask) {
        ArrayList<Order> x = dao.displayOrders(ask);
        if (x == null) {
            cons.printString("\nThere are no orders for this date.\n");
        } else {
            x.stream()
                    .forEach(o -> cons.printString("\n\t" + o));
        }
        cons.printString("\n");
    }

    public Order orderEditor(Order x) { //incomplete
        Taxes tax;
        Product prod;
        String customerName;
        double area;

        //lists products and with an int reference
        dao.passInv().stream()
                .forEach(p -> cons.printString("\n\t"
                        + (dao.passInv().indexOf(p) + 1) + " " + p.getProductType()));
        String pickProd = cons.getString("\n\nPlease enter the number next to the product "
                + "for the order.(" + x.getProductType() + ")");
        if (pickProd.equals("")) {
            prod = new Product(x.getProductType(), x.getCostPerSqFt(), x.getLaborCostPerSqFt());
        } else {
            int pickedProd = Integer.parseInt(pickProd) - 1;
            prod = dao.passInv().get(pickedProd).clone();
        }

        //lists taxes and with an int reference
        dao.passTaxTable().stream()
                .forEach(p -> cons.printString("\n\t" + (dao.passTaxTable().indexOf(p) + 1)
                        + " " + p.getState()));
        String pickTax = cons.getString("\n\nPlease enter the number next to the state for"
                + " the order.(" + x.getState() + ")");
        if (pickTax.equals("")) {
            tax = new Taxes(x.getState(), x.getTax());
        } else {
            int pickedTax = Integer.parseInt(pickProd) - 1;
            tax = dao.passTaxTable().get(pickedTax).clone();
        }

        int orderNumber = x.getOrderNumber();

        String cust = cons.getString("\nPlease enter the customer name.(" + x.getCustomerName() + ")");
        if (cust.equals("")) {
            customerName = x.getCustomerName();
        } else {
            customerName = cust;
        }

        String areaS = cons.getString("\nHow many square feet do they need?(" + x.getArea() + ")");
        if (cust.equals("")) {
            area = x.getArea();
        } else {
            area = Double.parseDouble(areaS);
        }

        return new Order(orderNumber, customerName, area, tax, prod);
    }
}
