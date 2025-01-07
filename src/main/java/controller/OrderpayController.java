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
//import javafx.beans.binding.Bindings;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fikrs
 */
public class OrderpayController implements Initializable {
    
    @FXML
    private Button Berandabtn;
    
    @FXML
    private Button Profilbtn;
    
    @FXML
    private Button Profilbtn1;
    
    @FXML
    private Button Keranjangbtn;
    
    @FXML
    private Button Paymentbtn;
    
    @FXML
    private Button Orderbtn;
    
    @FXML
    private Button Logoutbtn;
    
    @FXML
    private TableView Data;
    
    @FXML
    private Button Ordered;
    
    @FXML
    private SplitMenuButton Pengiriman;
    
    @FXML
    private ImageView Exit;
    
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private void handleNextButtonBeranda(ActionEvent event) throws IOException {
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
    private void handleNextButtonPayment(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/view/Payment.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
//    @FXML
//    private void handleNextButtonOrder(ActionEvent event) throws IOException {
// 
//        Parent root = FXMLLoader.load(getClass().getResource("../view/Orderpay.fxml"));
//
//        
//        Scene scene = new Scene(root);
//
//        Stage stage = (Stage) Orderbtn.getScene().getWindow();
//
//       
//        stage.setScene(scene);
//        stage.show();
//    }
    
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
    
    @FXML
    private void handleNextButtonKeranjang(ActionEvent event) throws IOException {

        URL url = new File("src/main/java/view/Keranjang.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void onOrderedClicked() {
    if (Data.getItems().isEmpty()) {
        // Menampilkan pemberitahuan jika tabel kosong
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pemberitahuan");
        alert.setHeaderText(null);
        alert.setContentText("Anda belum memesan apa-apa.");
        alert.showAndWait();
    } else {
        // Logika untuk memproses jika tabel tidak kosong
        System.out.println("Memproses pesanan...");
        // Tambahkan kode pemrosesan pesenan
    }
}
    
    @FXML
    public void handleClose(MouseEvent event) {
        // Mendapatkan Stage dan menutupnya
        Stage stage = (Stage) Exit.getScene().getWindow();
        stage.close();
    }
    
    private void handleShippingOption(String option) {
    // Mengubah teks SplitMenuButton ke opsi yang dipilih
    Pengiriman.setText(option);
    System.out.println("Pilihan pengiriman: " + option);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    // Membuat menu item
    MenuItem jneOption = new MenuItem("JNE");
    MenuItem jntOption = new MenuItem("JNT");
    MenuItem antarAjaOption = new MenuItem("AntarAja");

    // Menambahkan menu item ke dalam SplitMenuButton
    Pengiriman.getItems().addAll(jneOption, jntOption, antarAjaOption);

    // Menambahkan event handler untuk setiap opsi
    jneOption.setOnAction(event -> handleShippingOption("JNE"));
    jntOption.setOnAction(event -> handleShippingOption("JNT"));
    antarAjaOption.setOnAction(event -> handleShippingOption("AntarAja"));
    }     
    
    private int quantity1;
    private int quantity2;

    public void setQuantities(int quantity1, int quantity2) {
    this.quantity1 = quantity1;
    this.quantity2 = quantity2;
    updateQuantitiesOnUI();
}

    private void updateQuantitiesOnUI() {
    // Misalnya ada Label untuk menampilkan kuantitas
    // Tambahkan logika untuk memperbarui UI
    System.out.println("Quantity 1: " + quantity1 + ", Quantity 2: " + quantity2);
}

}


