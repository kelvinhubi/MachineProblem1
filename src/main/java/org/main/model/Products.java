package org.main.model;

import java.util.HashMap;
import java.util.List;

public class Products {
    private final String productModelName;
    private final double productPrice;

    public Products(String productModelName,double productPrice){
        this.productModelName = productModelName;
        this.productPrice = productPrice;
    }

    public String getProductModelName(){
        return this.productModelName;
    }
    public double getPrice(){
        return this.productPrice;
    }
}
