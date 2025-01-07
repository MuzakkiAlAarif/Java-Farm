// Kelas Cart
package Class;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> items; // Map to store products and their quantities

    // Default Constructor
    public Cart() {
        this.items = new HashMap<>();
    }

    // Method to add an item to the cart
    public void addItem(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity); // Add or update quantity
    }

    // Method to display all items in the cart
    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("The cart is empty.");
            return;
        }
        System.out.println("Cart Items:");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(product.getProduct() + " - Quantity: " + quantity);
        }
    }

    // Getters and Setters for the items
    public Map<Product, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Product, Integer> items) {
        this.items = items;
    }
}
