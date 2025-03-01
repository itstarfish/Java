package org.benedict.library.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.benedict.library.Models.Model;
import org.benedict.library.Utilities.AlertUtility;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateReaderController implements Initializable{

    public TextField field_firstName;
    public TextField field_lastName;
    public TextField field_email;
    public TextField field_city;
    public Button create_reader_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_reader_btn.setOnAction(event -> onReader());
    }

    private void onReader() {

        if (field_firstName.getText().isEmpty() || field_lastName.getText().isEmpty()||field_email.getText().isEmpty()||field_city.getText().isEmpty()){
            AlertUtility.displayError("Visi laukai yra privalomi");
        }else {
            String fName = field_firstName.getText();
            String lName = field_lastName.getText();
            String email = field_email.getText();
            String city = field_city.getText();

            /**
             * Create the reader
             */
            Model.getInstance().createReader(fName, lName, email, city);

            AlertUtility.displayInformation("Skaitytojas sėkmingai sukurtas");

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
