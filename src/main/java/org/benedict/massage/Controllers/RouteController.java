package org.benedict.massage.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import org.benedict.massage.Models.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class RouteController implements Initializable {
    public BorderPane parent;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().addListener((observable, oldValue, newVal)->{
            switch (newVal){
                case CUSTOMERS:
                    parent.setCenter(Model.getInstance().getViewFactory().getCustomersView());
                    break;
                case CREATE_CUSTOMER:
                    parent.setCenter(Model.getInstance().getViewFactory().getCreateCustomerView());
                    break;
                case FINANCE:
                    parent.setCenter(Model.getInstance().getViewFactory().getFinanceView());
                    break;
                case ADD_FINANCE:
                    parent.setCenter(Model.getInstance().getViewFactory().getAddFinanceView());
                    break;
                case VISITS:
                    parent.setCenter(Model.getInstance().getViewFactory().getVisitsView());
                    break;
                case NEW_VISIT:
                    parent.setCenter(Model.getInstance().getViewFactory().getNewVisitView());
                    break;
                default:
                    parent.setCenter(Model.getInstance().getViewFactory().getVisitsView());
            }
        });
    }
}
