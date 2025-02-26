package org.benedict.library.Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.benedict.library.Models.Book;
import org.benedict.library.Models.Model;
import org.benedict.library.Utilities.AlertUtility;
import org.benedict.library.Utilities.DialogUtility;
import org.benedict.library.Views.MenuItems;

import java.net.URL;
import java.util.Optional;
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
    public MenuItem remove_book;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_book_btn.setOnAction(event -> onAddBook());
        initTableColumns();
        loadBooksData();
        remove_book.setOnAction(event -> onRemoveBook());
        setRowFactoryForBooksTable();
    }

    /**
     * Open add book window
     */
    public void onAddBook(){
        if (!Model.getInstance().getAuthors().isEmpty()) {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(MenuItems.ADD_BOOK);
        }else{
            AlertUtility.displayError("Knygos pridėti negalime, nėra autorių!");
        }
    }

    /**
     * Init the table columns with Author model
     */
    private void initTableColumns(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_ISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_page_number.setCellValueFactory(new PropertyValueFactory<>("page_number"));
        col_publish_date.setCellValueFactory(new PropertyValueFactory<>("publish_date"));
        col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * Load authors data into table
     */

    private void loadBooksData(){
        ObservableList<Book> books = Model.getInstance().getBooks();
        books_table.setItems(books);
    }

    /**
     * Handle book remove
     */
    private void onRemoveBook(){
        Book selectedBook = (Book)books_table.getSelectionModel().getSelectedItem();
        if (selectedBook == null){
            AlertUtility.displayError("Pasirinkite knygą");
        } else {
            boolean confirmed = AlertUtility.displayConfirmation(
                    "Ar tikrai norite pašalinti knygą?"
            );
            if (confirmed){
                Model.getInstance().deleteBook(selectedBook.getId());
                ObservableList<Book> books = Model.getInstance().getBooks();
                books.remove(selectedBook);
                AlertUtility.displayInformation("Knyga pašalinta sėkmingai");
                loadBooksData();
            }
        }


    }

    /**
     * Sets row factory for books table
     */

    private void setRowFactoryForBooksTable(){
        books_table.setRowFactory(tableView -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())){
                    Book selectedBook = row.getItem();
                    editBook(selectedBook);
                }
            });

            return row;
        });
    }


    /**
     *  Opens dialog for editing book
     * @param book
     */

    private void editBook(Book book){
        Optional<Book> result = DialogUtility.showEditBookDialog(book);
        result.ifPresent(updatedBook ->{
            Model.getInstance().updateBook(updatedBook);
            loadBooksData();
        });
    }
}
