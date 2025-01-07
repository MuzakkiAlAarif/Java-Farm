/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllerAdmin;

import Class.Category;
import Class.Payment;
import Class.Product;
import Class.Shipping;
import DAO.ProductDAO;
import com.mycompany.java_farm.Connector;
import com.mycompany.java_farm.PaymentDAO;
import com.mycompany.java_farm.ShippingDAO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author fikrs
 */
public class PaymentshippingController implements Initializable {

@FXML
    private Button AddJasa;

    @FXML
    private Button AddPembayaran;

    @FXML
    private Button DeleteJasa;

    @FXML
    private Button DeletePembayaran;

    @FXML
    private ImageView Exit;

    @FXML
    private Button Logoutbtn;

    @FXML
    private TextField NamaJasa;

    @FXML
    private TextField NamaPembayaran;

    @FXML
    private Button Orderinbtn;

    @FXML
    private Button Paymentshippingbtn;

    @FXML
    private Button Profiladmin1btn;

    @FXML
    private Button Profiladminbtn;

    @FXML
    private Button Stockbtn;

    @FXML
    private TableColumn<Shipping, String> Tbl_NamaJasa;

    @FXML
    private TextField TipeJasa;

    @FXML
    private TextField TipePembayaran;

    @FXML
    private TableColumn<Payment, String> tbl_NamaPembayaran;
    
    @FXML
    private TableColumn<Payment, String> tbl_PaymentID;
    
    @FXML
    private TableColumn<Shipping, String> tbl_ShippingId;

    @FXML
    private TableColumn<Shipping, String> tbl_TipeJasa;

    @FXML
    private TableColumn<Payment, String> tbl_TipePembayaran;
    
    @FXML
    private TableView<Payment> tablePayment;
   
    @FXML
    private TableView<Shipping> TableShipping;

    
    @FXML
    private void handleNextButtonProfilAdmin(ActionEvent event) throws IOException {
       URL url = new File("src/main/java/viewAdmin/Profiladmin.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
 @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inisialisasi kolom tabel dengan PropertyValueFactory
        tbl_TipePembayaran.setCellValueFactory(new PropertyValueFactory<>("tipePayment"));
        tbl_NamaPembayaran.setCellValueFactory(new PropertyValueFactory<>("namaPayment"));
        tbl_PaymentID.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        
        tbl_TipeJasa.setCellValueFactory(new PropertyValueFactory<>("tipeJasa"));
        Tbl_NamaJasa.setCellValueFactory(new PropertyValueFactory<>("namaJasa"));
        tbl_ShippingId.setCellValueFactory(new PropertyValueFactory<>("shippingID"));

        // Muat data awal tabel
        loadTableData();
    }

    @FXML
    private void loadTableData() {
        try (Connection connection = Connector.getConnection()) {
            PaymentDAO paymentDAO = new PaymentDAO(connection);
            ShippingDAO shippingDAO = new ShippingDAO(connection);
            
            List<Payment> payments = paymentDAO.getAllPayments();
            List<Shipping> shipping = shippingDAO.getAllShippings();

            // Konversi daftar ke ObservableList untuk sinkronisasi tabel
            ObservableList<Payment> paymentData = FXCollections.observableArrayList(payments);
            ObservableList<Shipping> shippingData = FXCollections.observableArrayList(shipping);
            
            tablePayment.setItems(paymentData);
            TableShipping.setItems(shippingData);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Kesalahan", "Gagal memuat data: " + e.getMessage());
        }
    }
    
   @FXML
    void handleNextButtonAddPembayaran(ActionEvent event) {
        String tipePayment = TipePembayaran.getText();
        String namaPayment = NamaPembayaran.getText();

        if (tipePayment.isEmpty() || namaPayment.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Data tidak lengkap. Pastikan semua bidang terisi.");
            return;
        }

        try (Connection connection = Connector.getConnection()) {
            PaymentDAO paymentDAO = new PaymentDAO(connection);

            Payment payment = new Payment(0, tipePayment, namaPayment); // 0 digunakan sebagai placeholder untuk PaymentID
            paymentDAO.addPayment(payment);

            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Pembayaran berhasil ditambahkan.");

            loadTableData(); // Muat ulang tabel
            clearFields();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Kesalahan", "Gagal menyimpan data ke database: " + e.getMessage());
        }
    }

    @FXML
    void handleNextButtonDeletePembayaran(ActionEvent event) {
    // Ambil item yang dipilih dari tabel
    Payment selectedPayment = tablePayment.getSelectionModel().getSelectedItem();

    if (selectedPayment == null) {
        showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih pembayaran yang ingin dihapus.");
        return;
    }

    // Konfirmasi penghapusan
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Konfirmasi Hapus");
    alert.setHeaderText("Anda yakin ingin menghapus pembayaran ini?");
    alert.setContentText("PaymentID: " + selectedPayment.getPaymentID());

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        try (Connection connection = Connector.getConnection()) {
            PaymentDAO paymentDAO = new PaymentDAO(connection);
            paymentDAO.deletePayment(selectedPayment.getPaymentID());

            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Pembayaran berhasil dihapus.");
            loadTableData(); // Memuat ulang data tabel
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Kesalahan", "Gagal menghapus data: " + e.getMessage());
        }
    }
 
}
    private void populateFields(Payment payment, Shipping shipping) {
    // Mengisi field berdasarkan data Payment
    TipePembayaran.setText(payment.getTipePayment());
    NamaPembayaran.setText(payment.getNamaPayment());
    TipeJasa.setText(shipping.getTipeJasa());
    NamaJasa.setText(shipping.getNamaJasa());
}

