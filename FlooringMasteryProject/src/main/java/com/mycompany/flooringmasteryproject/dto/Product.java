/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmasteryproject.dto;

/**
 *
 * @author apprentice
 */
public class Product {
    private String productType;
    private double costPerSqFt;
    private double laborCostPerSqFt;
    
    public Product(Product x){
    this.productType = x.getProductType();    
    this.costPerSqFt = x.getCostPerSqFt();
    this.laborCostPerSqFt = x.getLaborCostPerSqFt();   
    }
    
    public Product (String productType, double costPerSqFt, double laborCostPerSqFt){
        this.productType = productType;
        this.costPerSqFt = costPerSqFt;
        this.laborCostPerSqFt = laborCostPerSqFt;
    }

    @Override
    public Product clone (){
        return new Product(this);
    }
    /**
     * @return the productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * @return the costPerSqFt
     */
    public double getCostPerSqFt() {
        return costPerSqFt;
    }

    /**
     * @param costPerSqFt the costPerSqFt to set
     */
    public void setCostPerSqFt(double costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    /**
     * @return the laborCostPerSqFt
     */
    public double getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }

    /**
     * @param laborCostPerSqFt the laborCostPerSqFt to set
     */
    public void setLaborCostPerSqFt(double laborCostPerSqFt) {
        this.laborCostPerSqFt = laborCostPerSqFt;
    }
}
