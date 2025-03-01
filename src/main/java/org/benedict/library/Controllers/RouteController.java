package org.benedict.library.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import org.benedict.library.Models.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class RouteController implements Initializable {
    public BorderPane parent;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().addListener((observable, oldValue, newVal)->{
            switch (newVal){
                case AUTHORS:
                    parent.setCenter(Model.getInstance().getViewFactory().getAuthorsView());
                    break;
                case CREATE_AUTHOR:
                    parent.setCenter(Model.getInstance().getViewFactory().getCreateAuthorView());
                    break;
                case BOOKS:
                    parent.setCenter(Model.getInstance().getViewFactory().getBooksView());
                    break;
                case ADD_BOOK:
                    parent.setCenter(Model.getInstance().getViewFactory().getAddBookView());
                    break;
                case READERS:
                    parent.setCenter(Model.getInstance().getViewFactory().getReadersView());
                    break;
                case ADD_READER:
                    parent.setCenter(Model.getInstance().getViewFactory().getAddReaderView());
                    break;
                case BOOK_LOANS:
                    parent.setCenter(Model.getInstance().getViewFactory().getBookLoansView());
                    break;
                case NEW_BOOK_LOAN:
                    parent.setCenter(Model.getInstance().getViewFactory().getNewBookLoanView());
                    break;
                default:
                    parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
            }
        });
    }
}
