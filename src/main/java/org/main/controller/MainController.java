package org.main.controller;
import org.main.model.Customer;
import org.main.model.Transaction;
import org.main.services.ITransaction;
import org.main.services.impl.TransactionService;
import org.main.view.Display;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainController {

    public void runProgram(){
        boolean retry = true;
        Display.displayTitle();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            while(retry){
                System.out.print("Enter Name: ");
                String name = br.readLine();
                System.out.print("Enter ShippingAddress: ");
                String shippingAddress = br.readLine();
                Customer customer = new Customer(name,shippingAddress);
                Display.displayMenu();
                ITransaction transaction = new TransactionService();
                Transaction receipt = transaction.createTransaction(br);

                StringBuilder str = new StringBuilder();

                str.append("Item Ordered: %s\n".formatted(receipt.modelName()));
                str.append("Quantity: %d\n".formatted(receipt.subItems()));
                str.append("Gross Amount: PHP %.2f\n".formatted(receipt.grossAmount()));
                str.append(String.format("VAT (12%%): PHP %.2f\n", receipt.vat()));
                str.append("Total Price: PHP %.2f\n".formatted(receipt.totalPrice()));
                retry = Display.displayReceipt(String.valueOf(str),br);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
