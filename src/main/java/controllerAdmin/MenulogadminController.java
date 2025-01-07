/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllerAdmin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fikrs
 */
public class MenulogadminController  {

    /**
     * Initializes the controller class.
     */
//    @FXML
//    private AnchorPane root;
    
    
    @FXML
    private Button btnLog;
    
    @FXML
    private Button btnReg;
    
    @FXML
    private ImageView Exit;
   
    
    @FXML
    private void handleNextButtonLoginAdmin(ActionEvent event) throws IOException {
        // Load the FXML file for the next UI (replace "FXMLDocument.fxml" with the actual filename)
        URL url = new File ("src/main/java/viewAdmin/LoginAdmin.fxml"). toURI().toURL();
    Parent root = FXMLLoader.load(url);
        // Create a new scene
        Scene scene = new Scene(root);

        // Get the stage
        Stage stage = (Stage) btnLog.getScene().getWindow();

        // Set the scene on the stage
        stage.setScene(scene);
        stage.show();
    }
//    
    @FXML
    private void handleNextButtonRegistAdmin(ActionEvent event) throws IOException {
        // Load the FXML file for the next UI (replace "FXMLDocument.fxml" with the actual filename)
        URL url = new File ("src/main/java/viewAdmin/RegistAdmin.fxml"). toURI().toURL();
    Parent root = FXMLLoader.load(url);
        // Create a new scene
        Scene scene = new Scene(root);

        // Get the stage
        Stage stage = (Stage) btnReg.getScene().getWindow();

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
    
   
    
//    @FXML
//    public void initialize1() {
//       
//    }
    
    
    
}     