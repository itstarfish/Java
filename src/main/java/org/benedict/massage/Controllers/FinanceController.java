package org.benedict.massage.Controllers;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.benedict.massage.Models.Customer;
import org.benedict.massage.Models.Finance;
import org.benedict.massage.Models.Model;
import org.benedict.massage.Models.Visit;
import org.benedict.massage.Utilities.AlertUtility;
import org.benedict.massage.Utilities.DialogUtility;
import org.benedict.massage.Views.MenuItems;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class FinanceController implements Initializable {

    public Button add_finance_record_btn;
    public Button filterButton;
    public TableView finance_records_table;
    public MenuItem remove_finance_record;
    public TableColumn col_id;
    public TableColumn col_visit;
    public TableColumn col_payment;
    public TableColumn col_paymentDate;
    public DatePicker filter_date_from;
    public DatePicker filter_date_to;
    public MenuItem edit_finance;

    private FilteredList<Finance> filteredFinanceRecords;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_finance_record_btn.setOnAction(event -> onCreateFinanceRecord());
        initTableColumns();
        loadFinanceData();
        remove_finance_record.setOnAction(event -> onRemoveFinanceRecord());
        edit_finance.setOnAction(event -> onEditFinanceRecord());
        setRowFactoryForFinanceRecordsTable();
        
        filterButton.setOnAction(event -> applyFilters());
    }
    /**
     * Open create finance record window
     */
    public void onCreateFinanceRecord(){
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(MenuItems.ADD_FINANCE);
    }

    /**
     * Init the table columns with Finance model
     */
    private void initTableColumns(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_visit.setCellValueFactory(new PropertyValueFactory<>("visit"));
        col_payment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        col_paymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

    }

    /**
     * Load finance records data into table
     */
    private void loadFinanceData(){
        ObservableList<Finance> finances = Model.getInstance().getFinanceRecords();
        filteredFinanceRecords = new FilteredList<>(finances);
        finance_records_table.setItems(filteredFinanceRecords);
    }

    /**
     * Handle finance records remove
     */
    private void onRemoveFinanceRecord(){
        Finance selectedFinance = (Finance) finance_records_table.getSelectionModel().getSelectedItem();
        if (selectedFinance == null){
            AlertUtility.displayError("Pasirinkite pajamų įrašą");
        } else {
            boolean confirmed = AlertUtility.displayConfirmation(
                    "Ar tikrai norite pašalinti pajamų įrašą?"
            );
            if (confirmed){
                Model.getInstance().deleteFinanceRecord(selectedFinance.getId());
                ObservableList<Finance> finances = Model.getInstance().getFinanceRecords();
                finances.remove(selectedFinance);
                AlertUtility.displayInformation("Pajamų įrašas pašalintas sėkmingai");
                loadFinanceData();
            }
        }


    }

    /**
     * Sets row factory for finance records table
     */
    private void setRowFactoryForFinanceRecordsTable(){
        finance_records_table.setRowFactory(tableView -> {
            TableRow<Finance> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())){
                    Finance selectedFinance = row.getItem();
                    editFinanceRecord(selectedFinance);
                }
            });

            return row;
        });
    }

    /**
     * Prepares finance record for editing
     */
    public void onEditFinanceRecord(){
        Finance selectedFinanceRecord = (Finance) finance_records_table.getSelectionModel().getSelectedItem();
        if (selectedFinanceRecord == null){
            AlertUtility.displayError("Pasirinkite pajamų įrašą");
        } else {
            editFinanceRecord(selectedFinanceRecord);
        }
    }

    /**
     *  Opens dialog for editing finance record
     * @param finance
     */
    private void editFinanceRecord(Finance finance){
        Optional<Finance> result = DialogUtility.showEditFinanceDialog(finance);
        result.ifPresent(updateFinanceRecord ->{
            Model.getInstance().updateFinanceRecord(updateFinanceRecord);
            loadFinanceData();
        });
    }

    /**
     * Finance records data filter
     */
    private void applyFilters() {
        filteredFinanceRecords.setPredicate(finance -> {
            if (filter_date_from.getValue() != null && filter_date_to.getValue() != null) {
                if (filter_date_from.getValue().isAfter(filter_date_to.getValue()) ||
                        finance.getPaymentDate() == null ||
                        finance.getPaymentDate().isBefore(filter_date_from.getValue()) ||
                        finance.getPaymentDate().isAfter(filter_date_to.getValue())) {
                    return false;
                }
            }
            return true;
        });
    }
}