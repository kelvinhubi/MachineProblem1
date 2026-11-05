package org.main.services.impl;
import org.main.model.Products;
import org.main.model.Transaction;
import org.main.services.CardService;
import org.main.services.IProducts;
import org.main.services.ITransaction;

import java.io.BufferedReader;
import java.util.HashMap;

public class TransactionService implements ITransaction{
    @Override
    public Transaction createTransaction(BufferedReader br) {
        try{
            IProducts productService = new ProductService();
            HashMap<Integer, Products> products = productService.getProducts();
            System.out.print("Enter the number of the chosen Model: ");
            int productChosen = Integer.parseInt(br.readLine());
            System.out.print("Enter number of items: ");
            int subitems = Integer.parseInt(br.readLine());
            System.out.print("Enter credit card number: ");
            String creditNumber = br.readLine();
            CardService cService = new CreditCardService();

            if(creditNumber.length() >=16 && subitems >0){
                if(cService.validateCard(creditNumber)){
                    return processTransaction(products.get(productChosen), subitems);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Transaction processTransaction(Products product, int subitems){
            double grossAmount = product.getPrice() * subitems;
            double vatAmount = grossAmount * VAT;
            double totalPrice = grossAmount + vatAmount;
        return new Transaction(grossAmount, vatAmount, totalPrice, product.getProductModelName(),subitems);
    }
}