    private void clearFields() {
            // Membersihkan field input
            TipePembayaran.clear();
            NamaPembayaran.clear();
            TipeJasa.clear();
            NamaJasa.clear();
        }
    
    
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    void handleNextButtonAddJasa(ActionEvent event) {
        String tipeJasa = TipeJasa.getText();
        String namaJasa = NamaJasa.getText();

        if (tipeJasa.isEmpty() || namaJasa.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Data tidak lengkap. Pastikan semua bidang terisi.");
            return;
        }

        try (Connection connection = Connector.getConnection()) {
            ShippingDAO shippingDAO = new ShippingDAO(connection);

            Shipping shipping = new Shipping(0, tipeJasa, namaJasa); // 0 digunakan sebagai placeholder untuk PaymentID
            shippingDAO.addShipping(shipping);

            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Pembayaran berhasil ditambahkan.");

            loadTableData(); // Muat ulang tabel
            clearFields();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Kesalahan", "Gagal menyimpan data ke database: " + e.getMessage());
        }
    }
    
     @FXML
    void handleNextButtonDeleteJasa(ActionEvent event) {
    // Ambil item yang dipilih dari tabel
    Shipping selectedShipping = TableShipping.getSelectionModel().getSelectedItem();

    if (selectedShipping == null) {
        showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih pembayaran yang ingin dihapus.");
        return;
    }

    // Konfirmasi penghapusan
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Konfirmasi Hapus");
    alert.setHeaderText("Anda yakin ingin menghapus pembayaran ini?");
    alert.setContentText("PaymentID: " + selectedShipping.getShippingID());

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        try (Connection connection = Connector.getConnection()) {
            ShippingDAO shippingDAO = new ShippingDAO(connection);
            shippingDAO.deleteShipping(selectedShipping.getShippingID());

            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Pembayaran berhasil dihapus.");
            loadTableData(); // Memuat ulang data tabel
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Kesalahan", "Gagal menghapus data: " + e.getMessage());
        }
    }
 
}
    
    @FXML
    private void handleNextButtonOrderin(ActionEvent event) throws IOException {
       
        URL url = new File("src/main/java/viewAdmin/Orderin.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
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
    private void handleNextButtonStockin(ActionEvent event) throws IOException {
       URL url = new File("src/main/java/viewAdmin/Stockadmin.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    

    
//    void handleUploadButton(ActionEvent event) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Pilih Gambar");
//        
//        // Filter file hanya untuk gambar
//        fileChooser.getExtensionFilters().addAll(
//            new FileChooser.ExtensionFilter("Gambar", "*.png", "*.jpg", "*.jpeg")
//        );
//
//        // Buka file manager
//        File selectedFile = fileChooser.showOpenDialog(null);
//
//        if (selectedFile != null) {
//            Image image = new Image(selectedFile.toURI().toString());
//            Imageadmin.setImage(image);
//        }
//    }

    
    @FXML
    public void handleClose(MouseEvent event) {
        // Mendapatkan Stage dan menutupnya
        Stage stage = (Stage) Exit.getScene().getWindow();
        stage.close();
    }
    
    

}

