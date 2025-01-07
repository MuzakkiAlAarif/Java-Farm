package DAO;

import Class.Product;
import Class.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.mycompany.java_farm.Connector;

public class ProductDAO {
    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection; // Menggunakan koneksi yang diberikan
        System.out.println("ProductDAO initialized with connection: " + connection);
    }

    public ProductDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Menambahkan produk baru ke database.
     * @param product Produk yang akan ditambahkan.
     * @throws SQLException Jika terjadi kesalahan saat menambahkan produk.
     */
    public void addProduct(Product product) throws SQLException {
        String query = "INSERT INTO product (product, harga, gambar, stock, categoryId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getProduct());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getImage()); // Path absolut
            stmt.setInt(4, product.getStock());
            stmt.setInt(5, product.getCategory().getCategoryId());
            stmt.executeUpdate();
            System.out.println("Product added: " + product.getProduct());
        } catch (SQLException e) {
            System.err.println("Error adding product: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Mengambil produk berdasarkan ID.
     * @param productId ID produk yang akan diambil.
     * @return Produk yang ditemukan, atau Optional.empty() jika tidak ada.
     * @throws SQLException Jika terjadi kesalahan saat mengambil produk.
     */
    public Optional<Product> getProduct(int productId) throws SQLException {
        String query = "SELECT * FROM product WHERE productId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String imagePath = rs.getString("gambar"); // Ambil path dari database
                Product product = new Product(
                    rs.getInt("productId"),
                    rs.getString("product"),
                    rs.getDouble("harga"),
                    imagePath, // Menggunakan path yang sesuai
                    rs.getInt("stock"),
                    new Category(rs.getInt("categoryId"), null)
                );
                System.out.println("Product retrieved: " + product.getProduct());
                return Optional.of(product);
            }
        } catch (SQLException e) {
            System.err.println("Error getting product: " + e.getMessage());
            throw e;
        }
        return Optional.empty();
    }

    /**
     * Mengambil semua produk dari database.
     * @return Daftar semua produk.
     * @throws SQLException Jika terjadi kesalahan saat mengambil produk.
     */
    public List<Product> getAllProducts() throws SQLException {
        String query = "SELECT * FROM product";
        List<Product> products = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String imagePath = rs.getString("gambar"); // Ambil path dari database
                products.add(new Product(
                    rs.getInt("productId"),
                    rs.getString("product"),
                    rs.getDouble("harga"),
                    imagePath, // Menggunakan path yang sesuai
                    rs.getInt("stock"),
                    new Category(rs.getInt("categoryId"), null)
                ));
                System.out.println("Product loaded: " + rs.getString("product"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all products: " + e.getMessage());
            throw e;
        }
        return products;
    }

    /**
     * Memperbarui informasi produk di database.
     * @param product Produk yang akan diperbarui.
     * @throws SQLException Jika terjadi kesalahan saat memperbarui produk.
     */
    public void updateProduct(Product product) throws SQLException {
        String query = "UPDATE product SET product = ?, harga = ?, gambar = ?, stock = ?, categoryId = ? WHERE productId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getProduct());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getImage()); // Pastikan path gambar sesuai
            stmt.setInt(4, product.getStock());
            stmt.setInt(5, product.getCategory().getCategoryId());
            stmt.setInt(6, product.getProductId());
            stmt.executeUpdate();
            System.out.println("Product updated: " + product.getProduct());
        } catch (SQLException e) {
            System.err.println("Error updating product: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Menghapus produk dari database berdasarkan ID.
     * @param productId ID produk yang akan dihapus.
     * @throws SQLException Jika terjadi kesalahan saat menghapus produk.
     */
    public void deleteProduct(int productId) throws SQLException {
        String query = "DELETE FROM product WHERE productId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
            System.out.println("Product deleted with ID: " + productId);
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
            throw e;
        }
    }
}
