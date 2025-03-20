package org.benedict.massage.Dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.benedict.massage.Models.Customer;
import org.benedict.massage.Models.Model;

import java.sql.*;
import java.util.logging.Logger;

public class CustomerDAO implements genericDAO{

    private Connection conn;
    private static final Logger logger = Logger.getLogger(CustomerDAO.class.getName());

    public CustomerDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public Object findById(int id) {
        return null;
    }


    public void create(String fName, String lastName, String email, String city) {
        String sql = "INSERT INTO customers(FirstName, LastName, Email, City, User_id) VALUES(?,?,?,?,?)";
        int userId = Model.getInstance().getLoggedUserId();

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setString(1, fName);
            stmt.setString(2, lastName);
            stmt.setString(3,email);
            stmt.setString(4,city);
            stmt.setInt(6,userId);

            stmt.executeUpdate();

            logger.info("Customer create successfully");
        }catch (SQLException e){
            logger.severe("Error creating customer: "+ e.getMessage());
        }
    }

    @Override
    public void update(Object entity) {
        if (!(entity instanceof Customer)){
            throw new IllegalArgumentException("Excepted Customer object");
        }

        Customer customer = (Customer) entity;

        String sql = "UPDATE customers SET FirstName = ? , LastName = ?, Email = ?, City = ? WHERE id = ?";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getEmail());
            stmt.setInt(5, customer.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0){
                logger.info("Customer updated "+ customer);
            } else{
                logger.warning("No customer found with id: " + customer.getId());
            }

        }catch (SQLException e ){
            logger.severe("Error updating customer: "+ e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0){
                logger.info("Customer with ID" + id + " was deleted successfully");
            } else {
                logger.info("No customer found with ID " + id);
            }
        }catch (SQLException e){
            logger.severe("Error deleting customer: " + e.getMessage());
        }

    }


    public ObservableList<Customer> findAll(){
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        String sql = "SELECT id, FirstName, LastName, Email, City FROM customers";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");
            String email = resultSet.getString("Email");
            String city = resultSet.getString("City");
            Customer customer = new Customer(id, firstName,lastName,email,city);
            customers.add(customer);
        }
        }catch (SQLException e){
            logger.severe("Error fetching customers: " + e.getMessage());
        }
        return customers;
    }
}
