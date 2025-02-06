package org.benedict.library.Models;

import org.benedict.library.Views.ViewFactory;
import org.benedict.library.dao.UserDAO;

import javax.swing.text.View;
import java.time.LocalDate;

public class Model {
    private static Model model; //Singleton instance
    private final ViewFactory viewFactory;
    public final UserDAO userDAO;
    private boolean loginSuccessFlag;

    private Model(){
        this.viewFactory = new ViewFactory();
        this.userDAO = new UserDAO(new DatabaseDriver().getConnection());
    }

    /*
    * Return singelton instance  of the Model class
    * @return the singleton instance
     */

    public static  synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }

        return model;
    }

    /*
    * Get ViewFacctory instance
    * @return ViewFactory instance
     */

    public ViewFactory getViewFactory(){
        return viewFactory;
    }

    /**
     *
     * @return loginSuccessFlag
     */
    public boolean getLoginSuccessFlag(){
        return loginSuccessFlag;
    }

    /**
     * Set login success flag
     * @param flag
     */

    public void setLoginSuccessFlag(boolean flag){
        this.loginSuccessFlag = flag;
    }

    /**
     * Create new user in DB
     *
     * @param userName
     * @param password
     *
     */

    public void createUser(String userName, String password){
        userDAO.createUser(userName, password, LocalDate.now());
    }


    public void checkCredentials(String userName, String password){
        //User DAO
    }
}
