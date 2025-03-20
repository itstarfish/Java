package org.benedict.massage.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.benedict.massage.Models.Model;
import org.benedict.massage.Utilities.AlertUtility;
import org.benedict.massage.Utilities.UserUtility;

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
        if (Model.getInstance().isUserExist(user_name_field.getText())){
            AlertUtility.displayError("Vartotojas, tokiu vardu sistemoje jau registruotas");
            this.emptyFields();
        } else if(!UserUtility.doPasswordsMatch(password_fld.getText(),repeat_password_field.getText())){
            AlertUtility.displayError("Nesutampa slaptažodžiai");
        }else{
            Model.getInstance().createUser(user_name_field.getText(), password_fld.getText());

            AlertUtility.displayInformation("Vartotojas sėkmingai sukurtas. Prašome prisijungti");

            Model.getInstance().getViewFactory().showLoginWindow();

            Model.getInstance().getViewFactory().closeStage(stage);

        }



    }
    /**
     * Empty form fields
     */
    public void emptyFields(){
        user_name_field.setText("");
        password_fld.setText("");
        repeat_password_field.setText("");
    }
}
