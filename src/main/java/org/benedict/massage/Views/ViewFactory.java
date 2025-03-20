package org.benedict.massage.Views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.benedict.massage.Controllers.RouteController;

public class ViewFactory {
    private final ObjectProperty<MenuItems> userSelectedMenuItem;
    private AnchorPane dashboard;
    private AnchorPane customersView;
    private AnchorPane createCustomerView;
    private AnchorPane financeView;
    private AnchorPane addFinanceView;
    private AnchorPane newVisitView;
    private AnchorPane visitsView;

    public ViewFactory(){
        this.userSelectedMenuItem = new SimpleObjectProperty<>();
    }

    /**
     * Getter for user selected menu item
     * @return the Object property
     */

    public ObjectProperty<MenuItems> getUserSelectedMenuItem(){
        return userSelectedMenuItem;
    }


    /**
     * Show dashboard
     */

    public AnchorPane getDashboardView() {
        if (dashboard == null) {
            dashboard = loadView("/Fxml/Dashboard.fxml", dashboard);
        }
        return dashboard;
    }

    /*
     * get FXML loader
     *
     * @param fxmlResource
     * @return loader
     */
    public FXMLLoader getLoader(String fxmlResource){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlResource));
        return loader;
    }

    /*
     * Create and show window
     *
     * @param fxmlResource
     * @return view
     */
    private AnchorPane loadView(String fxmlPath, AnchorPane view) {
        if (view == null) {
            try {
                view = getLoader(fxmlPath).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return view;
    }


    /*
    * Show login window
     */
    public void showLoginWindow(){
        createStage(getLoader("/Fxml/Login.fxml"));
    }

    /*
     * Show register window
     */
    public void showRegsiterWindow(){
        createStage(getLoader("/Fxml/Register.fxml"));
    }

    /*
     * Show main window
     */
    public void showMainWindow() {
        FXMLLoader loader = getLoader("/Fxml/Main.fxml");
        RouteController controller = new RouteController();
        loader.setController(controller);
        createStage(loader);
    }

    /**
     * Load and return customers view
     *
     * @return customersView
     */
    public AnchorPane getCustomersView() {
        customersView = loadView("/Fxml/Customers.fxml", customersView);
        return customersView;
    }

    /**
     * Load and return create customer view
     *
     * @return createCustomerView
     */
    public AnchorPane getCreateCustomerView() {
        createCustomerView = loadView("/Fxml/CreateCustomer.fxml", createCustomerView);
        return createCustomerView;
    }

    /**
     * Load and return finance view
     *
     * @return financeView
     */
    public AnchorPane getFinanceView() {
        financeView = loadView("/Fxml/Finance.fxml", financeView);
        return financeView;
    }

    /**
     * Load and return add finance View
     *
     * @return addFinanceView
     */
    public AnchorPane getAddFinanceView() {
        if (addFinanceView == null) {
            addFinanceView = loadView("/Fxml/AddFinanceRecord.fxml", addFinanceView);
        }
        return addFinanceView;
    }

    /**
     * Load and return visit View
     *
     * @return visitView
     */
    public AnchorPane getVisitsView() {
        visitsView = loadView("/Fxml/Visits.fxml", visitsView);
        return visitsView;
    }

    /**
     * Load and return new visit View
     *
     * @return newVisitView
     */
    public AnchorPane getNewVisitView() {
        if (newVisitView == null) {
            newVisitView = loadView("/Fxml/AddVisit.fxml", newVisitView);
        }
        return newVisitView;
    }

    /*
    * Create and display  new stage.
    * @param loader the FXML loader instance. Load fxml file and create scene
     */
    public void createStage(FXMLLoader loader){
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Masažo Salonas");
        stage.show();
    }

    /*
    * Close provided stage
    * @param stage to close
     */
    public void closeStage(Stage stage){
        stage.close();
    }
}
