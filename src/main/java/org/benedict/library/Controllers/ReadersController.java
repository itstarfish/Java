package org.benedict.library.Controllers;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.benedict.library.Models.Model;
import org.benedict.library.Models.Reader;
import org.benedict.library.Utilities.AlertUtility;
import org.benedict.library.Utilities.DialogUtility;
import org.benedict.library.Views.MenuItems;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReadersController implements Initializable {

    public Button add_reader_btn;
    public TextField filterFirstName;
    public TextField filterLastName;
    public TextField filterCity;
    public Button filterButton;
    public TableView readers_table;
    public TableColumn col_id;
    public TableColumn col_firstName;
    public TableColumn col_lastName;
    public TableColumn col_email;
    public TableColumn col_city;
    public MenuItem remove_reader;

    private FilteredList<Reader> filteredReaders;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_reader_btn.setOnAction(event -> onCreateReader());
        initTableColumns();
        loadReaderData();
        remove_reader.setOnAction(event -> onRemoveReader());

        setRowFactoryForReadersTable();

        //Data filtering
        filteredReaders = new FilteredList<>(Model.getInstance().getReaders());
        readers_table.setItems(filteredReaders);

        filterButton.setOnAction(event -> applyFilters());
    }
    /**
     * Open create reader window
     */
    public void onCreateReader(){
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(MenuItems.ADD_READER);
    }

    /**
     * Init the table columns with Reader model
     */
    private void initTableColumns(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
    }

    /**
     * Load reader data into table
     */

    private void loadReaderData(){
        ObservableList<Reader> readers = Model.getInstance().getReaders();
        readers_table.setItems(readers);
    }

    /**
     * Handle reader remove
     */
    private void onRemoveReader(){
        Reader selectedReader = (Reader) readers_table.getSelectionModel().getSelectedItem();
        if (selectedReader == null){
            AlertUtility.displayError("Pasirinkite skaitytoją");
        } else {
            boolean confirmed = AlertUtility.displayConfirmation(
                    "Ar tikrai norite pašalinti skaitytoją?"
            );
            if (confirmed){
                Model.getInstance().deleteAuthor(selectedReader.getId());
                ObservableList<Reader> readers = Model.getInstance().getReaders();
                readers.remove(selectedReader);
                AlertUtility.displayInformation("Skaitytojas pašalintas sėkmingai");
                loadReaderData();
            }
        }


    }

    /**
     * Sets row factory for reader table
     */

    private void setRowFactoryForReadersTable(){
        readers_table.setRowFactory(tableView -> {
            TableRow<Reader> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())){
                    Reader selectedReader = row.getItem();
                    editReader(selectedReader);
                }
            });

            return row;
        });
    }


    /**
     *  Opens dialog for editing reader
     * @param reader
     */

    private void editReader(Reader reader){
        Optional<Reader> result = DialogUtility.showEditReaderDialog(reader);
        result.ifPresent(updatedReader ->{
            Model.getInstance().updateReader(updatedReader);
            loadReaderData();
        });
    }

    /**
     * Readers data filter
     */

    private void applyFilters(){
        String firstNameFilter = filterFirstName.getText().toLowerCase();
        String lastNameFilter = filterLastName.getText().toLowerCase();
        String cityFilter = filterCity.getText().toLowerCase();

        filteredReaders.setPredicate(reader -> {
            if (!firstNameFilter.isEmpty() && !(reader.getFirstName().toLowerCase().contains(firstNameFilter))) {
                return false;
            }
            if (!lastNameFilter.isEmpty() && !(reader.getLastName().toLowerCase().contains(lastNameFilter))){
                return false;
            }
            if(!cityFilter.isEmpty() && !(reader.getCity().toLowerCase().contains(cityFilter))){
                return false;
            }

            return true;
        });
    }
}