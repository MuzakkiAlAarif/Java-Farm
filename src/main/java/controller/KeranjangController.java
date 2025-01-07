package controller;  
  
import Class.Product;  
import DAO.ProductDAO;  
import com.mycompany.java_farm.Connector;  
import java.io.File;  
import java.io.IOException;  
import java.net.URL;  
import java.sql.Connection;  
import java.sql.SQLException;  
import java.util.List;  
import java.util.Optional;  
import javafx.event.ActionEvent;  
import javafx.fxml.FXML;  
import javafx.fxml.FXMLLoader;  
import javafx.scene.Node;  
import javafx.scene.Parent;  
import javafx.scene.Scene;  
import javafx.scene.control.Button;  
import javafx.scene.control.CheckBox;  
import javafx.scene.control.Label;  
import javafx.scene.image.ImageView;  
import javafx.scene.input.MouseEvent;  
import javafx.scene.image.Image;  
import javafx.scene.control.Alert;  
import javafx.scene.control.ButtonType;  
import javafx.scene.control.ListView;  
import javafx.stage.Stage;  
  
public class KeranjangController {  
  
    // Button di UI  
    @FXML  
    private Button Profilbtn;  
  
    @FXML  
    private Button Profilbtn1;  
  
    @FXML  
    private Button Berandabtn;  
  
    @FXML  
    private Button Logoutbtn;  
  
    @FXML  
    private Button Keranjangbtn;  
  
    @FXML  
    private Button Paymentbtn;  
  
    @FXML  
    private Button Orderbtn;  
  
    @FXML  
    private Button Otworder;  
  
    @FXML  
    private Button Otwpay;  
  
    @FXML  
    private ImageView Exit;  
  
    // Cekbox di UI  
    @FXML  
    private CheckBox selectAll;  
    @FXML  
    private CheckBox item1;  
    @FXML  
    private CheckBox item2;  
    @FXML  
    private CheckBox item3;  
    @FXML  
    private CheckBox item4;  
  
    // Label kuantitas  
    @FXML  
    private Label quantityLabels1;  
  
    @FXML  
    private Label quantityLabels2;  
  
    // Tombol tambah kurang 1  
    @FXML  
    private Button tambahbtn1;  
    @FXML  
    private Button kurangibtn1;  
  
    // Tombol tambah kurang 2  
    @FXML  
    private Button tambahbtn2;  
    @FXML  
    private Button kurangibtn2;  
  
    @FXML  
    private ListView<Product> productListView; // ListView untuk menampilkan produk  
  
    // Hasil  
    private int counter1 = 0;  
    private int counter2 = 0;  
  
    // Tambahkan variabel untuk ProductDAO dan produk  
    private ProductDAO productDAO;  
    private Product currentProduct;  
    private JavashopController javashopController; // Referensi ke JavashopController  
  
    // Label untuk menampilkan detail produk  
    @FXML  
    private Label productNama; // Label untuk nama produk  
    @FXML  
    private Label productHarga; // Label untuk harga produk  
    @FXML  
    private Label productStock; // Label untuk stok produk  
    @FXML  
    private ImageView imageview; // ImageView untuk menampilkan gambar produk  
  
    public void setJavashopController(JavashopController javashopController) {  
        this.javashopController = javashopController; // Menghubungkan controller  
        System.out.println("JavashopController set in KeranjangController: " + (javashopController != null));  
    }  
  
     @FXML  
    public void initialize() {  
        System.out.println("Initializing KeranjangController...");  
        try {  
            Connection connector = Connector.getConnection(); // Ambil koneksi dari Connector  
            productDAO = new ProductDAO(connector); // Kirim koneksi ke ProductDAO  
            System.out.println("KeranjangController initialized with ProductDAO.");  
        } catch (SQLException e) {  
            e.printStackTrace();  
            showAlert("Error", "Gagal menginisialisasi koneksi database.");  
            return;  
        }  
  
        if (javashopController == null) {  
            System.err.println("Error: javashopController is null!");  
            showAlert("Error", "Controller tidak terhubung dengan benar.");  
            return;  
        } else {  
            System.out.println("javashopController is set correctly.");  
        }  
    }  
  
    @FXML  
    void handleTambahButton1(ActionEvent event) {  
        counter1++;  
        quantityLabels1.setText("" + counter1);  
        System.out.println("Counter 1 incremented: " + counter1);  
    }  
  
    @FXML  
    void handleKurangButton1(ActionEvent event) {  
        if (counter1 > 0) {  
            counter1--;  
            quantityLabels1.setText("" + counter1);  
            System.out.println("Counter 1 decremented: " + counter1);  
        }  
    }  
  
    @FXML  
    void handleTambahButton2(ActionEvent event) {  
        counter2++;  
        quantityLabels2.setText("" + counter2);  
        System.out.println("Counter 2 incremented: " + counter2);  
    }  
  
    @FXML  
    void handleKurangButton2(ActionEvent event) {  
        if (counter2 > 0) {  
            counter2--;  
            quantityLabels2.setText("" + counter2);  
            System.out.println("Counter 2 decremented: " + counter2);  
        }  
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
    private void handleNextButtonBeranda(ActionEvent event) throws IOException {  
        URL url = new File("src/main/java/view/Javashop.fxml").toURI().toURL();  
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
            URL url = new File("src/main/java/view/Menulog.fxml").toURI().toURL();  
            Parent root = FXMLLoader.load(url);  
            Scene scene = new Scene(root);  
  
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
            stage.setScene(scene);  
            stage.show();  
        } else {  
            alert.close();  
        }  
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
  
    @FXML  
    private void handleNextButtonOrder(ActionEvent event) throws IOException {  
        URL url = new File("src/main/java/view/Orderpay.fxml").toURI().toURL();  
        Parent root = FXMLLoader.load(url);  
        Scene scene = new Scene(root);  
  
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
        stage.setScene(scene);  
        stage.show();  
    }  
  
    @FXML  
    private void handleNextButtonOrdernow(ActionEvent event) throws IOException {  
        URL url = new File("src/main/java/view/Orderpay.fxml").toURI().toURL();  
        Parent root = FXMLLoader.load(url);  
        Scene scene = new Scene(root);  
  
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
        stage.setScene(scene);  
        stage.show();  
    }  
  
    @FXML  
    private void handleNextButtonPaynow(ActionEvent event) throws IOException {  
        URL url = new File("src/main/java/view/Payment.fxml").toURI().toURL();  
        Parent root = FXMLLoader.load(url);  
        Scene scene = new Scene(root);  
  
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
        stage.setScene(scene);  
        stage.show();  
    }  
  
    @FXML  
    void searchbar(ActionEvent event) {  
        // Implementasi pencarian jika diperlukan  
    }  
  
    @FXML  
    public void handleClose(MouseEvent event) {  
        Stage stage = (Stage) Exit.getScene().getWindow();  
        stage.close();  
    }  
  
    private void showAlert(String title, String message) {  
        Alert alert = new Alert(Alert.AlertType.INFORMATION);  
        alert.setTitle(title);  
        alert.setHeaderText(null);  
        alert.setContentText(message);  
        alert.showAndWait();  
    }  
  
    private void displayProductDetails(Product newValue) {  
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody  
    }  

    void updateProductListView() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    List<Product> getCartProducts() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}  
