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
            stmt.setString(4, description);
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
        if (!(entity instanceof Book)){
            throw new IllegalArgumentException("Excepted Book object");
        }

        Book book = (Book) entity;

        String sql = "UPDATE books SET ISBN = ?, Title = ?, Category = ?, Description = ?, Page_Number = ?, Publish_Date = ?, Price = ?, Author = ? WHERE id = ?";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setString(1, book.getIsbn());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getCategory());
            stmt.setString(4, book.getDescription());
            stmt.setInt(5, book.getPage_number());
            stmt.setString(6, book.getPublish_date());
            stmt.setDouble(7, book.getPrice());
            stmt.setInt(8, book.getAuthor());
            stmt.setInt(9, book.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0){
                logger.info("Book updated "+ book);
            } else{
                logger.warning("No book found with id: " + book.getId());
            }

        }catch (SQLException e ){
            logger.severe("Error updating book: "+ e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0){
                logger.info("Book with ID" + id + " was deleted successfully");
            } else {
                logger.info("No book found with ID " + id);
            }
        }catch (SQLException e){
            logger.severe("Error deleting book: " + e.getMessage());
        }

    }

    @Override
    public ObservableList<Book> findAll(){
        ObservableList<Book> books = FXCollections.observableArrayList();
        String sql = "SELECT id, ISBN, Title, Category, Description, Page_Number, Publish_Date, Price, Author FROM books";

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
