package org.benedict.library.Models;

import javafx.collections.ObservableList;
import org.benedict.library.Views.ViewFactory;
import org.benedict.library.dao.AuthorDAO;
import org.benedict.library.dao.BookDAO;
import org.benedict.library.dao.UserDAO;

import javax.swing.text.View;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Model {
    private static Model model; //Singleton instance
    private final ViewFactory viewFactory;
    public final UserDAO userDAO;
    public final BookDAO bookDAO;
    private boolean loginSuccessFlag;
    private User currentUser;
    public final AuthorDAO authorDAO;

    private Model(){
        this.viewFactory = new ViewFactory();
        this.userDAO = new UserDAO(new DatabaseDriver().getConnection());
        this.currentUser = null;
        this.authorDAO = new AuthorDAO(new DatabaseDriver().getConnection());
        this.bookDAO = new BookDAO(new DatabaseDriver().getConnection());
    }

    /*
    * Return singelton instance  of the Model class
    * @return the singleton instance
     */

    public static synchronized Model getInstance(){
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
     * Create author
     */

    public void createAuthor(String firstName, String lastName, String email, String city){
        authorDAO.create(firstName,lastName,email,city);
    }

    /**
     * Retrieve all authors from DB
     *
     * @return authors
     */
    public ObservableList<Author> getAuthors(){
        return authorDAO.findAll();
    }

    /**
     * Retrieve all author ids from DB
     *
     * @return arraylist with author ids
     */
    public ArrayList<Integer> getAuthorIds(){
        ArrayList<Integer> authorIds = new ArrayList<>();
        for (Author author:getAuthors()) {
            authorIds.add(author.getId());
        }
        return authorIds;
    }


    /**
     * Delete author from DB by id
     *
     * @param id the ID author
     */
    public void deleteAuthor(int id){
        authorDAO.delete(id);
    }

    /**
     * Update existing author in the database
     */

    public void updateAuthor(Author author){
        authorDAO.update(author);
    }


    /**
     * Retrieve all books from DB
     *
     * @return books
     */
    public ObservableList<Book> getBooks(){
        return bookDAO.findAll();
    }

    /**
     * Delete book from DB by id
     *
     * @param id the ID book
     */
    public void deleteBook(int id){
        bookDAO.delete(id);
    }

    /**
     * Add Book 
     * @param isbn
     * @param title
     * @param category
     * @param description
     * @param page_number
     * @param publish_date
     * @param price
     * @param author
     */
    public void addBook(String isbn, String title, String category, String description, int page_number, String publish_date, double price, int author) {
        bookDAO.create(isbn, title, category, description, page_number, publish_date, price, author);
    }

    /**
     * Update existing book in the database
     */

    public void updateBook(Book book){
        authorDAO.update(book);
    }
}
