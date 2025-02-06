package org.benedict.library.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.benedict.library.Models.Model;
import org.benedict.library.Views.MenuItems;

import java.net.URL;
import java.util.ResourceBundle;

public class BooksController implements Initializable {
    public Button add_book_btn;
    public TableView books_table;
    public TableColumn col_id;
    public TableColumn col_ISBN;
    public TableColumn col_title;
    public TableColumn col_category;
    public TableColumn col_description;
    public TableColumn col_page_number;
    public TableColumn col_publish_date;
    public TableColumn col_price;
    public TableColumn col_author;
    public MenuItem remove_author;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_book_btn.setOnAction(event -> onAddBook());
    }

    /**
     * Open add book window
     */
    public void onAddBook(){
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(MenuItems.ADD_BOOK);
    }
}
