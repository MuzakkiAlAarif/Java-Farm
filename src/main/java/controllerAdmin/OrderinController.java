/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllerAdmin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML OrderinController class
 *
 * @author fikrs
 */
public class OrderinController implements Initializable {

    @FXML
    private ImageView Exit;
    
    @FXML
    private Button Profiladminbtn;
    
    @FXML
    private Button Profiladmin1btn;
    
    @FXML 
    private Button Stockbtn;
    
    @FXML
    private Button Orderinbtn;
    
    @FXML
    private Button Paymentshippingbtn;
    
    @FXML
    private Button Logoutbtn;
    
    @FXML
    private void handleNextButtonLogout(ActionEvent event) throws IOException {
    // dialog keluar
    Alert alert = new Alert(Alert.AlertType.NONE);
    alert.setTitle("Konfirmasi Logout");
    alert.setHeaderText("Kakak yakin mau keluar?");
    alert.setContentText("Pilih \"Iya nih\" untuk melanjutkan atau \"Ngga jadi\" untuk tetap di halaman ini.");

    // pilih 
    ButtonType iyaNih = new ButtonType("Iya nih");
    ButtonType nggaJadi = new ButtonType("Ngga jadi");

    // nambahin tombol ke dialoog
    alert.getButtonTypes().setAll(iyaNih, nggaJadi);

    // Nunggu respon user
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == iyaNih) {
        // Jika memilih "Iya nih" maka akan ke menu awal
        URL url = new File("src/main/java/viewAdmin/Menulogadmin.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } else {
        // Jika memilih "Ngga jadi" maka dialog tertutup
        alert.close();
    }
    
}
    
    @FXML
    private void handleNextButtonStock(ActionEvent event) throws IOException {
       
        URL url = new File("src/main/java/viewAdmin/Stockadmin.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleNextButtonProfilAdmin(ActionEvent event) throws IOException {
       // Navigasi ke halaman bantuan
    URL url = new File("src/main/java/viewAdmin/Profiladmin.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleNextButtonPayship(ActionEvent event) throws IOException {
      URL url = new File("src/main/java/viewAdmin/Paymentshipping.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleClose(MouseEvent event) {
        // Mendapatkan Stage dan menutupnya
        Stage stage = (Stage) Exit.getScene().getWindow();
        stage.close();
    }
    
}
