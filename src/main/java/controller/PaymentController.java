/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

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
 * FXML Controller class
 *
 * @author fikrs
 */
public class PaymentController implements Initializable {
    
    @FXML
    private Button Berandabtn;
    
    @FXML
    private Button Keranjangbtn;
    
    @FXML
    private Button Profilbtn;
    
    @FXML
    private Button Profilbtn1;
    
    @FXML
    private Button Paymentbtn;
    
    @FXML
    private Button Orderbtn;
    
    @FXML
    private Button Logoutbtn;
    
    @FXML
    private ImageView Bankbjbbtn;
    
    @FXML
    private ImageView Bankbcabtn;
    
    @FXML
    private ImageView Bankbnibtn;
    
    @FXML
    private ImageView Gopaybtn;
    
    @FXML
    private ImageView Ovobtn;
    
    @FXML
    private ImageView Danabtn;
    
    @FXML
    private ImageView Alfabtn;
    
    @FXML
    private ImageView Indobtn;
    
    @FXML
    private ImageView Exit;
    
    
    @FXML
    void handleNextButtonBeranda(ActionEvent event) throws IOException {
    URL url = new File("src/main/java/view/Javashop.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void handleButtonActionProfil(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/view/Profil.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void handleNextButtonKeranjang(ActionEvent event) throws IOException {
   
        URL url = new File("src/main/java/view/Keranjang.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
//    @FXML
//    void handleNextButtonPayment(ActionEvent event) throws IOException {
//   
//        Parent root = FXMLLoader.load(getClass().getResource("../view/Payment.fxml"));
//
//       
//        Scene scene = new Scene(root);
//
//       
//        Stage stage = (Stage) Paymentbtn.getScene().getWindow();
//
//     
//        stage.setScene(scene);
//        stage.show();
//    }
    
    @FXML
    void handleNextButtonOrder(ActionEvent event) throws IOException {
    
        URL url = new File("src/main/java/view/Orderpay.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleNextButtonLogout(ActionEvent event) throws IOException {
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
        URL url = new File("src/main/java/view/Menulog.fxml").toURI().toURL();
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
    
//    Button bank ke order
    void handleNextButtonBJB(ActionEvent event) throws IOException {
   
        URL url = new File("src/main/java/view/Payment.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    void handleNextButtonBNI(ActionEvent event) throws IOException {
   
        Parent root = FXMLLoader.load(getClass().getResource("../view/Keranjang.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) Bankbnibtn.getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }
    
    void handleNextButtonBCA(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../view/Keranjang.fxml"));


        Scene scene = new Scene(root);


        Stage stage = (Stage) Bankbcabtn.getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }
    
    void handleNextButtonOvo(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../view/Keranjang.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) Ovobtn.getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }
    
    void handleNextButtonGopay(ActionEvent event) throws IOException {
  
        Parent root = FXMLLoader.load(getClass().getResource("../view/Keranjang.fxml"));


        Scene scene = new Scene(root);

        Stage stage = (Stage) Gopaybtn.getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }
    
    void handleNextButtonDana(ActionEvent event) throws IOException {
   
        Parent root = FXMLLoader.load(getClass().getResource("../view/Keranjang.fxml"));

     
        Scene scene = new Scene(root);

        Stage stage = (Stage) Danabtn.getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }
    
    void handleNextButtonIndo(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../view/Keranjang.fxml"));

        Scene scene = new Scene(root);

  
        Stage stage = (Stage) Indobtn.getScene().getWindow();


        stage.setScene(scene);
        stage.show();
    }
    
    void handleNextButtonAlfa(ActionEvent event) throws IOException {
   
        Parent root = FXMLLoader.load(getClass().getResource("../view/Keranjang.fxml"));

        Scene scene = new Scene(root);

    
        Stage stage = (Stage) Alfabtn.getScene().getWindow();

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

//    @FXML
//    private void Alfabtn(MouseEvent event) throws IOException {
//        
//    Parent root = FXMLLoader.load(getClass().getResource("../view/Keranjang.fxml"));
//
//        Scene scene = new Scene(root);
//
//    
//        Stage stage = (Stage) Alfabtn.getScene().getWindow();
//
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    @FXML
//    private void bjbClick(MouseEvent event) {
//    }
//
//    @FXML
//    private void bniClicked(MouseEvent event) {
//    }
//
//    @FXML
//    private void danaClicked(MouseEvent event) {
//    }
//
//    @FXML
//    private void gopayClicked(MouseEvent event) {
//    }
//
//    @FXML
//    private void ovoClick(MouseEvent event) {
//    }
////
//    @FXML
//    private void Indoclick(MouseEvent event) throws IOException {
//    Parent root = FXMLLoader.load(getClass().getResource("../view/Keranjang.fxml"));
//
//        Scene scene = new Scene(root);
//
//  
//        Stage stage = (Stage) Indoclick.getScene().getWindow();
//
//
//        stage.setScene(scene);
//        stage.show();
//    }

//
//    @FXML
//    private void alfaClick(MouseEvent event) {
//    }
   
}
