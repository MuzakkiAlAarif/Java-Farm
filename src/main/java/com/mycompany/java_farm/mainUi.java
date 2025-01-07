package com.mycompany.java_farm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.stage.StageStyle;

public class mainUi extends javafx.application.Application{

    private double xOffset = 0;
    private double yOffset = 0;
   
    public void start(Stage stage) throws Exception {
        // Gunakan jalur absolut ke lokasi file FXML
        File fxmlFile = new File("D:/FILE MEEEEE/SEMESTER 3/PBO/Java_farm/Java_farm/Java_farm/Java_farm/Java_farm/src/main/java/view/Loginnow.fxml");

        if (!fxmlFile.exists()) {
            System.out.println("File FXML tidak ditemukan: " + fxmlFile.getAbsolutePath());
            return;
        }
        
        // Memuat file FXML
        Parent root = FXMLLoader.load(fxmlFile.toURI().toURL());

        // Menambahkan fungsi untuk membuat window dapat dipindahkan
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        // Memuat file FXML
       
        stage.initStyle(StageStyle.UNDECORATED);       

        // Menyiapkan tampilan dan menampilkan window
        Scene scene = new Scene(root);
        stage.setTitle("Java Farm UI");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // Memanggil launch dari JavaFX Application class
        launch(args); // Ini akan memanggil metode start
        // Menjalankan Java Farm CLI jika perlu
        Java_farm.runFarmApp();
    }
}
        