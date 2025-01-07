package com.mycompany.java_farm;

import Class.Shipping;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShippingDAO {
    private Connection connection;

    public ShippingDAO(Connection connection) {
        this.connection = connection;
    }

    // Menambahkan Shipping baru
    public void addShipping(Shipping shipping) throws SQLException {
        String query = "INSERT INTO Shipping (TipeJasa, NamaJasa) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, shipping.getTipeJasa());
            stmt.setString(2, shipping.getNamaJasa());
            stmt.executeUpdate();
        }
    }
    
    // Mengambil semua data Shipping
    public List<Shipping> getAllShippings() throws SQLException {
        String query = "SELECT * FROM Shipping";
        List<Shipping> shippings = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int shippingID = rs.getInt("ShippingID");
                String tipeJasa = rs.getString("TipeJasa");
                String namaJasa = rs.getString("NamaJasa");

                shippings.add(new Shipping(shippingID, tipeJasa, namaJasa));
            }
        }

        return shippings;
    }

    // Mengambil satu data Shipping berdasarkan ShippingID
    public Shipping getShipping(int shippingId) throws SQLException {
        String query = "SELECT * FROM Shipping WHERE ShippingID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, shippingId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Shipping(
                    rs.getInt("ShippingID"),
                    rs.getString("TipeJasa"),
                    rs.getString("NamaJasa")
                );
            }
        }
        return null;
    }

    // Menghapus Shipping berdasarkan ShippingID
    public void deleteShipping(int shippingId) throws SQLException {
        String query = "DELETE FROM Shipping WHERE ShippingID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, shippingId);
            stmt.executeUpdate();
        }
    }
}
