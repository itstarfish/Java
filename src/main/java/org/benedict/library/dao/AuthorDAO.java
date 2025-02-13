package org.benedict.library.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.benedict.library.Models.Author;
import org.benedict.library.Models.Model;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Logger;

public class AuthorDAO implements genericDAO{

    private Connection conn;
    private static final Logger logger = Logger.getLogger(AuthorDAO.class.getName());

    public AuthorDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public Object findById(int id) {
        return null;
    }


    public void create(String fName, String lastName, String email, String city) {
        String sql = "INSERT INTO authors(FirstName, LastName, Email, City, Date, User_id) VALUES(?,?,?,?,?,?)";
        int userId = Model.getInstance().getLoggedUserId();

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setString(1, fName);
            stmt.setString(2, lastName);
            stmt.setString(3,email);
            stmt.setString(4,city);
            stmt.setDate(5, Date.valueOf(LocalDate.now()));
            stmt.setInt(6,userId);

            stmt.executeUpdate();

            logger.info("Author create successfully");
        }catch (SQLException e){
            logger.severe("Error creating author: "+ e.getMessage());
        }
    }

    @Override
    public void update(Object entity) {
        if (!(entity instanceof Author)){
            throw new IllegalArgumentException("Excepted Author object");
        }

        Author author = (Author) entity;

        String sql = "UPDATE authors SET FirstName = ? , LastName = ?, Email = ?, City = ? WHERE id = ?";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setString(1, author.getFirstName());
            stmt.setString(2, author.getLastName());
            stmt.setString(3, author.getEmail());
            stmt.setString(4, author.getEmail());
            stmt.setInt(5, author.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0){
                logger.info("Author updated "+ author);
            } else{
                logger.warning("No author found with id: " + author.getId());
            }

        }catch (SQLException e ){
            logger.severe("Error updating author: "+ e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM authors WHERE id = ?";
        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0){
                logger.info("Author with ID" + id + " was deleted successfully");
            } else {
                logger.info("No author found with ID " + id);
            }
        }catch (SQLException e){
            logger.severe("Error deleting author: " + e.getMessage());
        }

    }


    public ObservableList<Author> findAll(){
        ObservableList<Author> authors = FXCollections.observableArrayList();
        String sql = "SELECT id, FirstName, LastName, Email, City FROM authors";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");
            String email = resultSet.getString("Email");
            String city = resultSet.getString("City");
            Author author = new Author(id, firstName,lastName,email,city);
            authors.add(author);
        }
        }catch (SQLException e){
            logger.severe("Error fetching authors: " + e.getMessage());
        }
        return authors;
    }
}
