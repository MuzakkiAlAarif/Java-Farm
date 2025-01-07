package controller;

import Class.Pengguna;
import DAO.UserDAO;
import Class.User;
import com.mycompany.java_farm.Connector;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ProfilController implements Initializable {

    @FXML
    private Button Logoutbtn;

    @FXML
    private Button Berandabtn;

    @FXML
    private ImageView Exit;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label roleLabel;

    private UserDAO userDAO;

    @FXML
    private Button Paymentbtn;

    @FXML
    private Button Keranjangbtn;

    @FXML
    private Button Orderbtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inisialisasi koneksi database menggunakan Connector
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            System.out.println("Koneksi ke database berhasil.");
            userDAO = new UserDAO(connection); // Inisialisasi UserDAO dengan koneksi
            
            String email = userDAO.getCurrentUserEmail(); // Ambil email dari sesi pengguna
            System.out.println("Email pengguna yang diambil: " + email);
            
            if (email == null || email.isEmpty()) {
                System.out.println("Tidak ada pengguna yang sedang login.");
                showAlert("Error", "Tidak ada pengguna yang sedang login.");
                return; // Keluar dari metode jika tidak ada pengguna yang login
            }

            User user = userDAO.getUserByEmail(email); // Ambil user berdasarkan email
            System.out.println("Mencoba mengambil data pengguna dengan email: " + email);

            if (user != null) {
                // Menampilkan data user ke UI
                displayUserDetails(user);
            } else {
                System.out.println("User tidak ditemukan.");
                showAlert("Error", "User tidak ditemukan.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
            showAlert("Error", "Gagal memuat data pengguna.");
        }
    }

    private void displayUserDetails(User user) {
        System.out.println("Menampilkan detail pengguna:");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Role: " + user.getRole());

        usernameLabel.setText(user.getUsername());
        emailLabel.setText(user.getEmail());
        roleLabel.setText(user.getRole()); // Menampilkan role pengguna
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleNextButtonBeranda(ActionEvent event) throws IOException {
        System.out.println("Navigating to Beranda...");
        URL url = new File("src/main/java/view/Javashop.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleNextButtonKeranjang(ActionEvent event) throws IOException {
        System.out.println("Navigating to Keranjang...");
        URL url = new File("src/main/java/view/Keranjang.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleNextButtonPayment(ActionEvent event) throws IOException {
        System.out.println("Navigating to Payment...");
        URL url = new File("src/main/java/view/Payment.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleNextButtonOrder(ActionEvent event) throws IOException {
        System.out.println("Navigating to Order...");
        URL url = new File("src/main/java/view/Orderpay.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleNextButtonLogout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Konfirmasi Logout");
        alert.setHeaderText("Kakak yakin mau keluar?");
        alert.setContentText("Pilih \"Iya nih\" untuk melanjutkan atau \"Ngga jadi\" untuk tetap di halaman ini.");

        ButtonType iyaNih = new ButtonType("Iya nih");
        ButtonType nggaJadi = new ButtonType("Ngga jadi");

        alert.getButtonTypes().setAll(iyaNih, nggaJadi);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == iyaNih) {
            System.out.println("User memilih untuk logout.");
            URL url = new File("src/main/java/view/Menulog.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("User membatalkan logout.");
            alert.close();
        }
    }

    @FXML
    public void handleClose(MouseEvent event) {
        System.out.println("Menutup aplikasi.");
        Stage stage = (Stage) Exit.getScene().getWindow();
        stage.close();
    }
}
