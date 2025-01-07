package controllerAdmin;

import DAO.UserDAO;
import com.mycompany.java_farm.Connector;// Import koneksi database
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fikrs
 */
public class ProfiladminController implements Initializable {
    
    
    @FXML
    private TextArea BioData;

    @FXML
    private TextField EditGender;

    @FXML
    private TextField EditNohp;

    @FXML
    private TextField EditUsername;

    @FXML
    private ImageView Exit;

    @FXML
    private Button Logoutbtn;

    @FXML
    private Button Orderinbtn;

    @FXML
    private Button Paymentshippingbtn;

    @FXML
    private Button Profiladminbtn;

    @FXML
    private Button Stockbtn;

    @FXML
    private Button UploadProfil;

    @FXML
    private Text genderLabel;

    @FXML
    private ImageView imageprofil;

    @FXML
    private Text nomorhpLabel;

    @FXML
    private Text usernameLabel;
    
     @FXML
    private Button EditProfilbtn;

    @FXML
    private Button SimpanProfilbtn;

    // Koneksi database
    private Connection conn;

    
   @FXML
void handleButtonSimpanProfilbtn(ActionEvent event) {
    String newUsername = EditUsername.getText();
    String newGender = EditGender.getText();
    String newNohp = EditNohp.getText();
    String newBioData = BioData.getText();

    if (newUsername.isEmpty() || newGender.isEmpty() || newNohp.isEmpty() || newBioData.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Peringatan");
        alert.setHeaderText("Input tidak valid");
        alert.setContentText("Harap isi semua bidang sebelum menyimpan perubahan.");
        alert.showAndWait();
        return;
    }

    try {
        UserDAO userDAO = new UserDAO(conn);
        boolean isUpdated = userDAO.editUser(
            getCurrentAdminId(), 
            newUsername, 
            null, // Tidak memperbarui gambar
            newBioData, 
            newGender, 
            Integer.parseInt(newNohp)
        );

        if (isUpdated) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sukses");
            alert.setHeaderText("Profil berhasil diperbarui");
            alert.setContentText("Data profil Anda telah berhasil diperbarui.");
            alert.showAndWait();
            loadAdminData();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gagal");
            alert.setHeaderText("Gagal memperbarui profil");
            alert.setContentText("Terjadi kesalahan saat menyimpan perubahan.");
            alert.showAndWait();
        }
    } catch (Exception e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Kesalahan");
        alert.setHeaderText("Terjadi Kesalahan");
        alert.setContentText("Error: " + e.getMessage());
        alert.showAndWait();
    }
}

    
    private void loadAdminData() {
    try {
        String query = "SELECT username, gender, phoneNumber, bioData FROM User WHERE userId = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, getCurrentAdminId()); // Menggunakan metode untuk mendapatkan ID admin
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            EditUsername.setText(rs.getString("username"));
            EditGender.setText(rs.getString("gender"));
            EditNohp.setText(rs.getString("phoneNumber"));
            BioData.setText(rs.getString("bioData"));
        } else {
            System.out.println("Data admin tidak ditemukan.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error mengambil data admin: " + e.getMessage());
    }
}
      @FXML
    void handleButtonEditGender(ActionEvent event) {

    }

    @FXML
    void handleButtonEditNohp(ActionEvent event) {

    }

    @FXML
    void handleButtonEditProfilbtn(ActionEvent event) {

    }

    @FXML
    void handleButtonEditUsername(ActionEvent event) {

    }

    

    @FXML
    void handleButtonUploadProfil(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Mengambil koneksi database dari Connector.java
            conn = Connector.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProfiladminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Memuat data admin dari database
        loadAdminData();
    }

    // Method untuk memuat data admin dari database

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

        // nambahin tombol ke dialog
        alert.getButtonTypes().setAll(iyaNih, nggaJadi);

        // Nunggu respon user
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == iyaNih) {
            // Jika memilih "Iya nih" maka akan ke menu awal
            URL url = new File("src/main/java/viewAdmin/Menulogadmin.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root);

            // Ambil stage dari Logoutbtn
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
        Stage stage = (Stage) Stockbtn.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleNextButtonOrderin(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/viewAdmin/Orderin.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) Orderinbtn.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleNextButtonPayMethod(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/viewAdmin/Paymentmethod.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) Paymentshippingbtn.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleNextButtonPayship(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/viewAdmin/Paymentshipping.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) Paymentshippingbtn.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleClose(MouseEvent event) {
        // Mendapatkan Stage dan menutupnya
        Stage stage = (Stage) Exit.getScene().getWindow();
        stage.close();
    }

    private int getCurrentAdminId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
