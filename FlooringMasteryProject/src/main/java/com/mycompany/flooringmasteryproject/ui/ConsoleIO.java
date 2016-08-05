/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmasteryproject.ui;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class ConsoleIO {

    Scanner kb = new Scanner(System.in);

    public int getInt(String x) {
        int y = 0;
        boolean failed;
        String s = "";

        do {
            System.out.println(x);
            try {
                s = kb.nextLine();
                y = Integer.parseInt(s);
                failed = false;
            } catch (NumberFormatException e) {
                System.out.println("Input must be an integer.");
                failed = true;
            }

        } while (failed);
        return y;
    }

    public int getInt(String x, int min, int max) {
        int w = 0;
        boolean failed;
        String s = "";

        do {
            failed = false;
            System.out.println(x);

            try {

                s = kb.nextLine();
                w = Integer.parseInt(s);

                if (w < min || w > max) {
                    System.out.println("Please give a number between " + min + " and " + max + ". ");
                    failed = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input must be an integer.");
                failed = true;
            }

        } while (failed);

        return w;

    }

    public String getString(String x) {
        System.out.println(x);
        return kb.nextLine();
    }

    public float getFloat(String x) {
        boolean failed;
        float y = 0;
        do {
            failed = false;
            System.out.println(x);
            try {
                String s = kb.nextLine();
                y = Float.parseFloat(s);
            } catch (NumberFormatException e) {
                System.out.println("Input must be a number.");
                failed = true;
            }
        } while (failed);
        return y;
    }

    public float getFloat(String x, float min, float max) {
        float w = 0;
        boolean failed;

        do {
            failed = false;
            System.out.println(x);

            try {
                String s = kb.nextLine();
                w = Float.parseFloat(s);

                if (w < min || w > max) {
                    System.out.println("Please give a number between " + min + " and " + max + ". ");
                    failed = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input must be a number.");
                failed = true;
            }
        } while (failed);
        return w;
    }

    public double getDouble(String x) {
        double y = 0;
        boolean failed;

        do {
            System.out.println(x);
            failed = false;
            try {
                String s = kb.nextLine();
                y = Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Input must be a number.");
                failed = true;
            }

        } while (failed);
        return y;
    }

    public double getDouble(String x, double min, double max) {
        double w = 0;
        boolean failed;

        do {
            failed = false;
            System.out.println(x);

            try {

                String s = kb.nextLine();
                w = Double.parseDouble(s);

                if (w < min || w > max) {
                    System.out.println("Please give a number between " + min + " and " + max + ". ");
                    failed = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input must be a number.");
                failed = true;
            }
        } while (failed);

        return w;

    }

    public void printString(String x) {
        System.out.print(x);
        
    }
    
    public void printString (Object x){
        System.out.print(x);
    }
}
