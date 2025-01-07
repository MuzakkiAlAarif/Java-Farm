package controller;

/**
 *
 * @author fikrs
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ItemData {
    private static ObservableList<Item> items = FXCollections.observableArrayList();

    static {
        items.add(new Item("1", "Bijidelima", "Kategori Biji", 5000, 10,
                ItemData.class.getResource("/Packicon/Bijidelima.png").toExternalForm()));
        items.add(new Item("2", "Product 1", "Kategori Umum", 10000, 5,
                ItemData.class.getResource("/Packicon/Productu1.png").toExternalForm()));
    }

    public static ObservableList<Item> getItems() {
        return items;
    }
}

