package org.main.controller;

import org.main.model.Customer;
import org.main.model.Transaction;
import org.main.services.ITransaction;
import org.main.services.impl.CreditCardService;
import org.main.services.impl.ProductService;
import org.main.services.impl.TransactionService;
import org.main.view.Display;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainController {

    public void runProgram() {
        Display disp = new Display(new ProductService());
        boolean retry = true;
        disp.displayTitle();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (retry) {
                try {
                    System.out.print("Enter Name: ");
                    String name = br.readLine();
                    System.out.print("Enter ShippingAddress: ");
                    String shippingAddress = br.readLine();
                    Customer customer = new Customer(name, shippingAddress);
                    disp.displayMenu();
                    ITransaction transaction = new TransactionService(new ProductService(), new CreditCardService());
                    Transaction receipt = transaction.createTransaction(br);

                    String str = "Item Ordered: %s\n".formatted(receipt.modelName()) +
                            "Quantity: %d\n".formatted(receipt.subItems()) +
                            "Gross Amount: PHP %.2f\n".formatted(receipt.grossAmount()) +
                            String.format("VAT (12%%): PHP %.2f\n", receipt.vat()) +
                            "Total Price: PHP %.2f\n".formatted(receipt.totalPrice());

                    retry = disp.displayReceipt(String.valueOf(str), br);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
