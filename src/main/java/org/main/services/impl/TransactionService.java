package org.main.services.impl;

import org.main.model.Products;
import org.main.model.Transaction;
import org.main.services.CardService;
import org.main.services.IProducts;
import org.main.services.ITransaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class TransactionService implements ITransaction {

    private final IProducts productService;
    private final CardService cService;

    public TransactionService(IProducts productService, CardService cService) {
        this.productService = productService;
        this.cService = cService;
    }

    @Override
    public Transaction createTransaction(BufferedReader br) {

        try {
            Map<Integer, Products> products = productService.getProducts();
            System.out.print("Enter the number of the chosen Model: ");
            int productChosen = Integer.parseInt(br.readLine());
            System.out.print("Enter number of items: ");
            int subitems = Integer.parseInt(br.readLine());
            System.out.print("Enter credit card number: ");
            String creditNumber = br.readLine();
            if (subitems > 0) {
                if (creditNumber == null || !creditNumber.matches("\\d+")) {
                    throw new IllegalArgumentException("Card number must contain digits only.");
                }

                if (creditNumber.length() != 16) {
                    throw new IllegalArgumentException("Card number must contain exactly 16 digits.");
                }
                if (!cService.validateCard(creditNumber)) {
                    throw new IllegalArgumentException("Invalid card number or quantity.");
                }
                Products product = products.get(productChosen);
                if (product == null) {
                    throw new IllegalArgumentException("Product not listed");
                }
                return processTransaction(products.get(productChosen), subitems);
            }

        }catch (NumberFormatException e) {
            // Handles cases where the user types letters instead of numbers for choices/quantity
            System.out.println("⚠️ Invalid input format. Please enter valid numbers.\n");
        } catch (IllegalArgumentException e) {
            // Handles your custom validation failures (bad card length, missing product, etc.)
            System.out.println("⚠️ Error: " + e.getMessage() + " Please try again.\n");
        } catch (IOException e) {
            System.out.println("⚠️ A system I/O error occurred: " + e.getMessage());
        }
        return new Transaction(0,0,0,"Unknown",0);
    }

    public Transaction processTransaction(Products product, int subitems) {
        double grossAmount = product.getPrice() * subitems;
        double vatAmount = grossAmount * VAT;
        double totalPrice = grossAmount + vatAmount;
        return new Transaction(grossAmount, vatAmount, totalPrice, product.getProductModelName(), subitems);
    }
}
