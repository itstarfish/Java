package org.benedict.library.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.benedict.library.Models.Author;
import org.benedict.library.Models.Model;
import org.benedict.library.Utilities.AlertUtility;

import java.net.URL;
import java.util.ResourceBundle;

public class addBooksController implements Initializable {
    public TextField field_ISBN;
    public TextField field_Title;
    public TextField field_category;
    public TextField field_description;
    public TextField field_page_number;
    public TextField field_publish_date;
    public TextField field_price;
    public Button add_book_btn;
    public ComboBox field_author;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        field_author.getItems().addAll(Model.getInstance().getAuthorIds());
        add_book_btn.setOnAction(event -> onBook());

    }

    private void onBook() {

            if (field_ISBN.getText().isEmpty() ||
                    field_Title.getText().isEmpty() ||
                    field_category.getText().isEmpty() ||
                    field_description.getText().isEmpty() ||
                    field_page_number.getText().isEmpty() ||
                    field_publish_date.getText().isEmpty() ||
                    field_price.getText().isEmpty() ||
                    field_author.getSelectionModel().isEmpty()){
                AlertUtility.displayError("Visi laukai yra privalomi");
            } else {
            String ISBN = field_ISBN.getText();
            String title = field_Title.getText();
            String category = field_category.getText();
            String description = field_description.getText();
            int page_number = Integer.parseInt(field_page_number.getText());
            String publish_date = field_publish_date.getText();
            double price = Double.parseDouble(field_price.getText());
            int author = Integer.parseInt(field_author.getValue().toString());

            /**
             * Create the book
             */

            Model.getInstance().addBook(ISBN, title, category, description, page_number, publish_date, price, author);

            AlertUtility.displayInformation("Knyga sėkmingai pridėta");

            emptyFields();
            }
    }

    private void emptyFields(){
        field_ISBN.setText("");
        field_category.setText("");
        field_description.setText("");
        field_price.setText("");
        field_page_number.setText("");
        field_publish_date.setText("");
        field_Title.setText("");
    }


}
