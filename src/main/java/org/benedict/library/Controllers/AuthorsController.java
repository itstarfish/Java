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

public class AuthorsController implements Initializable {
    public Button add_author_btn;
    public TableView authors_table;
    public TableColumn col_id;
    public TableColumn col_firstName;
    public TableColumn col_lastName;
    public TableColumn col_email;
    public TableColumn col_city;
    public MenuItem remove_author;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_author_btn.setOnAction(event -> onCreateAuthor());
    }
    /**
     * Open create author window
     */
    public void onCreateAuthor(){
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(MenuItems.CREATE_AUTHOR);
    }
}
