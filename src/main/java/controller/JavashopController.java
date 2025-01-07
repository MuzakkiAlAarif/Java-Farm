package controller;  
  
import Class.Category;  
import Class.Product;  
import DAO.ProductDAO;  
import com.mycompany.java_farm.Connector;  
import javafx.event.ActionEvent;  
import javafx.fxml.FXML;  
import javafx.fxml.FXMLLoader;  
import javafx.scene.Node;  
import javafx.scene.Parent;  
import javafx.scene.Scene;  
import javafx.scene.control.*;  
import javafx.scene.image.Image;  
import javafx.scene.image.ImageView;  
import javafx.stage.Stage;  
  
import java.io.File;  
import java.io.IOException;  
import java.net.URL;  
import java.sql.Connection;  
import java.sql.SQLException;  
import java.util.ArrayList;  
import java.util.List;  
import java.util.Optional;  
import javafx.scene.input.MouseEvent;  
  
public class JavashopController {  
  
    @FXML  
    private Button Berandabtn;  
  
    @FXML  
    private MenuButton KategoriBiji;  
  
    @FXML  
    private Button Keranjangbtn;  
  
    @FXML  
    private Button Paymentbtn;  
  
    @FXML  
    private Button Profilbtn;  
  
    @FXML  
    private Button Profil1;  
  
    @FXML  
    private Button Orderbtn;  
  
    @FXML  
    private Button Logoutbtn;  
  
    @FXML  
    private ImageView Exit;  
  
    @FXML  
    private Button Cart1;  
  
    @FXML  
    private Label labelproduk1; // Label untuk Nama Produk  
    @FXML  
    private Label labelharga1; // Label untuk Harga Produk  
    @FXML  
    private Label labelstok1; // Label untuk Stok Produk  
  
    @FXML  
    private TextField searchbartext;  
  
    @FXML  
    private ImageView productImageView;  
  
    private ProductDAO productDAO;  
    private List<Product> cartProducts = new ArrayList<>(); // Menyimpan produk yang ditambahkan ke keranjang  
    @FXML  
    private ListView<Product> productListView;
    private KeranjangController keranjangController;
    JavashopController javashopController = new JavashopController();  
   

