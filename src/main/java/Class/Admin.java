package Class;

public class Admin extends User {

    // Constructor lengkap
    public Admin(int userId, String username, String password, String email, String Role) {
        super(userId, username, password, email, Role);
    }

    // CRUD methods for managing products
    public void createProduct(Product product) {
        System.out.println("Product " + product.getProduct() + " created successfully.");
        // Logic to save product to database
    }

    public Product readProduct(int productId) {
        System.out.println("Fetching product with ID: " + productId);
        // Logic to fetch product from database
        Category category = new Category(1, "Sample Category"); // Placeholder
        return new Product(productId, "Sample Product", 100.0, "sample_image.jpg", 50, category);
    }

    public void updateProduct(Product product) {
        System.out.println("Product " + product.getProduct() + " updated successfully.");
        // Logic to update product in database
    }

    public void deleteProduct(int productId) {
        System.out.println("Product with ID " + productId + " deleted successfully.");
        // Logic to delete product from database
    }

    // CRUD methods for categories
    public void createCategory(Category category) {
        System.out.println("Category " + category.getName() + " created successfully.");
        // Logic to save category to database
    }

    public void updateCategory(Category category) {
        System.out.println("Category " + category.getName() + " updated successfully.");
        // Logic to update category in database
    }

    public void deleteCategory(int categoryId) {
        System.out.println("Category with ID " + categoryId + " deleted successfully.");
        // Logic to delete category from database
    }
}
