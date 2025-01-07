package controllerAdmin;

import DAO.UserDAO;
import com.mycompany.java_farm.Connector;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
public class LoginadminController implements Initializable {
    
//    @FXML
//    private AnchorPane root;
   
    @FXML
    private TextField tf1;
    
    @FXML
    private PasswordField tf2;
    
    @FXML
    private Button Inbtn;
    
    @FXML
    private Hyperlink Loginkuy;
    
    @FXML
    private Button Backbtn;
    
    @FXML
    private ImageView Exit;
    
    @FXML
    void fullScreen(MouseDragEvent event) {

    }

    
    
    @FXML
    private void handleButtonActionGO(ActionEvent event) {
    String email = tf1.getText();
    String password = tf2.getText();

    if (email.isEmpty() || password.isEmpty()) {
        showAlert(Alert.AlertType.WARNING, "Peringatan", "Ups, sepertinya email atau password belum diisi :)");
    } else if (password.length() < 8) {
        showAlert(Alert.AlertType.WARNING, "Peringatan", "Password minimal harus 8 karakter!");
    } else if (!email.contains("@gmail.com")) {
        showAlert(Alert.AlertType.WARNING, "Peringatan", "Email harus menggunakan domain ->@gmail.com<-");
    } else {
        try (Connection connection = Connector.getConnection()) {
            System.out.println("Koneksi berhasil!");
            UserDAO userDAO = new UserDAO(connection);
            boolean isValidUser = userDAO.verifyAdminLogin(email, password);

            if (isValidUser) {
                showAlert(Alert.AlertType.INFORMATION, "Informasi", "Login berhasil!");

                URL url = new File("src/main/java/viewAdmin/Stockadmin.fxml").toURI().toURL();
                System.out.println("URL file FXML: " + url);
                Parent root = FXMLLoader.load(url);
                Scene scene = new Scene(root);
                Stage stage = (Stage) Inbtn.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Email atau password salah. Silakan coba lagi.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Kesalahan Database", "Terjadi kesalahan saat mengakses database: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Kesalahan IO", "Gagal memuat file FXML: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Kesalahan Tak Terduga", "Error: " + e.getMessage());
        }
    }
}
    
    private void showAlert(Alert.AlertType alertType, String title, String message) {
    Platform.runLater(() -> {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    });
}

    
    @FXML
    private void handleHyperlinkActionRegist(ActionEvent event) throws IOException {
    // Navigasi ke halaman bantuan
    URL url = new File ("src/main/java/viewAdmin/Registadmin.fxml"). toURI().toURL();
    Parent root = FXMLLoader.load(url);    // ... (sisanya sama seperti contoh sebelumnya)

    Scene scene = new Scene(root);

    // Get the stage
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage
    stage.setScene(scene);
    stage.show();
    }
    
    
    @FXML
    private void handleButtonActionBack(ActionEvent event) throws IOException {
    // Navigasi ke halaman bantuan
    URL url = new File ("src/main/java/viewAdmin/Menulogadmin.fxml"). toURI().toURL();
    Parent root = FXMLLoader.load(url);
    // ... (sisanya sama seperti contoh sebelumnya)
    Scene scene = new Scene(root);

    // Get the stage
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Set the scene on the stage
    stage.setScene(scene);
    stage.show();
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
//    public void fullScreen(){
//        Stage stage = (Stage) root.getScene().getWindow();
//
//    // aktifkan full screen
//    stage.setFullScreen(true);
//    stage.setFullScreenExitHint("");
//    
//    //ui mengikuti ukuran elemen
//    root.prefWidthProperty().bind(stage.widthProperty());
//    root.prefHeightProperty().bind(stage.heightProperty());
//    }

    
    
}
