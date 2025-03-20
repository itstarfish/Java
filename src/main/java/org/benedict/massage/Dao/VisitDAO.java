package org.benedict.massage.Dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.benedict.massage.Models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

public class VisitDAO implements genericDAO{

    private final Connection conn;
    private static final Logger logger = Logger.getLogger(VisitDAO.class.getName());
    public VisitDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Object findById(int id) {
        return null;
    }

    public void create(Customer customer, LocalDate visitDate, LocalTime visitStartTime, LocalTime visitFinishTime, String visitStatus, String details){
        String sql = "INSERT INTO visits(customer_id, visitDate, visitStartTime, visitFinishTime, visitStatus, details) VALUES(?,?,?,?,?,?)";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1, customer.getId());
            stmt.setString(2, String.valueOf(visitDate));
            stmt.setString(3, String.valueOf(visitStartTime));
            stmt.setString(4, String.valueOf(visitFinishTime));
            stmt.setString(5, visitStatus);
            stmt.setString(6, details);

            stmt.executeUpdate();

            logger.info("Visit added successfully");
        }catch (SQLException e){
            logger.severe("Error adding visit: "+ e.getMessage());
        }
    }

    @Override
    public void update(Object entity) {
        if (!(entity instanceof Visit)){
            throw new IllegalArgumentException("Expected Visit object");
        }

        Visit visit = (Visit) entity;

        String sql = "UPDATE visits SET customer_id = ?, visitDate = ?, visitStartTime = ?,visitFinishTime = ?, visitStatus = ?, details = ? WHERE id = ?";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1, visit.getCustomer().getId());
            stmt.setString(2, visit.getVisitDate().toString());
            stmt.setString(3, visit.getVisitStartTime().toString());
            stmt.setString(4, visit.getVisitFinishTime().toString());
            stmt.setString(5, visit.getVisitStatus().getLabel());
            stmt.setString(6, visit.getDetails());
            stmt.setInt(7, visit.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0){
                logger.info("Visit updated ");
            } else{
                logger.warning("No visit found with id: " + visit.getId());
            }

        }catch (SQLException e ){
            logger.severe("Error updating visit: "+ e.getMessage());
        }
    }

    public void changeVisitStatus(Object entity) {
        if (!(entity instanceof Visit)){
            throw new IllegalArgumentException("Excepted Visit object");
        }

        Visit visit = (Visit) entity;

        String sql = "UPDATE visits SET visitStatus = ? WHERE id = ?";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setString(1, visit.getVisitStatus().toString());
            stmt.setInt(2, visit.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0){
                logger.info("visit updated ");
            } else{
                logger.warning("No visit found with id: " + visit.getId());
            }

        }catch (SQLException e ){
            logger.severe("Error updating visit: "+ e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM visits WHERE id = ?";
        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0){
                logger.info("Visit with ID" + id + " was deleted successfully");
            } else {
                logger.info("No visit found with ID " + id);
            }
        }catch (SQLException e){
            logger.severe("Error deleting visit: " + e.getMessage());
        }

    }

    @Override
    public ObservableList<Visit> findAll(){
        ObservableList<Visit> visits = FXCollections.observableArrayList();
        String sql = "SELECT id, customer_id, visitDate, visitStartTime, visitFinishTime, visitStatus, details FROM visits ORDER BY visitDate ASC, visitStartTime ASC";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                Customer customer = Model.getInstance().getCustomerById(resultSet.getInt("customer_id"));
                LocalDate visitDate = LocalDate.parse(resultSet.getString("visitDate"));
                LocalTime visitStartTime = LocalTime.parse(resultSet.getString("visitStartTime"));
                LocalTime visitFinishTime = LocalTime.parse(resultSet.getString("visitFinishTime"));
                String visitStatus = resultSet.getString("visitStatus");
                String details = resultSet.getString("details");

                Visit visit = new Visit(id, customer, visitDate, visitStartTime, visitFinishTime, visitStatus, details);
                visits.add(visit);
            }
        }catch (SQLException e){
            logger.severe("Error fetching visits: " + e.getMessage());
        }
        return visits;
    }
}
