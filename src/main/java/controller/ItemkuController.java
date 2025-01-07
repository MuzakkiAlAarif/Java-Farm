/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author fikrs
 */
public class ItemkuController implements Initializable {

    @FXML
    private ImageView img;

    @FXML
    private Label labelbiji;

    @FXML
    private Label labelharga;
    /**
     * Initializes the controller class.
     
     */
    public void setItemData(String biji, String harga, String imagePath) {
        labelbiji.setText(biji);
        labelharga.setText(harga);
        img.setImage(new Image(imagePath));
    }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
}
//    void setItemData(String string, String string0, String packiconBijidelimapng) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//    
//}
