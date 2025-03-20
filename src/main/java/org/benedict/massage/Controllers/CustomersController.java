package org.benedict.massage.Controllers;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.benedict.massage.Models.Customer;
import org.benedict.massage.Models.Model;
import org.benedict.massage.Utilities.AlertUtility;
import org.benedict.massage.Utilities.DialogUtility;
import org.benedict.massage.Views.MenuItems;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomersController implements Initializable {
    public Button add_customer_btn;
    public TableView customers_table;
    public TableColumn col_id;
    public TableColumn col_firstName;
    public TableColumn col_lastName;
    public TableColumn col_email;
    public TableColumn col_city;
    public MenuItem remove_customer;
    public TextField filterFirstName;
    public TextField filterLastName;
    public TextField filterCity;
    public Button filterButton;
    public MenuItem edit_customer;

    private FilteredList<Customer> filteredCustomers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_customer_btn.setOnAction(event -> onCreateCustomer());
        initTableColumns();
        loadCustomerData();
        remove_customer.setOnAction(event -> onRemoveCustomer());
        edit_customer.setOnAction(event -> onEditCustomer());

        setRowFactoryForCustomersTable();

        filterButton.setOnAction(event -> applyFilters());
    }
    /**
     * Open create customer window
     */
    public void onCreateCustomer(){
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(MenuItems.CREATE_CUSTOMER);
    }

    /**
     * Init the table columns with Customer model
     */
    private void initTableColumns(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
    }

    /**
     * Load customers data into table
     */

    private void loadCustomerData(){
        ObservableList<Customer> customers = Model.getInstance().getCustomers();
        filteredCustomers = new FilteredList<>(customers);
        customers_table.setItems(filteredCustomers);
    }

    /**
     * Handle customer remove
     */
    private void onRemoveCustomer(){
        Customer selectedCustomer = (Customer) customers_table.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null){
            AlertUtility.displayError("Pasirinkite klientą");
        } else {
            boolean confirmed = AlertUtility.displayConfirmation(
                    "Ar tikrai norite pašalinti klientą?"
            );
            if (confirmed){
                Model.getInstance().deleteCustomer(selectedCustomer.getId());
                ObservableList<Customer> customers = Model.getInstance().getCustomers();
                customers.remove(selectedCustomer);
                AlertUtility.displayInformation("Klientas pašalintas sėkmingai");
                loadCustomerData();
            }
        }
    }

    /**
     * Handle edit customer
     */
    private void onEditCustomer(){
        Customer selectedCustomer = (Customer) customers_table.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null){
            AlertUtility.displayError("Pasirinkite klientą");
        } else {
            editCustomer(selectedCustomer);
        }
    }

    /**
     * Sets row factory for customer table
     */

    private void setRowFactoryForCustomersTable(){
        customers_table.setRowFactory(tableView -> {
            TableRow<Customer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())){
                    Customer selectedCustomer = row.getItem();
                    editCustomer(selectedCustomer);
                }
            });

            return row;
        });
    }


    /**
     *  Opens dialog for editing customer
     * @param customer
     */

    private void editCustomer(Customer customer){
        Optional<Customer> result = DialogUtility.showEditCustomerDialog(customer);
        result.ifPresent(updatedCustomer ->{
            Model.getInstance().updateCustomer(updatedCustomer);
            loadCustomerData();
        });
    }

    /**
     * Customers data filter
     */

    private void applyFilters(){
        String firstNameFilter = filterFirstName.getText().toLowerCase();
        String lastNameFilter = filterLastName.getText().toLowerCase();
        String cityFilter = filterCity.getText().toLowerCase();

        filteredCustomers.setPredicate(customer -> {
            if (!firstNameFilter.isEmpty() && !(customer.getFirstName().toLowerCase().contains(firstNameFilter))) {
                return false;
            }
            if (!lastNameFilter.isEmpty() && !(customer.getLastName().toLowerCase().contains(lastNameFilter))){
                return false;
            }
            if(!cityFilter.isEmpty() && !(customer.getCity().toLowerCase().contains(cityFilter))){
                return false;
            }
            return true;
        });
    }
}
