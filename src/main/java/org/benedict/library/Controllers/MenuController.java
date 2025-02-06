package org.benedict.library.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.benedict.library.Models.Model;
import org.benedict.library.Views.MenuItems;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    public Button logout_btn;
    public Text current_user_text;
    public Button authors_btn;
    public Button books_btn;

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
        authors_btn.setOnAction(event -> onAuthors());
        books_btn.setOnAction(event -> onBooks());

    }
    /**
     *  Handle auhors window
     */

    public void onAuthors(){
        //Navigate to author window
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(MenuItems.AUTHORS);
    }

    /**
     * Handle Books window
     */

    public void onBooks(){
        //Navigate to Books window
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(MenuItems.BOOKS);
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
