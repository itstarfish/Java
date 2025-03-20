package org.benedict.massage.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.benedict.massage.Models.*;
import org.benedict.massage.Utilities.AlertUtility;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddVisitController implements Initializable {

    @FXML
    public ComboBox<Customer> field_customer;
    @FXML
    public DatePicker field_visit_date;
    @FXML
    public Button add_visit_btn;
    public TextArea field_visit_details;

    @FXML
    private Spinner<Integer> startHoursSpinner;
    @FXML
    private Spinner<Integer> startMinutesSpinner;
    @FXML
    private Spinner<Integer> finishHoursSpinner;
    @FXML
    private Spinner<Integer> finishMinutesSpinner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SpinnerValueFactory<Integer> startHoursFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 8);
        SpinnerValueFactory<Integer> finishHoursFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 8);
        startHoursSpinner.setValueFactory(startHoursFactory);
        finishHoursSpinner.setValueFactory(finishHoursFactory);

        SpinnerValueFactory<Integer> startMinutesFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
        SpinnerValueFactory<Integer> finishMinutesFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
        startMinutesSpinner.setValueFactory(startMinutesFactory);
        finishMinutesSpinner.setValueFactory(finishMinutesFactory);

        field_customer.getItems().addAll(Model.getInstance().getCustomers());

        add_visit_btn.setOnAction(event -> onAddVisit());
    }


    private void onAddVisit() {
        if (field_customer.getSelectionModel().isEmpty() ||
                field_visit_date.getValue() == null || startHoursSpinner.getValue() == null ||
                startMinutesSpinner.getValue() == null || finishHoursSpinner.getValue() == null ||
                finishMinutesSpinner.getValue() == null) {
            AlertUtility.displayError("Visi laukai yra privalomi");
        } else {
            Customer selectedCustomer = field_customer.getValue();
            LocalDate visitDate = field_visit_date.getValue();
            LocalTime visitStartTime = getSelectedStartTime();
            LocalTime visitFinishTime = getSelectedFinishTime();
            String visitDetails = field_visit_details.getText();

            Model.getInstance().createVisit(selectedCustomer, visitDate, visitStartTime, visitFinishTime, VisitStatus.PLANNED.getLabel(), visitDetails);

            AlertUtility.displayInformation("Vizitas sėkmingai pridėtas");

            emptyFields();
        }
    }

    private void emptyFields() {
        field_customer.getSelectionModel().clearSelection();
        field_visit_date.setValue(null);
    }

    public LocalTime getSelectedStartTime() {
        int hours = startHoursSpinner.getValue();
        int minutes = startMinutesSpinner.getValue();
        return LocalTime.of(hours, minutes);
    }

    public LocalTime getSelectedFinishTime() {
        int hours = finishHoursSpinner.getValue();
        int minutes = finishMinutesSpinner.getValue();
        return LocalTime.of(hours, minutes);
    }
}