    public void setKeranjangController(KeranjangController controller) {  
        this.keranjangController = controller;  
    }  

    
    @FXML  
    private void initialize() {  
        System.out.println("Initializing JavashopController...");  
        try {  
            Connection connector = Connector.getConnection(); // Ambil koneksi dari Connector  
            productDAO = new ProductDAO(connector); // Kirim koneksi ke ProductDAO  
            System.out.println("JavashopController initialized with ProductDAO.");  
        } catch (SQLException e) {  
            e.printStackTrace();  
            showAlert("Error", "Gagal menginisialisasi koneksi database.");  
            return; // Keluar dari metode jika koneksi gagal  
        }  
  
        MenuItem buahOption = KategoriBiji.getItems().get(0);  
        MenuItem sayurOption = KategoriBiji.getItems().get(1);  
        MenuItem umbiOption = KategoriBiji.getItems().get(2);  
  
        buahOption.setOnAction(event -> handleCategoryOption("Buah-buahan"));  
        sayurOption.setOnAction(event -> handleCategoryOption("Sayur-sayuran"));  
        umbiOption.setOnAction(event -> handleCategoryOption("Umbi-umbian"));  
  
        // Contoh menampilkan produk pertama dari database  
        try {  
            List<Product> products = productDAO.getAllProducts();  
            System.out.println("Products loaded: " + products.size());  
            if (!products.isEmpty()) {  
                displayProductDetails(products.get(0)); // Tampilkan detail produk pertama  
            } else {  
                System.out.println("No products found in the database.");  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
            showAlert("Error", "Gagal memuat produk dari database.");  
        }  
    }  
  
    public void updateProductListView() {  
    productListView.getItems().clear(); // Kosongkan ListView  
    List<Product> cartProducts = keranjangController.getCartProducts(); // Ambil produk dari keranjang  
    productListView.getItems().addAll(cartProducts); // Tambahkan produk ke ListView  
}  
    
    public void displayProductDetails(Product product) {  
        if (product != null) {  
            System.out.println("Displaying product details: " + product.getProduct());  
            labelproduk1.setText(product.getProduct());  
            labelharga1.setText("Rp " + product.getPrice());  
            labelstok1.setText(String.valueOf(product.getStock()));  
  
            // Mengatur gambar produk  
            String imagePath = product.getImage(); // Menggunakan path yang sesuai  
            Image image = new Image("file:///" + imagePath.replace("\\", "/")); // Menggunakan format URL  
            productImageView.setImage(image);  
        } else {  
            System.err.println("Product is null, cannot display details.");  
        }  
    }  
  
    @FXML  
    private void handleAddToCart(ActionEvent event) {  
    // Ambil informasi produk dari label dan image view  
    String productName = labelproduk1.getText();  
    double price = Double.parseDouble(labelharga1.getText().replace("Rp ", "").replace(".", "").trim());  
    String imagePath = productImageView.getImage() != null ? productImageView.getImage().getUrl() : "default_image_path";  
    int stock = Integer.parseInt(labelstok1.getText());  
    Category category = new Category(); // Sesuaikan dengan kategori yang relevan  
  
    // Validasi informasi produk  
    if (productName.isEmpty() || price < 0 || stock < 0) {  
        showAlert("Error", "Informasi produk tidak valid.");  
        return;  
    }  
  
    // Buat objek produk baru  
    Product currentProduct = new Product(0, productName, price, imagePath, stock, category); // ID produk bisa diatur sesuai kebutuhan  
  
    // Tambahkan produk ke keranjang  
    addToCart(currentProduct);  
    showAlert("Sukses", "Produk berhasil ditambahkan ke keranjang.");  
}  


  
    public void addToCart(Product product) {  
    if (product != null) {  
        cartProducts.add(product);  
        System.out.println("Total products in cart: " + cartProducts.size());  
  
        // Update keranjang jika sudah ada controller yang terhubung  
        if (keranjangController != null) {  
            keranjangController.updateProductListView(); // Pastikan ini memanggil metode yang benar  
        }  
    } else {  
        System.err.println("Cannot add null product to cart.");  
    }  
}  

    public List<Product> getCartProducts() {  
        return cartProducts;  
    }  

  
    @FXML  
    void handleNextButtonBeranda(ActionEvent event) throws IOException {  
        URL url = new File("src/main/java/view/Javashop.fxml").toURI().toURL();  
        Parent root = FXMLLoader.load(url);  
        Scene scene = new Scene(root);  
  
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
        stage.setScene(scene);  
        stage.show();  
    }  
  
    @FXML  
void handleNextButtonKeranjang(ActionEvent event) throws IOException {  
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Keranjang.fxml"));  
    Parent root = loader.load();  
  
    // Ambil controller dari loader  
    KeranjangController keranjangController = loader.getController();  
    this.keranjangController = keranjangController; // Simpan referensi ke keranjangController  
    keranjangController.setJavashopController(this); // Hubungkan controller  
  
    Scene scene = new Scene(root);  
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
    stage.setScene(scene);  
    stage.show();  
}  



  
    @FXML  
    void handleNextButtonPayment(ActionEvent event) throws IOException {  
        URL url = new File("src/main/java/view/Payment.fxml").toURI().toURL();  
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
    void handleNextButtonOrder(ActionEvent event) throws IOException {  
        URL url = new File("src/main/java/view/Orderpay.fxml").toURI().toURL();  
        Parent root = FXMLLoader.load(url);  
        Scene scene = new Scene(root);  
  
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
        stage.setScene(scene);  
        stage.show();  
    }  
       @FXML  
    void handleNextButtonCart2(ActionEvent event) throws IOException {  
        URL url = new File("src/main/java/view/Keranjang.fxml").toURI().toURL();  
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
    void handleNextButtonCart1(ActionEvent event) throws IOException {  
        handleNextButtonKeranjang(event);  
    }  
  
    @FXML  
    void handleNextButtonCart3(ActionEvent event) throws IOException {  
        handleNextButtonKeranjang(event);  
    }  
  
    @FXML  
    void handleNextButtonCart4(ActionEvent event) throws IOException {  
        handleNextButtonKeranjang(event);  
    }  
  
    @FXML  
    public void handleClose(MouseEvent event) {  
        Stage stage = (Stage) Exit.getScene().getWindow();  
        stage.close();  
    }  
  
    @FXML  
    void searchbar(ActionEvent event) {  
        // Implementasi pencarian  
    }  
  
    @FXML  
    private void handleCategoryOption(String option) {  
        System.out.println("Pilihan kategori: " + option);  
        // Implementasi filter berdasarkan kategori  
    }  
  
    private void showAlert(String title, String message) {  
        Alert alert = new Alert(Alert.AlertType.INFORMATION);  
        alert.setTitle(title);  
        alert.setHeaderText(null);  
        alert.setContentText(message);  
        alert.showAndWait();  
    }  
}  
