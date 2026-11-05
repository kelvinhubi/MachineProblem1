package org.main.services.impl;

import org.main.model.Products;
import org.main.services.IProducts;

import java.util.HashMap;

public class ProductService implements IProducts {//For example this is the one who gets the list in the database
    public HashMap<Integer, Products> getProducts(){
        HashMap<Integer, Products> products = new HashMap<>();
        products.put(1,new Products("Oppo N9 Ultra ",125_000.00));
        products.put(2,new Products("Vivo X300 Ultra",118_000.00));
        products.put(3,new Products("Xiaomi 17 Ultra",75_999.00));
        products.put(4,new Products("IPhone 17 Pro Max",168_000.00));
        return products;
    }
}
