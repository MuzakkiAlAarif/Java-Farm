package Class;

import java.awt.Image;

public class Product {
    private int productId;
    private String product; // Nama produk
    private double price;   // Harga produk
    private String image;   // URL atau path gambar
    private int stock;      // Stok produk
    private Category category;  // Referensi ke kategori

    // Constructor
    public Product(int productId, String product, double price, String image, int stock, Category category) {
        this.productId = productId;
        this.product = product;
        this.price = price;
        this.image = image;
        this.stock = stock;
        this.category = category;
    }

    // Getter dan setter
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // Implementasi metode tambahan
    public String getImagePath() {
        return image; // Mengembalikan path gambar
    }

    public void setImagePath(String imagePath) {
        this.image = imagePath; // Menyimpan path gambar
    }
 }

   