package org.benedict.massage.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.benedict.massage.Models.Model;
import org.benedict.massage.Models.Visit;
import org.benedict.massage.Utilities.AlertUtility;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddFinanceRecordController implements Initializable{
    
    public TextField field_payment;
    public Button create_finance_record_btn;
    public ComboBox field_visit;
    public DatePicker field_payment_date;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_finance_record_btn.setOnAction(event -> onFinanceRecord());
        field_visit.setItems(Model.getInstance().getVisits().filtered(visit -> "DONE".equals(visit.getVisitStatus())));
    }

    private void onFinanceRecord() {

        if (field_payment_date.getValue() == null || field_visit.getSelectionModel().isEmpty() || field_payment.getText().isEmpty()) {
            AlertUtility.displayError("Visi laukai yra privalomi");
        } else {
            Double payment = Double.valueOf(field_payment.getText());
            LocalDate paymentDate = field_payment_date.getValue();
            Visit visit = (Visit) field_visit.getSelectionModel().getSelectedItem();

            Model.getInstance().createFinanceRecord(visit, payment, paymentDate);

            AlertUtility.displayInformation("Pajamų įrašas sėkmingai sukurtas");

            emptyFields();
        }
    }

    private void emptyFields() {
        field_visit.getSelectionModel().clearSelection();
        field_payment.clear();
        field_payment_date.setValue(null);
    }

}
