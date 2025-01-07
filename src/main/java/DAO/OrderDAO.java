/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.*;
import Class.Order;

/**
 *
 * @author USER
 */

public class OrderDAO {
    private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    public void createOrder(Order order) throws SQLException {
        String query = "INSERT INTO Order (userId, totalPrice, orderDate) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getUser().getUserId());
            stmt.setDouble(2, order.getTotalPrice());
            stmt.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                order.setOrderId(keys.getInt(1));
            }
        }
    }

    // Additional methods for retrieving and updating order data
}
