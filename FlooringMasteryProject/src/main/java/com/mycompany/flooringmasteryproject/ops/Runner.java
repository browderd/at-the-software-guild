/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmasteryproject.ops;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class Runner {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderController session;

        if (new Runner().config().equalsIgnoreCase("test")) {
            session = (OrderController) ctx.getBean("TestMode");
            session.run();
        } else {
            session = (OrderController) ctx.getBean("ProdMode");
            session.run();
        }

    }

    public String config() {
        ArrayList<String> conf = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader("config.txt")));
            String temp;
            String[] tempStrings;

            while (sc.hasNextLine()) {
                temp = sc.nextLine();
                tempStrings = temp.split(",");
                conf.add(tempStrings[0]);

            }
        } catch (FileNotFoundException e) {
            //No relevant action to be performedreturn tempStrings[0];
        }
        return conf.get(0);
    }
}
