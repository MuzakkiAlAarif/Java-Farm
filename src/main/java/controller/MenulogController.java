package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class for Menulog.
 *
 * @author fikrs
 */
public class MenulogController {

    @FXML
    private Button btnLog;

    @FXML
    private Button btnReg;

    @FXML
    private ImageView Exit;

    // Method untuk tombol Login
    @FXML
    void handleNextButtonLog(ActionEvent event) {
        try {
            // Load file Logginow.fxml
            Parent root = FXMLLoader.load(getClass().getResource("/view/Logginow.fxml"));
            Scene scene = new Scene(root);

            // Ambil stage dan set scene baru
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saat memuat halaman Login: " + e.getMessage());
        }
    }

    // Method untuk tombol Register
    @FXML
    void handleNextButtonReg(ActionEvent event) {
        try {
            // Load file Registnow.fxml
            Parent root = FXMLLoader.load(getClass().getResource("/view/Registnow.fxml"));
            Scene scene = new Scene(root);

            // Ambil stage dan set scene baru
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saat memuat halaman Register: " + e.getMessage());
        }
    }

    // Method untuk menutup aplikasi
    @FXML
    public void handleClose(MouseEvent event) {
        Stage stage = (Stage) Exit.getScene().getWindow();
        stage.close();
    }
}
