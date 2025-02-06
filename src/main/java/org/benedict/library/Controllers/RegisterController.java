package org.benedict.library.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.benedict.library.Models.Model;
import org.benedict.library.Utilities.AlertUtility;
import org.benedict.library.Utilities.UserUtility;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    public TextField user_name_field;
    public PasswordField password_fld;
    public PasswordField repeat_password_field;
    public Button register_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        register_btn.setOnAction(actionEvent -> onRegister());
    }

    public void onRegister(){
        Stage stage = (Stage) register_btn.getScene().getWindow();

        //Check if password match

        if(!UserUtility.doPasswordsMatch(password_fld.getText(),repeat_password_field.getText())){
            AlertUtility.displayError("Nesutampa slaptažodžiai");
        }else{
            Model.getInstance().createUser(user_name_field.getText(), password_fld.getText());

            AlertUtility.displayInformation("Vartotojas sėkmingai sukurtas. Prašome prisijungti");

            Model.getInstance().getViewFactory().showLoginWindow();

            Model.getInstance().getViewFactory().closeStage(stage);

        }



    }
}
