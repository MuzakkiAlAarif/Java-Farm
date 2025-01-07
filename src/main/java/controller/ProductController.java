package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ProductController {

    @FXML
    private Button Berandabtn;

    @FXML
    private Button Logoutbtn;

    @FXML
    private Button Order;

    @FXML
    private Button Profil1;

    @FXML
    private Button krj;

    @FXML
    private Button paybtn;
    
    @FXML
    private Button Keranjangnow;

    @FXML
    void handleNextButtonBeranda(ActionEvent event) throws IOException {
// Load the FXML file for the next UI (replace "FXMLDocument.fxml" with the actual filename)
        URL url = new File("src/main/java/view/Javashop.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        
        // Create a new scene
        Scene scene = new Scene(root);

        // Get the stage
        Stage stage = (Stage) Berandabtn.getScene().getWindow();

        // Set the scene on the stage
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleNextButtonKRJ(ActionEvent event) throws IOException {
// Load the FXML file for the next UI (replace "FXMLDocument.fxml" with the actual filename)
        URL url = new File("src/main/java/view/Keranjang.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleNextButtonLogout(ActionEvent event) throws IOException {
// Load the FXML file for the next UI (replace "FXMLDocument.fxml" with the actual filename)
        Parent root = FXMLLoader.load(getClass().getResource("../view/Menulog.fxml"));

        // Create a new scene
        Scene scene = new Scene(root);

        // Get the stage
        Stage stage = (Stage) Logoutbtn.getScene().getWindow();

        // Set the scene on the stage
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleNextButtonOrder(ActionEvent event) throws IOException {
// Load the FXML file for the next UI (replace "FXMLDocument.fxml" with the actual filename)
        Parent root = FXMLLoader.load(getClass().getResource("../view/Orderpay.fxml"));

        // Create a new scene
        Scene scene = new Scene(root);

        // Get the stage
        Stage stage = (Stage) Order.getScene().getWindow();

        // Set the scene on the stage
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleNextButtonpayment(ActionEvent event) throws IOException {
// Load the FXML file for the next UI (replace "FXMLDocument.fxml" with the actual filename)
        Parent root = FXMLLoader.load(getClass().getResource("../view/Payment.fxml"));

        // Create a new scene
        Scene scene = new Scene(root);

        // Get the stage
        Stage stage = (Stage) paybtn.getScene().getWindow();

        // Set the scene on the stage
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleNextButtonprofil(ActionEvent event) throws IOException {
// Load the FXML file for the next UI (replace "FXMLDocument.fxml" with the actual filename)
        Parent root = FXMLLoader.load(getClass().getResource("../view/Profil.fxml"));

        // Create a new scene
        Scene scene = new Scene(root);

        // Get the stage
        Stage stage = (Stage) Profil1.getScene().getWindow();

        // Set the scene on the stage
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void handleNextButtonKeranjangnow(ActionEvent event) throws IOException {
// Load the FXML file for the next UI (replace "FXMLDocument.fxml" with the actual filename)
        Parent root = FXMLLoader.load(getClass().getResource("../view/Keranjang.fxml"));

        // Create a new scene
        Scene scene = new Scene(root);

        // Get the stage
        Stage stage = (Stage) Keranjangnow.getScene().getWindow();

        // Set the scene on the stage
        stage.setScene(scene);
        stage.show();
    }
    
    

}
