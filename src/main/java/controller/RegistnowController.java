package controller;

import DAO.UserDAO;
import com.mycompany.java_farm.Connector;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;




/**
 *
 * @author fikrs
 */
public class RegistnowController implements Initializable {
    
//   @FXML
//   private AnchorPane root;
    
    @FXML
    private TextField Inuser;
    
    @FXML
    private TextField Inemail;
    
    @FXML
    private PasswordField Inpw;
    
    @FXML
    private Button Inreg;
    
    @FXML
    private Hyperlink Regisyu;
    
    @FXML
    private Button Backbtn;
    
    @FXML 
    private ImageView Exit;
    
    @FXML
    void fullScreen(MouseDragEvent event) {

    }
    
    
       @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        String username = Inuser.getText();
        String email = Inemail.getText();
        String password = Inpw.getText();

        // Validasi input
        if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Data tidak lengkap. Pastikan semua bidang terisi.");
        } else if (password.length() < 8) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Password minimal harus 8 karakter!");
        } else if (username.length() < 4) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Username tidak boleh kurang dari 4 karakter!");
        } else if (!email.contains("@gmail.com")) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Email harus menggunakan domain @gmail.com");
        } else {
            // Registrasi ke database
            try (Connection connection = Connector.getConnection()) {
                UserDAO userDAO = new UserDAO(connection);
                boolean isRegistered = userDAO.registerUser(username, email, password);

                if (isRegistered) {
                    // Jika registrasi berhasil
                    showAlert(Alert.AlertType.INFORMATION, "Sukses", "Registrasi berhasil!");
                    // Pindah ke halaman login
                    navigateTo(event, "Loginnow.fxml");
                    URL url = new File("src/main/java/view/Loginnow.fxml").toURI().toURL();
                    Parent root = FXMLLoader.load(url);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Gagal", "Registrasi gagal. Coba lagi.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Kesalahan", "Terjadi kesalahan saat menghubungkan ke database.");
            }
        }
    }

    @FXML
    private void handleHyperlinkAction(ActionEvent event) throws IOException {
        navigateTo(event, "Loginnow.fxml");
        URL url = new File("src/main/java/view/Loginnow.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
    }

    @FXML
    private void handleButtonActionMenuLog(ActionEvent event) throws IOException {
        navigateTo(event, "Menulog.fxml");
        URL url = new File("src/main/java/view/Menulog.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
    }

    private void navigateTo(ActionEvent event, String fxmlFile) throws IOException {
        URL url = new File("src/main/java/view/" + fxmlFile).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    public void handleClose(MouseEvent event) {
        // Mendapatkan Stage dan menutupnya
        Stage stage = (Stage) Exit.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
