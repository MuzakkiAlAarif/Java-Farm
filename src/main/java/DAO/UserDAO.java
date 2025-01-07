package DAO;

import Class.Admin;
import Class.Pengguna;
import Class.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private final Connection connection;
    private static String currentUserEmail; // Variabel statis untuk menyimpan email pengguna yang sedang login

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // Mendapatkan pengguna berdasarkan email dan password
    public User getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM User WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);

            System.out.println("Menjalankan query untuk email: " + email); // Debugging
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("userId");
                String username = rs.getString("username");
                String role = rs.getString("role");

                // Simpan email pengguna yang berhasil login
                currentUserEmail = email;
                System.out.println("User ditemukan: " + username + " dengan role: " + role); // Debugging

                // Mengembalikan objek sesuai dengan tipe pengguna
                if ("admin".equalsIgnoreCase(role)) {
                    return new Admin(id, username, password, email, role);
                } else if ("pengguna".equalsIgnoreCase(role)) {
                    return new Pengguna(id, username, password, email, role);
                }
            } else {
                System.out.println("Tidak ada pengguna ditemukan dengan email: " + email); // Debugging
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Registrasi pengguna baru
    public boolean registerUser(String username, String email, String password) {
        String sql = "INSERT INTO User (username, email, password, role) VALUES (?, ?, ?, 'pengguna')";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Verifikasi pengguna
    public boolean verifyUser(String email, String password) {
        String sql = "SELECT COUNT(*) AS userCount FROM User WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int userCount = rs.getInt("userCount");
                return userCount > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean registerAdmin(String username, String email, String password) {
        String sql = "INSERT INTO User (username, email, password, role) VALUES (?, ?, ?, 'admin')";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Metode untuk verifikasi login admin
    public boolean verifyAdminLogin(String email, String password) {
        String sql = "SELECT COUNT(*) AS adminCount FROM User WHERE email = ? AND password = ? AND role = 'admin'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int adminCount = rs.getInt("adminCount");
                return adminCount > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean editUser(int userId, String username, String image, String bioData, String gender, Integer phoneNumber) {
        String sql = "UPDATE User SET username = ?, image = ?, bioData = ?, gender = ?, phoneNumber = ? WHERE userId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, image);
            stmt.setString(3, bioData);
            stmt.setString(4, gender);
            stmt.setInt(5, phoneNumber);
            stmt.setInt(6, userId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByEmail(String email) {
        User user = null;
        String sql = "SELECT * FROM User WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("userId");
                String username = rs.getString("username");
                String role = rs.getString("role");
                user = new User(id, username, "", email, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String getCurrentUserEmail() {
        if (currentUserEmail == null || currentUserEmail.isEmpty()) {
            System.out.println("Tidak ada pengguna yang sedang login."); // Debugging
        }
        return currentUserEmail;
    }

    public void setCurrentUserEmail(String email) {
        currentUserEmail = email; // Simpan email pengguna yang sedang login
    }
}
