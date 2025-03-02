package org.benedict.library.Models;

import javafx.collections.ObservableList;
import org.benedict.library.Dao.*;
import org.benedict.library.Views.ViewFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class Model {
    private static Model model; //Singleton instance
    private final ViewFactory viewFactory;
    public final UserDAO userDAO;
    public final BookDAO bookDAO;
    private boolean loginSuccessFlag;
    private User currentUser;
    public final AuthorDAO authorDAO;
    private final ReaderDAO readerDAO;
    private final BookLoanDAO bookLoanDAO;

    private Model(){
        this.viewFactory = new ViewFactory();
        this.userDAO = new UserDAO(new DatabaseDriver().getConnection());
        this.currentUser = null;
        this.authorDAO = new AuthorDAO(new DatabaseDriver().getConnection());
        this.bookDAO = new BookDAO(new DatabaseDriver().getConnection());
        this.readerDAO = new ReaderDAO(new DatabaseDriver().getConnection());
        this.bookLoanDAO = new BookLoanDAO(new DatabaseDriver().getConnection());
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
     * Retrieve author by id DB
     *
     * @return author
     */
    public Author getAuthorById(int id){
        for (Author author:getAuthors()) {
            if (author.getId() == id) {
                return author;
            }
        }
        return null;
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
    public void addBook(String isbn, String title, String category, String description, int page_number, String publish_date, double price, Author author) {
        bookDAO.create(isbn, title, category, description, page_number, publish_date, price, author);
    }

    /**
     * Update existing book in the database
     */

    public void updateBook(Book book){
        bookDAO.update(book);
    }

    /**
     * Retrieve all readers from DB
     *
     * @return readers
     */
    public ObservableList<Reader> getReaders(){
        return readerDAO.findAll();
    }

    /**
     * Update existing reader in the database
     */

    public void updateReader(Reader reader){
        readerDAO.update(reader);
    }


    /**
     * Create reader
     */

    public void createReader(String firstName, String lastName, String email, String city){
        readerDAO.create(firstName,lastName,email,city);
    }

    /**
     * Delete reader from DB by id
     *
     * @param id the ID reader
     */
    public void deleteReader(int id){
        readerDAO.delete(id);
    }

    /**
     * Retrieve all book loans from DB
     *
     * @return bookLoans
     */
    public ObservableList<BookLoan> getBookLoans() { return bookLoanDAO.findAll();
    }

    /**
     * Update existing reader in the database
     */

    public void updateBookLoan(BookLoan bookLoan){
        bookLoanDAO.update(bookLoan);
    }


    /**
     * Create reader
     */

    public void createBookLoan(Book book, Reader reader, LocalDate loanDate, LocalDate returnDate, String status){
        bookLoanDAO.create(book, reader, loanDate, returnDate, status);
    }

    /**
     * Delete bookLoan from DB by id
     *
     * @param id the ID bookLoan
     */
    public void deleteBookLoan(int id){
        bookLoanDAO.delete(id);
    }


    /**
     * Retrieve book by id DB
     *
     * @return book
     */
    public Book getBookById(int id){
        for (Book book:getBooks()) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    /**
     * Retrieve reader by id DB
     *
     * @return reader
     */
    public Reader getReaderById(int id){
        for (Reader reader:getReaders()) {
            if (reader.getId() == id) {
                return reader;
            }
        }
        return null;
    }

    /**
     * Retrieve book loan by id DB
     *
     * @return bookloan
     */
    public BookLoan getBookLoanById(int id){
        for (BookLoan bookLoan:getBookLoans()) {
            if (bookLoan.getId() == id) {
                return bookLoan;
            }
        }
        return null;
    }

    /**
     * Check if book to loan is not loaned already
     *
     * @return boolean
     */
    public boolean isBookLoaned(Book selectedBook) {
        for (BookLoan bookLoan:getBookLoans()) {
            if (bookLoan.getBook().getId() == selectedBook.getId() && bookLoan.getReturnStatus().equals("Paimta")) {
                System.out.println("Knyga jau išduota ir negražinta");
                return true;
            }
        }
        return false;
    }

    public void returnBook (BookLoan bookLoan){
        bookLoanDAO.returnBook(bookLoan);
    }
}
