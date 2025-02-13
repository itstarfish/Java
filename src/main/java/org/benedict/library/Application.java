package org.benedict.library;


import javafx.stage.Stage;
import org.benedict.library.Models.Model;
import org.benedict.library.Utilities.AlertUtility;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) {
       if (Model.getInstance().hasRegisteredUsers()){
           Model.getInstance().getViewFactory().showLoginWindow();
       } else{
           AlertUtility.displayInformation("Prieš pradedant darbą su sistema turite sukurti vartotoją");
           Model.getInstance().getViewFactory().showRegsiterWindow();
       }
    }

    public static void main(String[] args) {
        launch();
    }
}