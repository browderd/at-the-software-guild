/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachinewebapp.dto;

import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class Change {
    private int pennies;
    private int nickels;
    private int dimes;
    private int quarters;
    private String result;
    private boolean isEnough = true;
    
    public Change(){}
    
    public Change (int quarters, int dimes, int nickels, int pennies){
        
        this.quarters = quarters;
        this.dimes = dimes;
        this.nickels = nickels;
        this.pennies = pennies;
        result = "Your change is " + this.quarters + " quarters, " + this.dimes + " dimes, "
                + this.nickels + " nickels, and " + this.pennies + " pennies.";
    }
    
    /**
     * @return the pennies
     */
    public int getPennies() {
        return pennies;
    }

    /**
     * @param pennies the pennies to set
     */
    public void setPennies(int pennies) {
        this.pennies = pennies;
    }

    /**
     * @return the nickels
     */
    public int getNickels() {
        return nickels;
    }

    /**
     * @param nickels the nickels to set
     */
    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    /**
     * @return the dimes
     */
    public int getDimes() {
        return dimes;
    }

    /**
     * @param dimes the dimes to set
     */
    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    /**
     * @return the quarters
     */
    public int getQuarters() {
        return quarters;
    }

    /**
     * @param quarters the quarters to set
     */
    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return the isEnough
     */
    public boolean isIsEnough() {
        return isEnough;
    }

    /**
     * @param isEnough the isEnough to set
     */
    public void setIsEnough(boolean isEnough) {
        this.isEnough = isEnough;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.pennies;
        hash = 59 * hash + this.nickels;
        hash = 59 * hash + this.dimes;
        hash = 59 * hash + this.quarters;
        hash = 59 * hash + Objects.hashCode(this.result);
        hash = 59 * hash + (this.isEnough ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Change other = (Change) obj;
        if (this.pennies != other.pennies) {
            return false;
        }
        if (this.nickels != other.nickels) {
            return false;
        }
        if (this.dimes != other.dimes) {
            return false;
        }
        if (this.quarters != other.quarters) {
            return false;
        }
        if (this.isEnough != other.isEnough) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        return true;
    }
    
    
}
