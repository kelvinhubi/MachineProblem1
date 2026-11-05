package org.main.model;

public class Customer {
    private final String name;
    private final String shippingAddress;

    public Customer(String name, String shippingAddress) {
        this.name = name;
        this.shippingAddress = shippingAddress;
    }

    public String getName(){
        return this.name;
    }
    public String getShippingAddress(){
        return this.shippingAddress;
    }



}
