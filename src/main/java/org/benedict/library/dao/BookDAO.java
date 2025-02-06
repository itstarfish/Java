package org.benedict.library.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.benedict.library.Models.Author;
import org.benedict.library.Models.Book;
import org.benedict.library.Models.Model;

import java.sql.*;
import java.util.logging.Logger;

public class BookDAO implements genericDAO{
    private final Connection conn;
    private static final Logger logger = Logger.getLogger(BookDAO.class.getName());
    public BookDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Object findById(int id) {
        return null;
    }

    public void create(String isbn, String title, String category, String description, int page_number, String publish_date, Double price, int author){
        String sql = "INSERT INTO Books(ISBN, Title, Category, Description, Page_Number, Publish_Date, Price, Author) VALUES(?,?,?,?,?,?,?,?)";
        int userId = Model.getInstance().getLoggedUserId();

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setString(1, isbn);
            stmt.setString(2, title);
            stmt.setString(3,category);
            stmt.setString(4,description);
            stmt.setInt(5, page_number);
            stmt.setString(6, publish_date);
            stmt.setDouble(7, price);
            stmt.setInt(8, author);

            stmt.executeUpdate();

            logger.info("Book added successfully");
        }catch (SQLException e){
            logger.severe("Error adding book: "+ e.getMessage());
        }
    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(Object id) {

    }

    @Override
    public ObservableList<Book> findAll(){
        ObservableList<Book> books = FXCollections.observableArrayList();
        String sql = "SELECT id, FirstName, LastName, Email, City FROM authors";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String isbn = resultSet.getString("ISBN");
                String title = resultSet.getString("Title");
                String category = resultSet.getString("Category");
                String description = resultSet.getString("Description");
                int page_number = resultSet.getInt("Page_Number");
                String publish_date = resultSet.getString("Publish_Date");
                Double price = resultSet.getDouble("Price");
                int author = resultSet.getInt("Author");

                Book book = new Book(id, isbn, title, category, description, page_number, publish_date, price, author);
                books.add(book);
            }
        }catch (SQLException e){
            logger.severe("Error fetching books: " + e.getMessage());
        }
        return books;
    }
}
