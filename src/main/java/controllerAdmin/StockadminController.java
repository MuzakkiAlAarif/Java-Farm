package controllerAdmin;

import DAO.ProductDAO;
import Class.Product;
import Class.Category;
import com.mycompany.java_farm.Connector;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class StockadminController{

    @FXML
    private ImageView Imageadmin;

    @FXML
    private TextField Produk;

    @FXML
    private TextField Kategori;

    @FXML
    private TextField Stock;

    @FXML
    private TextField Harga;

    @FXML
    private TextField ProductId; // Tambahan untuk Update/Delete

    @FXML
    private Button Addbtn;

    @FXML
    private Button Updatebtn;

    @FXML
    private Button Deletebtn;

    @FXML
    private Button Uploadbtn;

    @FXML
    private TableColumn<Product, Double> tbl_harga;

    @FXML
    private TableColumn<Product, Integer> tbl_kategori;

    @FXML
    private TableColumn<Product, String> tbl_produk;

    @FXML
    private TableColumn<Product, Integer> tbl_stock;

    @FXML
    private TableColumn<Product, Integer> tbl_produkId;

    @FXML
    private TableView<Product> TableView;

    private String imagePath; // Variabel untuk menyimpan path gambar

    @FXML
    private ObservableList<Product> productList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Hubungkan kolom tabel dengan properti produk
        tbl_produkId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        tbl_produk.setCellValueFactory(new PropertyValueFactory<>("product"));
        tbl_harga.setCellValueFactory(new PropertyValueFactory<>("price"));
        tbl_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tbl_kategori.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCategory().getCategoryId()).asObject());

        // Muat data ke dalam tabel saat inisialisasi
        loadTableData();

        // Event listener untuk menangkap baris yang dipilih
        TableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateFields(newValue);
            }
        });

        // Set tabel menjadi editable
        TableView.setEditable(true);

        // Tambahkan editor untuk kolom stok
        tbl_stock.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tbl_stock.setOnEditCommit(event -> {
            Product product = event.getRowValue();
            product.setStock(event.getNewValue());
            updateProductInDatabase(product);
        });
    }

    @FXML
    private void loadTableData() {
        productList.clear();
        try (Connection connection = Connector.getConnection()) {
            ProductDAO productDAO = new ProductDAO(connection);
            productList.addAll(productDAO.getAllProducts());
            TableView.setItems(productList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Kesalahan", "Gagal memuat data produk: " + e.getMessage());
        }
    }

    @FXML
    void handleButtonAdd(ActionEvent event) {
        String produk = Produk.getText();
        String kategori = Kategori.getText();
        String stockText = Stock.getText();
        String hargaText = Harga.getText();

        if (produk.isEmpty() || kategori.isEmpty() || stockText.isEmpty() || hargaText.isEmpty() || imagePath == null) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Data tidak lengkap. Pastikan semua bidang terisi.");
            return;
        }

        try {
            int stock = Integer.parseInt(stockText);
            double harga = Double.parseDouble(hargaText);
            int categoryId = Integer.parseInt(kategori);

            try (Connection connection = Connector.getConnection()) {
                ProductDAO productDAO = new ProductDAO(connection);

                Category category = new Category(categoryId, null);

                Product product = new Product(0, produk, harga, imagePath, stock, category);

                productDAO.addProduct(product);

                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Produk berhasil ditambahkan.");

                loadTableData(); // Muat ulang tabel
                clearFields();

            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Kesalahan", "Gagal menyimpan data ke database: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Kesalahan", "Stok, Harga, dan Kategori harus berupa angka yang valid.");
        }
    }

    @FXML
    void handleButtonDelete(ActionEvent event) {
        Product selectedProduct = TableView.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih baris yang ingin dihapus.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Hapus");
        alert.setHeaderText("Anda yakin ingin menghapus produk ini?");
        alert.setContentText("Produk: " + selectedProduct.getProduct());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try (Connection connection = Connector.getConnection()) {
                ProductDAO productDAO = new ProductDAO(connection);
                productDAO.deleteProduct(selectedProduct.getProductId());
                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Produk berhasil dihapus.");
                loadTableData();
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Kesalahan", "Gagal menghapus data: " + e.getMessage());
            }
        }
    }

    @FXML
    void handleButtonUpdate(ActionEvent event) {
        Product selectedProduct = TableView.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih produk yang ingin diperbarui.");
            return;
        }

        String produk = Produk.getText();
        String kategoriText = Kategori.getText();
        String stockText = Stock.getText();
        String hargaText = Harga.getText();

        if (produk.isEmpty() || kategoriText.isEmpty() || stockText.isEmpty() || hargaText.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Semua bidang input harus diisi.");
            return;
        }

        try {
            int categoryId = Integer.parseInt(kategoriText);
            int stock = Integer.parseInt(stockText);
            double harga = Double.parseDouble(hargaText);

            selectedProduct.setProduct(produk);
            selectedProduct.setPrice(harga);
            selectedProduct.setStock(stock);

            Category updatedCategory = new Category(categoryId, null); // Category tanpa nama
            selectedProduct.setCategory(updatedCategory);

            if (imagePath != null) {
                selectedProduct.setImagePath(imagePath);
            }

            try (Connection connection = Connector.getConnection()) {
                ProductDAO productDAO = new ProductDAO(connection);
                productDAO.updateProduct(selectedProduct); // Metode ini menyimpan ke database
            }

            loadTableData();
            clearFields();

            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Produk berhasil diperbarui.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Kesalahan", "Kategori, stok, dan harga harus berupa angka valid.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Kesalahan", "Gagal memperbarui produk di database: " + e.getMessage());
        }
    }


    private void populateFields(Product product) {
        ProductId.setText(String.valueOf(product.getProductId()));
        Produk.setText(product.getProduct());
        Kategori.setText(String.valueOf(product.getCategory().getCategoryId()));
        Stock.setText(String.valueOf(product.getStock()));
        Harga.setText(String.valueOf(product.getPrice()));

        if (product.getImagePath() != null) {
            Imageadmin.setImage(new Image(new File(product.getImagePath()).toURI().toString()));
            imagePath = product.getImagePath();
        }
    }

    @FXML
    void handleUploadButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih Gambar Produk");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", ".png", ".jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            imagePath = selectedFile.getAbsolutePath();
            Imageadmin.setImage(new Image(selectedFile.toURI().toString()));
        } else {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Tidak ada file yang dipilih.");
        }
    }
    @FXML
    void handleClose(javafx.scene.input.MouseEvent event) {
        System.out.println("Aplikasi Ditutup");
        System.exit(0);
    }

    @FXML
    void handleNextButtonProfilAdmin(ActionEvent event) throws MalformedURLException, IOException {
        URL url = new File("src/main/java/viewAdmin/Profiladmin.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleNextButtonOrderin(ActionEvent event) throws MalformedURLException, IOException {
        URL url = new File("src/main/java/viewAdmin/Orderin.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleNextButtonPayship(ActionEvent event) throws MalformedURLException, IOException {
        URL url = new File("src/main/java/viewAdmin/Payship.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleNextButtonLogout(ActionEvent event) throws MalformedURLException, IOException {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Konfirmasi Logout");
        alert.setHeaderText("Kakak yakin mau keluar?");
        alert.setContentText("Pilih \"Iya nih\" untuk melanjutkan atau \"Ngga jadi\" untuk tetap di halaman ini.");

        ButtonType iyaNih = new ButtonType("Iya nih");
        ButtonType nggaJadi = new ButtonType("Ngga jadi");

        alert.getButtonTypes().setAll(iyaNih, nggaJadi);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == iyaNih) {
            URL url = new File("src/main/java/viewAdmin/Menulogadmin.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
    

    private void clearFields() {
        ProductId.clear();
        Produk.clear();
        Kategori.clear();
        Stock.clear();
        Harga.clear();
        Imageadmin.setImage(null);
        imagePath = null;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updateProductInDatabase(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}