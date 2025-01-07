package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import DAO.UserDAO;
import com.mycompany.java_farm.Connector;

public class LoginController implements Initializable {

    @FXML
    private TextField tf1;

    @FXML
    private PasswordField tf2;

    @FXML
    private Button tf3;

    @FXML
    private Hyperlink Loginkuy;

    @FXML
    private Button tf4;

    @FXML
    private ImageView Exit;

    @FXML
    void fullScreen(MouseDragEvent event) {

    }

    @FXML
    private void handleButtonActionshop(ActionEvent event) throws IOException {
        String email = tf1.getText().trim();
        String password = tf2.getText().trim();

        // Menambahkan log debugging
        System.out.println("Proses login dimulai...");
        System.out.println("Email yang dimasukkan: " + email);
        System.out.println("Password yang dimasukkan: " + password);

        if (email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Ups, sepertinya email atau password belum diisi :)");
        } else if (password.length() < 8) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Password minimal harus 8 karakter!");
        } else if (!email.contains("@gmail.com")) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Email harus menggunakan domain ->@gmail.com<-");
        } else {
            try (Connection connection = Connector.getConnection()) {
                System.out.println("Koneksi ke database berhasil.");

                UserDAO userDAO = new UserDAO(connection);
                boolean isValidUser = userDAO.verifyUser(email, password);

                // Menambahkan log debugging
                System.out.println("Hasil verifikasi pengguna: " + (isValidUser ? "Valid" : "Tidak valid"));

                if (isValidUser) {
                    System.out.println("Pengguna berhasil diverifikasi. Menyimpan email pengguna...");
                    userDAO.setCurrentUserEmail(email); // Simpan email pengguna
                    showAlert(Alert.AlertType.INFORMATION, "Informasi", "Login berhasil!");

                    System.out.println("Membuka halaman utama...");
                    URL url = new File("src/main/java/view/Javashop.fxml").toURI().toURL();
                    Parent root = FXMLLoader.load(url);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) tf3.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Email atau password salah. Silakan coba lagi.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Kesalahan SQL: " + e.getMessage());
                showAlert(Alert.AlertType.ERROR, "Kesalahan", "Terjadi kesalahan saat menghubungkan ke database.");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Kesalahan tak terduga: " + e.getMessage());
                showAlert(Alert.AlertType.ERROR, "Kesalahan", "Terjadi kesalahan tak terduga.");
            }
        }
    }

    @FXML
    private void handleHyperlinkActionRegist(ActionEvent event) throws IOException {
        System.out.println("Navigasi ke halaman registrasi...");
        URL url = new File("src/main/java/view/Registnow.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleButtonActionMenuLog(ActionEvent event) throws IOException {
        System.out.println("Navigasi ke halaman menu login...");
        URL url = new File("src/main/java/view/Menulog.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleClose(MouseEvent event) {
        System.out.println("Aplikasi ditutup.");
        Stage stage = (Stage) Exit.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("LoginController diinisialisasi.");
    }
}
