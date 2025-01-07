package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Class.Category;

public class CategoryDAO {
    private Connection connection;

    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

    public void addCategory(Category category) throws SQLException {
        String query = "INSERT INTO Category (category) VALUES (?)"; // Perbaikan nama kolom
        try (PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, category.getName());
            stmt.executeUpdate();

            // Get generated categoryId
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    category.setCategoryId(generatedKeys.getInt(1)); // Set the generated categoryId
                    System.out.println("Inserted Category ID: " + category.getCategoryId()); // Log ID untuk debugging
                } else {
                    System.out.println("Failed to retrieve generated Category ID.");
                }
            }
        }
    }

    public Category getCategory(int categoryId) throws SQLException {
        String query = "SELECT * FROM Category WHERE categoryId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Category(rs.getInt("categoryId"), rs.getString("category")); // Perbaikan nama kolom
            } else {
                System.out.println("Category ID " + categoryId + " tidak ditemukan.");
            }
        }
        return null; // Return null jika kategori tidak ditemukan
    }

    public Category getCategoryById(int categoryId) {
        try {
            // Memanggil metode getCategory
            return getCategory(categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Jika terjadi error, kembalikan null
        }
    }
}
