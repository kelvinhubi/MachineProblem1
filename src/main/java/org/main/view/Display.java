package org.main.view;

import org.main.model.Products;
import org.main.services.IProducts;

import java.io.BufferedReader;
import java.util.Map;

public class Display {

    private final IProducts productsService;

    public Display(IProducts productsService) {
        this.productsService = productsService;
    }

    public void displayMenu() {
        System.out.print("Select Your Mobile Phone: \n\n");
        Map<Integer, Products> products = productsService.getProducts();
        for (int i = 1; i <= products.size(); i++) {
            Products product = products.get(i);
            System.out.printf("%1$d - %2$s (%3$s)\n", i, product.getProductModelName(), product.getPrice());
        }
    }

    public void displayTitle() {
        System.out.println("Welcome to Lazada Online Ordering System");
    }

    public boolean displayReceipt(String value, BufferedReader br) {
        System.out.print(value);

        try {
            System.out.print("Do you want to try again Y/N: ");
            String choice = br.readLine();
            return choice.equalsIgnoreCase("y");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
