package com.mycompany.java_farm;

import Class.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
    private Connection connection;

    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }

    // Menambahkan Payment baru
    public void addPayment(Payment payment) throws SQLException {
        String query = "INSERT INTO payment (TipePayment, NamaPayment) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, payment.getTipePayment());
            stmt.setString(2, payment.getNamaPayment());
            stmt.executeUpdate();
        }
    }

    // Mengambil semua data Payment
    public List<Payment> getAllPayments() throws SQLException {
        String query = "SELECT * FROM payment";
        List<Payment> payments = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int paymentID = rs.getInt("PaymentID");
                String tipePayment = rs.getString("TipePayment");
                String namaPayment = rs.getString("NamaPayment");

                payments.add(new Payment(paymentID, tipePayment, namaPayment));
            }
        }

        return payments;
    }

    
    public Payment getPayment(int paymentId) throws SQLException {
        String query = "SELECT * FROM product WHERE productId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, paymentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Payment(
                    rs.getInt("paymentId"),   // ID Produk (huruf kecil)
                    rs.getString("tipePayemnet"), // Nama produk (huruf kecil)
                    rs.getString("namaPayment") // Nama produk (huruf kecil)
                    
                );
            }
        }
        return null;
    }

   public List<Payment> getAllPayment() throws SQLException {
    String query = "SELECT * FROM payment"; // Pastikan tabel yang benar digunakan
    List<Payment> payment = new ArrayList<>(); // Variabel yang dideklarasikan adalah `payment`
    try (Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        while (rs.next()) {
            payment.add(new Payment( // Menggunakan `payment` sesuai deklarasi
                rs.getInt("paymentId"),   // ID Payment
                rs.getString("tipePayment"),  // Tipe Payment
                rs.getString("namaPayment") // Nama Payment
            ));
        }
    }
    return payment; // Mengembalikan `payment`
}

    
    // Method untuk menghapus Payment berdasarkan TipePayment dan NamaPayment
    public void deletePayment(int paymentId) throws SQLException {
    // Query disesuaikan dengan kolom yang ada di tabel
    String query = "DELETE FROM payment WHERE paymentId = ?"; 
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, paymentId); // Mengatur parameter ID
        stmt.executeUpdate(); // Menjalankan perintah SQL
    }
}

}