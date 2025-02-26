package org.benedict.library.Controllers;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.benedict.library.Models.Author;
import org.benedict.library.Models.Model;
import org.benedict.library.Utilities.AlertUtility;
import org.benedict.library.Utilities.DialogUtility;
import org.benedict.library.Views.MenuItems;

import java.net.URL;
import java.util.Optional;
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
    public TextField filterFirstName;
    public TextField filterLastName;
    public TextField filterCity;
    public Button filterButton;

    private FilteredList<Author> filteredAuthors;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_author_btn.setOnAction(event -> onCreateAuthor());
        initTableColumns();
        loadAuthorData();
        remove_author.setOnAction(event -> onRemoveAuthor());


        setRowFactoryForAuthorsTable();

        //Data filtering
        filteredAuthors = new FilteredList<>(Model.getInstance().getAuthors());
        authors_table.setItems(filteredAuthors);

        filterButton.setOnAction(event -> applyFilters());
    }
    /**
     * Open create author window
     */
    public void onCreateAuthor(){
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(MenuItems.CREATE_AUTHOR);
    }

    /**
     * Init the table columns with Author model
     */
    private void initTableColumns(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
    }

    /**
     * Load authors data into table
     */

    private void loadAuthorData(){
        ObservableList<Author> authors = Model.getInstance().getAuthors();
        authors_table.setItems(authors);
    }

    /**
     * Handle author remove
     */
    private void onRemoveAuthor(){
        Author selectedAuthor = (Author) authors_table.getSelectionModel().getSelectedItem();
        if (selectedAuthor == null){
            AlertUtility.displayError("Pasirinkite autorių");
        } else {
            boolean confirmed = AlertUtility.displayConfirmation(
                    "Ar tikrai norite pašalinti autorių?"
            );
            if (confirmed){
                Model.getInstance().deleteAuthor(selectedAuthor.getId());
                ObservableList<Author> authors = Model.getInstance().getAuthors();
                authors.remove(selectedAuthor);
                AlertUtility.displayInformation("Autorius pašalintas sėkmingai");
                loadAuthorData();
            }
        }


    }

    /**
     * Sets row factory for author table
     */

    private void setRowFactoryForAuthorsTable(){
        authors_table.setRowFactory(tableView -> {
            TableRow<Author> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())){
                    Author selectedAuthor = row.getItem();
                    editAuthor(selectedAuthor);
                }
            });

            return row;
        });
    }


    /**
     *  Opens dialog for editing author
     * @param author
     */

    private void editAuthor(Author author){
        Optional<Author> result = DialogUtility.showEditAuthorDialog(author);
        result.ifPresent(updatedAuthor ->{
            Model.getInstance().updateAuthor(updatedAuthor);
            loadAuthorData();
        });
    }

    /**
     * Authors data filter
     */

    private void applyFilters(){
        String firstNameFilter = filterFirstName.getText().toLowerCase();
        String lastNameFilter = filterLastName.getText().toLowerCase();
        String cityFilter = filterCity.getText().toLowerCase();

        filteredAuthors.setPredicate(author -> {
            if (!firstNameFilter.isEmpty() && !(author.getFirstName().toLowerCase().contains(firstNameFilter))) {
                return false;
            }
            if (!lastNameFilter.isEmpty() && !(author.getLastName().toLowerCase().contains(lastNameFilter))){
                return false;
            }
            if(!cityFilter.isEmpty() && !(author.getCity().toLowerCase().contains(cityFilter))){
                return false;
            }

            return true;
        });
    }
}
