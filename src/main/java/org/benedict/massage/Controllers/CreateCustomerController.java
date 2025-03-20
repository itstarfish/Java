package org.benedict.massage.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.benedict.massage.Models.Model;
import org.benedict.massage.Utilities.AlertUtility;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateCustomerController implements Initializable {
    public TextField field_firstName;
    public TextField field_lastName;
    public TextField field_email;
    public TextField field_city;
    public Button create_customer_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_customer_btn.setOnAction(event -> onCustomer());
    }

    private void onCustomer() {

        if (field_firstName.getText().isEmpty() || field_lastName.getText().isEmpty()||field_email.getText().isEmpty()||field_city.getText().isEmpty()){
            AlertUtility.displayError("Visi laukai yra privalomi");
        }else {
            String fName = field_firstName.getText();
            String lName = field_lastName.getText();
            String email = field_email.getText();
            String city = field_city.getText();

            /**
             * Create the customer
             */
            Model.getInstance().createCustomer(fName, lName, email, city);

            AlertUtility.displayInformation("Klientas sėkmingai sukurtas");

            emptyFields();
        }
    }

    private void emptyFields(){
        field_firstName.setText("");
        field_lastName.setText("");
        field_email.setText("");
        field_city.setText("");
    }

}
