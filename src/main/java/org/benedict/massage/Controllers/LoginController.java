package org.benedict.massage.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.benedict.massage.Models.Model;
import org.benedict.massage.Utilities.AlertUtility;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public TextField user_name_field;
    @FXML
    public PasswordField password_field;
    @FXML
    public Hyperlink regsiter_link;
    @FXML
    public Button login_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        regsiter_link.setOnAction(actionEvent -> onRegister());
        login_btn.setOnAction(actionEvent -> onLogin());
    }

    /**
     * Handle login action
     */

    public void onLogin(){
        Stage stage = (Stage) regsiter_link.getScene().getWindow();
        //Model.getInstance().getViewFactory().showMainWindow();

        //Check cred
        Model.getInstance().checkCredentials(user_name_field.getText(),password_field.getText());


        //if login success, open dashboard window
        if (Model.getInstance().getLoginSuccessFlag()){
            Model.getInstance().getViewFactory().showMainWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        } else {
            user_name_field.setText("");
            password_field.setText("");
            AlertUtility.displayError("Neteisingi prisijungimo duomenys");
        }
    }

    /**
     * Handle register action
     */
    public void onRegister(){
        Stage stage = (Stage) regsiter_link.getScene().getWindow();
        Model.getInstance().getViewFactory().showRegsiterWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }


}
