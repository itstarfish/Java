package org.benedict.massage.Models;

import javafx.collections.ObservableList;
import org.benedict.massage.Dao.*;
import org.benedict.massage.Views.ViewFactory;

import java.time.LocalDate;
import java.time.LocalTime;

public class Model {
    private static Model model; //Singleton instance
    private final ViewFactory viewFactory;
    public final UserDAO userDAO;
    private boolean loginSuccessFlag;
    private User currentUser;
    public final CustomerDAO customerDAO;
    private final FinanceDAO financeDAO;
    private final VisitDAO visitDAO;

    private Model(){
        this.viewFactory = new ViewFactory();
        this.userDAO = new UserDAO(new DatabaseDriver().getConnection());
        this.currentUser = null;
        this.customerDAO = new CustomerDAO(new DatabaseDriver().getConnection());
        this.financeDAO = new FinanceDAO(new DatabaseDriver().getConnection());
        this.visitDAO = new VisitDAO(new DatabaseDriver().getConnection());
    }

    /*
    * Return singelton instance  of the Model class
    * @return the singleton instance
     */

    public static synchronized Model getInstance(){
        if (model == null) {
            synchronized (Model.class) {
                if (model == null) {
                    model = new Model();
                }
            }
        }
        return model;

    }

    /*
    * Get ViewFactory instance
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
        User user = userDAO.findUserByCredentials(userName,password);
        if(user != null){
            this.loginSuccessFlag = true;
            this.currentUser = user;
        }
    }

    /**
     * Get current user name
     *
     * @return userName
     */
    public String getLoggedUserName(){
        return currentUser != null ? currentUser.userNameProperty(): null;
    }

    /** Get current user id
     *
     * @return id - usert id
     */

    public int getLoggedUserId(){
        return currentUser != null ? currentUser.getId() : null;
    }

    /**
     * Check if user exist in system
     *
     * @param userName - user name
     * @return true if user exist
     */

    public boolean isUserExist(String userName){
        return userDAO.isUserExist(userName);
    }

    /**
     * Check if exist users in system
     *
     * @return count of users
     */
    public boolean hasRegisteredUsers(){
        return userDAO.countUsers() > 0;
    }

    /**
     * Create customer
     */

    public void createCustomer(String firstName, String lastName, String email, String city){
        customerDAO.create(firstName,lastName,email,city);
    }

    /**
     * Retrieve all customers from DB
     *
     * @return customers
     */
    public ObservableList<Customer> getCustomers(){
        return customerDAO.findAll();
    }

    /**
     * Retrieve customer by id DB
     *
     * @return customer
     */
    public Customer getCustomerById(int id){
        ObservableList<Customer> customers = getCustomers();
        if(customers != null) {
            for (Customer customer : customers) {
                if (customer.getId() == id) {
                    return customer;
                }
            }
        }
        return null;
    }


    /**
     * Delete customer from DB by id
     *
     * @param id the ID customer
     */
    public void deleteCustomer(int id){
        customerDAO.delete(id);
    }

    /**
     * Update existing customer in the database
     */

    public void updateCustomer(Customer customer){
        customerDAO.update(customer);
    }

    /**
     * Retrieve all finance records from DB
     *
     * @return finance records
     */
    public ObservableList<Finance> getFinanceRecords(){
        return financeDAO.findAll();
    }

    /**
     * Update existing finance record in the database
     */

    public void updateFinanceRecord(Finance finance){
        financeDAO.update(finance);
    }


    /**
     * Create finance record
     */

    public void createFinanceRecord(Visit visit, Double payment, LocalDate paymentDate){
        financeDAO.create(visit,payment,paymentDate);
    }

    /**
     * Delete finance record from DB by id
     *
     * @param id the ID of finence record
     */
    public void deleteFinanceRecord(int id){
        financeDAO.delete(id);
    }

    /**
     * Retrieve all visits from DB
     *
     * @return visits
     */
    public ObservableList<Visit> getVisits() { return visitDAO.findAll();
    }

    /**
     * Update existing visit in the database
     */

    public void updateVisit(Visit visit){
        visitDAO.update(visit);
    }


    /**
     * Create visit
     */

    public void createVisit(Customer customer, LocalDate visitDate, LocalTime visitStartTime, LocalTime visitFinishTime, String visitStatus, String details){
        visitDAO.create(customer, visitDate, visitStartTime, visitFinishTime, visitStatus, details);
    }

    /**
     * Delete visit from DB by id
     *
     * @param id the ID of visit
     */
    public void deleteVisit(int id){
        visitDAO.delete(id);
    }



    /**
     * Retrieve finance record by id DB
     *
     * @return finance
     */
    public Finance getFinanceById(int id){
        ObservableList<Finance> finances = getFinanceRecords();
        if(finances != null) {
            for (Finance finance : finances) {
                if (finance.getId() == id) {
                    return finance;
                }
            }
        }
        return null;
    }

    /**
     * Retrieve visit by id DB
     *
     * @return visit
     */
    public Visit getVisitById(int id){
        ObservableList<Visit> visits = getVisits();
        if(visits != null) {
            for (Visit visit : getVisits()) {
                if (visit.getId() == id) {
                    return visit;
                }
            }
        }
        return null;
    }

    /**
     * Check if visit is not taken already
     *
     * @return boolean
     */
    public boolean isVisitTaken(Visit selectedVisit) {
        ObservableList<Visit> visits = getVisits();
        if (visits == null) {
            for (Visit visit : visits) {
                if (visit.getCustomer().getId() == selectedVisit.getId() && visit.getVisitStatus().equals("Paimta")) {
                    System.out.println("Visitas negalimas, laikas jau užimtas");
                    return true;
                }
            }
        }
        return false;
    }

    public void changeVisitStatus(Visit visit){
        visitDAO.changeVisitStatus(visit);
    }

    /**
     * Check if visit already have a finance record
     *
     * @return finance
     */
    public Finance getFinanceByVisitID(int id) {
        ObservableList<Finance> finances = getFinanceRecords();
        if (finances == null) {
            for (Finance finance : getFinanceRecords()) {
                if (finance.getVisit().getId() == id) {
                    return finance;
                }
            }
        }
        return null;
    }
}
