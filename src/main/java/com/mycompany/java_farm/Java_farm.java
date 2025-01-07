package com.mycompany.java_farm;

import Class.Admin;
import Class.Pengguna;
import Class.User;
import DAO.CategoryDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Java_farm {
    // Hapus main() di sini karena ini akan dikelola oleh mainUi.java

    public static void runFarmApp() {
        try (Connection connector = Connector.getConnection()) {
            System.out.println("Database connected successfully.");

            Scanner scanner = new Scanner(System.in);
            UserDAO userDAO = new UserDAO(connector);
            CategoryDAO categoryDAO = new CategoryDAO(connector);
            ProductDAO productDAO = new ProductDAO(connector);

            boolean running = true;
            while (running) {
                System.out.println("\n=== Selamat Datang di Java Farm ===");
                System.out.println("1. Akses sebagai Admin");
                System.out.println("2. Akses sebagai User");
                System.out.println("3. Keluar");
                System.out.print("Pilih opsi: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Membersihkan input newline

                switch (choice) {
                    case 1 -> adminLogin(scanner, userDAO, categoryDAO, productDAO);
                    case 2 -> userAccess(scanner, userDAO, productDAO);
                    case 3 -> {
                        running = false;
                        System.out.println("Keluar dari aplikasi...");
                    }
                    default -> System.out.println("Pilihan tidak valid. Coba lagi.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database connection failed.");
        }
    }

    private static void adminLogin(Scanner scanner, UserDAO userDAO, CategoryDAO categoryDAO, ProductDAO productDAO) {
        System.out.print("Masukkan email admin: ");
        String email = scanner.nextLine();
        System.out.print("Masukkan kata sandi admin: ");
        String password = scanner.nextLine();

        User user = userDAO.getUserByEmailAndPassword(email, password);
        if (user instanceof Admin) {
            System.out.println("Login sebagai Admin berhasil.");
            adminMenu(scanner, categoryDAO, productDAO);
        } else {
            System.out.println("Email atau kata sandi salah, atau Anda bukan Admin.");
        }
    }

    private static void userAccess(Scanner scanner, UserDAO userDAO, ProductDAO productDAO) {
        boolean userMenuRunning = true;
        while (userMenuRunning) {
            System.out.println("\n=== Akses User ===");
            System.out.println("1. Login");
            System.out.println("2. Registrasi");
            System.out.println("3. Kembali ke Menu Utama");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Membersihkan input newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Masukkan email: ");
                    String email = scanner.nextLine();
                    System.out.print("Masukkan kata sandi: ");
                    String password = scanner.nextLine();

                    User user = userDAO.getUserByEmailAndPassword(email, password);
                    if (user instanceof Pengguna) {
                        System.out.println("Login berhasil. Selamat datang, " + user.getUsername() + "!");
                        userMenu(scanner, productDAO);
                    } else {
                        System.out.println("Email atau kata sandi salah.");
                    }
                }
                case 2 -> {
                    System.out.print("Masukkan nama pengguna: ");
                    String username = scanner.nextLine();
                    System.out.print("Masukkan email: ");
                    String email = scanner.nextLine();
                    System.out.print("Masukkan kata sandi: ");
                    String password = scanner.nextLine();

                    boolean success = userDAO.registerUser(username, email, password);
                    if (success) {
                        System.out.println("Registrasi berhasil! Silakan login.");
                    } else {
                        System.out.println("Registrasi gagal. Email mungkin sudah digunakan.");
                    }
                }
                case 3 -> userMenuRunning = false;
                default -> System.out.println("Pilihan tidak valid. Coba lagi.");
            }
        }
    }

    private static void adminMenu(Scanner scanner, CategoryDAO categoryDAO, ProductDAO productDAO) {
        boolean adminRunning = true;
        while (adminRunning) {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Manajemen Pengguna");
            System.out.println("2. Manajemen Kategori");
            System.out.println("3. Manajemen Produk");
            System.out.println("4. Keluar");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> System.out.println("Fitur Manajemen Pengguna akan segera tersedia...");
                case 2 -> System.out.println("Fitur Manajemen Kategori akan segera tersedia...");
                case 3 -> System.out.println("Fitur Manajemen Produk akan segera tersedia...");
                case 4 -> {
                    adminRunning = false;
                    System.out.println("Keluar dari menu admin...");
                }
                default -> System.out.println("Pilihan tidak valid. Coba lagi.");
            }
        }
    }

    private static void userMenu(Scanner scanner, ProductDAO productDAO) {
        boolean userRunning = true;
        while (userRunning) {
            System.out.println("\n=== Menu User ===");
            System.out.println("1. Lihat Katalog Produk");
            System.out.println("2. Tambah ke Keranjang");
            System.out.println("3. Lihat Riwayat Pesanan");
            System.out.println("4. Keluar");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> System.out.println("Fitur Lihat Katalog akan segera tersedia...");
                case 2 -> System.out.println("Fitur Tambah ke Keranjang akan segera tersedia...");
                case 3 -> System.out.println("Fitur Riwayat Pesanan akan segera tersedia...");
                case 4 -> {
                    userRunning = false;
                    System.out.println("Keluar dari menu user...");
                }
                default -> System.out.println("Pilihan tidak valid. Coba lagi.");
            }
        }
    }
}
