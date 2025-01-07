package Class;

public class Pengguna extends User {
    private Cart cart;

    // Constructor lengkap
    public Pengguna(int userId, String username, String password, String email, String Role) {
        super(userId, username, password, email, Role);
        this.cart = new Cart(); // Initialize a new cart
    }

    // Method to browse products
    public void browseProducts() {
        System.out.println("Browsing available products...");
        // Logic to display available products (to be implemented)
    }

    // Method to add product to cart
    public void addToCart(Product product, int quantity) {
        cart.addItem(product, quantity);
        System.out.println("Added " + quantity + " of " + product.getProduct() + " to the cart.");
    }

    // Method to view cart items
    public void viewCart() {
        System.out.println("Viewing cart items:");
        cart.displayItems();
    }

    // Method to checkout
    public void checkout() {
        System.out.println("Proceeding to checkout...");
        // Logic for generating order, calculating total, etc.
        cart.displayItems();
    }
}
