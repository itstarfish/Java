package org.benedict.massage.Controllers;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.benedict.massage.Models.Customer;
import org.benedict.massage.Models.Finance;
import org.benedict.massage.Models.Visit;
import org.benedict.massage.Models.Model;
import org.benedict.massage.Utilities.AlertUtility;
import org.benedict.massage.Utilities.DialogUtility;
import org.benedict.massage.Views.MenuItems;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class VisitsController implements Initializable {
    public Button visit_btn;
    public ComboBox filterStatus;
    public ComboBox filterTime;
    public Button filterButton;
    public TableView visits_table;
    public TableColumn col_id;
    public TableColumn col_customer;
    public TableColumn col_visitDate;
    public TableColumn col_visitStartTime;
    public TableColumn col_visitFinishTime;
    public TableColumn col_visitStatus;
    public MenuItem remove_visit;
    public MenuItem record_finance;
    public MenuItem view_finance;
    public MenuItem edit_visit;
    public MenuItem view_customer;

    private FilteredList<Visit> filteredVisits;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filterStatus.getItems().addAll(VisitStatus.getAllLabels());
        filterStatus.getSelectionModel().select(VisitStatus.ALL.getLabel());
        filterTime.getItems().addAll(VisitTimeFrame.getAllLabels());

        visit_btn.setOnAction(event -> onVisit());
        initTableColumns();
        loadVisitsData();
        remove_visit.setOnAction(event -> onRemoveVisit());
        record_finance.setOnAction(event -> onRecordFinance());
        view_finance.setOnAction(event -> onViewFinance());
        edit_visit.setOnAction(event -> editVisit((Visit) visits_table.getSelectionModel().getSelectedItem()));
        view_customer.setOnAction(event -> onViewCustomer());

        setRowFactoryForVisitsTable();
        
        filterButton.setOnAction(event -> applyFilters());
    }

    /**
     * Open visits window
     */
    public void onVisit() {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(MenuItems.NEW_VISIT);
    }

    /**
     * Init the table columns with visit model
     */
    private void initTableColumns() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_customer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        col_visitDate.setCellValueFactory(new PropertyValueFactory<>("visitDate"));
        col_visitStartTime.setCellValueFactory(new PropertyValueFactory<>("visitStartTime"));
        col_visitFinishTime.setCellValueFactory(new PropertyValueFactory<>("visitFinishTime"));
        col_visitStatus.setCellValueFactory(new PropertyValueFactory<>("visitStatus"));
    }

    /**
     * Load visits data into table
     */

    private void loadVisitsData() {
        ObservableList<Visit> visits = Model.getInstance().getVisits();
        filteredVisits = new FilteredList<>(visits);
        visits_table.setItems(filteredVisits);
    }

    /**
     * Handle visits remove
     */
    private void onRemoveVisit() {
        Visit selectedVisit = (Visit) visits_table.getSelectionModel().getSelectedItem();
        if (selectedVisit == null) {
            AlertUtility.displayError("Pasirinkite vizitą");
        } else {
            boolean confirmed = AlertUtility.displayConfirmation(
                    "Ar tikrai norite pašalinti vizitą?"
            );
            if (confirmed) {
                Model.getInstance().deleteVisit(selectedVisit.getId());
                ObservableList<Visit> visits = Model.getInstance().getVisits();
                visits.remove(selectedVisit);
                AlertUtility.displayInformation("Vizitas pašalintas sėkmingai");
                loadVisitsData();
            }
        }
    }

    /**
     * Handle view Finance
     */
    private void onViewFinance() {
        Visit selectedVisit = (Visit) visits_table.getSelectionModel().getSelectedItem();
        if (selectedVisit == null) {
            AlertUtility.displayError("Pasirinkite vizitą");
        } else {
            Finance finance = Model.getInstance().getFinanceByVisitID(selectedVisit.getId());
            if (finance != null) {
                DialogUtility.showEditFinanceDialog(finance);
            } else {
                AlertUtility.displayError("Šiam vizitui pajamų įrašo nėra");
            }
        }
    }

    /**
     * Handle view Customer
     */
    private void onViewCustomer() {
        Visit selectedVisit = (Visit) visits_table.getSelectionModel().getSelectedItem();
        if (selectedVisit == null) {
            AlertUtility.displayError("Pasirinkite vizitą");
        } else {
            Customer customer = (Customer) (selectedVisit.getCustomer());
            if (customer != null) {
                DialogUtility.showEditCustomerDialog(customer);
            } else {
                AlertUtility.displayError("Šiam vizitui kliento įrašo nėra");
            }
        }
    }

    /**
     * Handle record finance for visit and status change
     */
    private void onRecordFinance() {
        Visit selectedVisit = (Visit) visits_table.getSelectionModel().getSelectedItem();
        if (selectedVisit == null) {
            AlertUtility.displayError("Pasirinkite vizitą");
        } else {
            if (Model.getInstance().getFinanceByVisitID(selectedVisit.getId()) != null) {
                selectedVisit.setVisitStatus(VisitStatus.PAID);
                Model.getInstance().changeVisitStatus(selectedVisit);
                AlertUtility.displayError("Pajamų įrašas šiam vizitui jau egzistuoja, vizitas pažymėtas kaip apmokėtas");
            } else if (selectedVisit.getVisitStatus() == VisitStatus.PAID) {
                AlertUtility.displayError("Vizitas jau turi pajamų įrašą ir yra apmokėtas");
            } else if (selectedVisit.getVisitStatus() == VisitStatus.DONE) {
                Optional<Visit> result = DialogUtility.showRecordFinanceDialog(selectedVisit);
                result.ifPresent(updatedVisit -> {
                    Model.getInstance().updateVisit(updatedVisit);
                    loadVisitsData();
                });
                AlertUtility.displayInformation("Vizito pajamos išsaugotos sėkmingai");
            } else {
                AlertUtility.displayError("Vizito statusas turi būti įvykdytas norint išsaugoti pajamas");
            }
        }
    }
    
    /**
     * Sets row factory for visits table
     */

    private void setRowFactoryForVisitsTable() {
        visits_table.setRowFactory(tableView -> {
            TableRow<Visit> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Visit selectedVisit = row.getItem();
                    editVisit(selectedVisit);
                }
            });

            return row;
        });
    }


    /**
     * Opens dialog for editing visit
     *
     */

    private void editVisit(Visit selectedVisit) {
        if (selectedVisit == null) {
            AlertUtility.displayError("Pasirinkite vizitą");
        } else {
            Optional<Visit> result = DialogUtility.showEditVisitDialog(selectedVisit);
            result.ifPresent(updatedVisit -> {
                Model.getInstance().updateVisit(updatedVisit);
                loadVisitsData();
            });
        }
    }

    /**
     * Visits data filter
     */

    private void applyFilters() {
        String returnStatusFilter = (String) filterStatus.getValue();
        String returnTimeFilter = (String) filterTime.getValue();
        LocalDate today = LocalDate.now();

        filteredVisits.setPredicate(visit -> {
            boolean statusMatch = false;
            boolean timeMatch = false;

            // Status
            VisitStatus statusFilter = VisitStatus.getEnumByLabel(returnStatusFilter);
            if (statusFilter == VisitStatus.ALL) {
                statusMatch = true;
            } else {
                statusMatch = visit.getVisitStatus() == statusFilter;
            }

            // Time
            if (returnTimeFilter == null || returnTimeFilter.equals(VisitTimeFrame.ALL.getLabel())) {
                timeMatch = true;
            } else if (returnTimeFilter.equals(VisitTimeFrame.TODAY.getLabel())) {
                timeMatch = visit.getVisitDate().equals(today);
            } else if (returnTimeFilter.equals(VisitTimeFrame.TOMORROW.getLabel())) {
                timeMatch = visit.getVisitDate().equals(today.plusDays(1));
            } else if (returnTimeFilter.equals(VisitTimeFrame.WEEK.getLabel())) {
                timeMatch = visit.getVisitDate().isAfter(today.minusDays(1)) &&
                        visit.getVisitDate().isBefore(today.plusDays(7));
            } else if (returnTimeFilter.equals(VisitTimeFrame.MONTH.getLabel())) {
                timeMatch = visit.getVisitDate().getMonth() == today.getMonth() &&
                        visit.getVisitDate().getYear() == today.getYear();
            }

            return statusMatch && timeMatch;
        });
    }
}

