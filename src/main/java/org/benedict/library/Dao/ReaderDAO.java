package org.benedict.library.Dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.benedict.library.Models.Author;
import org.benedict.library.Models.Model;
import org.benedict.library.Models.Reader;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Logger;

public class ReaderDAO implements genericDAO {

    private Connection conn;
    private static final Logger logger = Logger.getLogger(ReaderDAO.class.getName());

    public ReaderDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public Object findById(int id) {
        return null;
    }


    public void create(String fName, String lastName, String email, String city) {
        String sql = "INSERT INTO readers(FirstName, LastName, Email, City, Date) VALUES(?,?,?,?,?)";
        int userId = Model.getInstance().getLoggedUserId();

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setString(1, fName);
            stmt.setString(2, lastName);
            stmt.setString(3,email);
            stmt.setString(4,city);
            stmt.setDate(5, Date.valueOf(LocalDate.now()));


            stmt.executeUpdate();

            logger.info("Reader create successfully");
        }catch (SQLException e){
            logger.severe("Error creating reader: "+ e.getMessage());
        }
    }

    @Override
    public void update(Object entity) {
        if (!(entity instanceof Reader)){
            throw new IllegalArgumentException("Excepted Reader object");
        }

        Reader reader = (Reader) entity;

        String sql = "UPDATE readers SET FirstName = ? , LastName = ?, Email = ?, City = ? WHERE id = ?";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setString(1, reader.getFirstName());
            stmt.setString(2, reader.getLastName());
            stmt.setString(3, reader.getEmail());
            stmt.setString(4, reader.getEmail());
            stmt.setInt(5, reader.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0){
                logger.info("Reader updated "+ reader);
            } else{
                logger.warning("No reader found with id: " + reader.getId());
            }

        }catch (SQLException e ){
            logger.severe("Error updating reader: "+ e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM readers WHERE id = ?";
        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0){
                logger.info("Reader with ID" + id + " was deleted successfully");
            } else {
                logger.info("No reader found with ID " + id);
            }
        }catch (SQLException e){
            logger.severe("Error deleting reader: " + e.getMessage());
        }

    }


    public ObservableList<Reader> findAll(){
        ObservableList<Reader> readers = FXCollections.observableArrayList();
        String sql = "SELECT id, FirstName, LastName, Email, City FROM readers";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");
                String city = resultSet.getString("City");
                Reader reader = new Reader(id, firstName,lastName,email,city);
                readers.add(reader);
            }
        }catch (SQLException e){
            logger.severe("Error fetching readers: " + e.getMessage());
        }
        return readers;
    }
}
