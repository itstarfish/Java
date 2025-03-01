package org.benedict.library.Views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.benedict.library.Controllers.RouteController;

public class ViewFactory {
    private final ObjectProperty<MenuItems> userSelectedMenuItem;
    private AnchorPane dashboard;
    private AnchorPane authorsView;
    private AnchorPane createAuthorView;
    private AnchorPane booksView;
    private AnchorPane addBookView;
    private AnchorPane readersView;
    private AnchorPane addReaderView;
    private AnchorPane newBookLoanView;
    private AnchorPane bookLoansView;

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

    public AnchorPane getDashboardView(){
        if(dashboard == null){
            try {
                dashboard = new FXMLLoader(getClass().getResource("/Fxml/Dashboard.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return dashboard;
    }

    /*
    * Show login window
     */
    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    /*
     * Show register window
     */
    public void showRegsiterWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Register.fxml"));
        createStage(loader);
    }

    /*
    * Show main window
     */
    public void showMainWindow(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Main.fxml"));
        RouteController controller = new RouteController();
        loader.setController(controller);
        createStage(loader);

    }

    /**
     * Load and return authors view
     *
     * @return authorsView
     */
    public AnchorPane getAuthorsView(){
        try{
            authorsView = new FXMLLoader(getClass().getResource("/Fxml/Authors.fxml")).load();
        } catch (Exception e){
            System.out.println(e);
        }
        return authorsView;
    }

    /**
     * Load and return create author view
     *
     * @return createAuthorView
     */
    public AnchorPane getCreateAuthorView(){
        if(createAuthorView == null){
            try{
                createAuthorView = new FXMLLoader(getClass().getResource("/Fxml/CreateAuthor.fxml")).load();
            } catch (Exception e){
                System.out.println(e);
            }
        }
        return createAuthorView;
    }
    /**
     * Load and return books view
     *
     * @return booksView
     */
    public AnchorPane getBooksView(){
            try{
                booksView = new FXMLLoader(getClass().getResource("/Fxml/Books.fxml")).load();
            } catch (Exception e) {
                System.out.println(e);
            }
        return booksView;
    }

    /**
     * Load and return add book view
     *
     * @return addBookView
     */
    public AnchorPane getAddBookView(){
        if(addBookView == null){
            try{
                addBookView = new FXMLLoader(getClass().getResource("/Fxml/AddBook.fxml")).load();
            } catch (Exception e){
                System.out.println(e);
            }
        }
        return addBookView;
    }

    /**
     * Load and return readers view
     *
     * @return readersView
     */
    public AnchorPane getReadersView(){
        try{
            readersView = new FXMLLoader(getClass().getResource("/Fxml/Readers.fxml")).load();
        } catch (Exception e) {
            System.out.println(e);
        }
        return readersView;
    }

    /**
     * Load and return add Reader View
     *
     * @return addReaderView
     */
    public AnchorPane getAddReaderView(){
        if(addReaderView == null){
            try{
                addReaderView = new FXMLLoader(getClass().getResource("/Fxml/AddReader.fxml")).load();
            } catch (Exception e){
                System.out.println(e);
            }
        }
        return addReaderView;
    }

    /**
     * Load and return book Loans View
     *
     * @return bookLoansView
     */
    public AnchorPane getBookLoansView(){
        try{
            bookLoansView = new FXMLLoader(getClass().getResource("/Fxml/BookLoans.fxml")).load();
        } catch (Exception e) {
            System.out.println(e);
        }
        return bookLoansView;
    }

    /**
     * Load and return new Book Loan View
     *
     * @return newBookLoanView
     */
    public AnchorPane getNewBookLoanView(){
        if(newBookLoanView == null){
            try{
                newBookLoanView = new FXMLLoader(getClass().getResource("/Fxml/NewBookLoanView.fxml")).load();
            } catch (Exception e){
                System.out.println(e);
            }
        }
        return newBookLoanView;
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
        stage.setTitle("KITM knygynas");
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
