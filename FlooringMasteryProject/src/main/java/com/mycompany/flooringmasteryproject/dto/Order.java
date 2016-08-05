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
public class Order {
    private int orderNumber;
    private String customerName;
    private String state;
    private String productType;
    private double taxRate;
    private double area;
    private double costPerSqFt;
    private double laborCostPerSqFt;
    private double materialCost;
    private double laborCost;
    private double tax;
    private double totalCost;
    
    
    public Order (int orderNumber, String customerName, double area, Taxes x, Product y){
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.area = area;
        state = x.getState();
        taxRate = x.getTaxRate();
        productType = y.getProductType();
        costPerSqFt = y.getCostPerSqFt();
        laborCostPerSqFt = y.getLaborCostPerSqFt();
        materialCost = this.costPerSqFt*this.area;
        laborCost = this.laborCostPerSqFt*this.area;
        tax = (materialCost+laborCost)*this.taxRate;
        totalCost = materialCost+laborCost+tax;
        
    }
    
    public Order(Order x){
        this.orderNumber = x.getOrderNumber();
        this.customerName = x.getCustomerName();
        this.area = x.getArea();
        this.state = x.getState();
        this.taxRate = x.getTaxRate();
        this.productType = x.getProductType();
        this.costPerSqFt = x.getCostPerSqFt();
        this.laborCostPerSqFt = x.getLaborCostPerSqFt();
        this.materialCost = x.getMaterialCost();
        this.laborCost = x.getLaborCost();
        this.tax = x.getTax();
        this.totalCost = x.getTotalCost();
    }
    
    public Order(int orderNumber, String customerName, double area, String state, double taxRate,
            String productType, double costPerSqFt, double laborCostPerSqFt, double materialCost,
            double laborCost, double tax, double totalCost){
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.area = area;
        this.state = state;
        this.taxRate = taxRate;
        this.productType = productType;
        this.costPerSqFt = costPerSqFt;
        this.laborCostPerSqFt = laborCostPerSqFt;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.tax = tax;
        this.totalCost = totalCost;
    }
    
    @Override
    public Order clone(){
        return new Order(this);
    }
    
    @Override
    public String toString(){
        return orderNumber + " - " + customerName + " - " + totalCost + ".";
    }

    /**
     * @return the orderNumber
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
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
     * @return the taxRate
     */
    public double getTaxRate() {
        return taxRate;
    }

    /**
     * @param taxRate the taxRate to set
     */
    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * @return the area
     */
    public double getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * @return the costPerSqFt
     */
    public double getCostPerSqFt() {
        return costPerSqFt;
    }


    /**
     * @return the laborCostPerSqFt
     */
    public double getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }


    /**
     * @return the materialCost
     */
    public double getMaterialCost() {
        return materialCost;
    }


    /**
     * @return the laborCost
     */
    public double getLaborCost() {
        return laborCost;
    }

    /**
     * @return the tax
     */
    public double getTax() {
        return tax;
    }


    /**
     * @return the totalCost
     */
    public double getTotalCost() {
        return totalCost;
    }

}
