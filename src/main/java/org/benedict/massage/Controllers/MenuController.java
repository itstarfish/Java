package org.benedict.massage.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.benedict.massage.Models.Model;
import org.benedict.massage.Views.MenuItems;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    public Button logout_btn;
    public Text current_user_text;
    public Button customers_btn;
    public Button finance_btn;
    public Button visits_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Add listeners to the menu buttons
        current_user_text.setText(Model.getInstance().getLoggedUserName());
        addListenets();
    }


    /**
     *
     *
     */
    private void addListenets(){

        logout_btn.setOnAction(event -> onLogout());
        customers_btn.setOnAction(event -> onCustomers());
        finance_btn.setOnAction(event -> onFinance());
        visits_btn.setOnAction(event -> onVisits());

    }
    /**
     *  Handle customers window
     */

    public void onCustomers(){
        //Navigate to customers window
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(MenuItems.CUSTOMERS);
    }


    /**
     * Handle finance records window
     */

    public void onFinance(){
        //Navigate to finance window
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(MenuItems.FINANCE);
    }

    /**
     * Handle visits window
     */

    public void onVisits(){
        //Navigate to visits window
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(MenuItems.VISITS);
    }


    /**
     * Handle logout event
     */

    public void onLogout(){
        //Create stage
        Stage stage = (Stage)logout_btn.getScene().getWindow();
        //Close stage
        Model.getInstance().getViewFactory().closeStage(stage);
        //Show login window
        Model.getInstance().getViewFactory().showLoginWindow();
        //Destroy login flag
        Model.getInstance().setLoginSuccessFlag(false);
    }

}
