package org.benedict.massage.Dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.benedict.massage.Models.Finance;
import org.benedict.massage.Models.Model;
import org.benedict.massage.Models.Visit;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Logger;

public class FinanceDAO implements genericDAO {

    private Connection conn;
    private static final Logger logger = Logger.getLogger(FinanceDAO.class.getName());

    public FinanceDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public Object findById(int id) {
        return null;
    }

    public void create(Visit visit, Double payment, LocalDate paymentDate){
        String sql = "INSERT INTO finance(visit_id, payment, paymentDate) VALUES(?,?,?)";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1, visit.getId());
            stmt.setDouble(2, payment);
            stmt.setString(3, paymentDate.toString());

            stmt.executeUpdate();

            logger.info("Finance create successfully");
        }catch (SQLException e){
            logger.severe("Error creating finance record: "+ e.getMessage());
        }
    }

    @Override
    public void update(Object entity) {
        if (!(entity instanceof Finance)){
            throw new IllegalArgumentException("Excepted Finance object");
        }

        Finance finance = (Finance) entity;

        String sql = "UPDATE finance SET visit_id = ?, payment = ?, paymentDate = ? WHERE id = ?";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1, finance.getVisit().getId());
            stmt.setDouble(2, finance.getPayment());
            stmt.setString(3, finance.getPaymentDate().toString());
            stmt.setInt(4, finance.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0){
                logger.info("Finance record updated "+ finance);
            } else{
                logger.warning("No finance record found with id: " + finance.getId());
            }

        }catch (SQLException e ){
            logger.severe("Error updating finance record: "+ e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM finance WHERE id = ?";
        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0){
                logger.info("Finance record with ID" + id + " was deleted successfully");
            } else {
                logger.info("No finance record found with ID " + id);
            }
        }catch (SQLException e){
            logger.severe("Error deleting finance recod: " + e.getMessage());
        }

    }


    public ObservableList<Finance> findAll(){
        ObservableList<Finance> financeRecords = FXCollections.observableArrayList();
        String sql = "SELECT id, visit_id, payment, paymentDate FROM finance ORDER BY paymentDate";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                Integer id = resultSet.getInt("id");
                Visit visit = Model.getInstance().getVisitById(resultSet.getInt("visit_id"));
                Double payment = Double.valueOf(resultSet.getString("payment"));
                LocalDate paymentDate = LocalDate.parse(resultSet.getString("paymentDate"));
                Finance finance = new Finance(id, visit, payment, paymentDate);
                financeRecords.add(finance);
            }
        }catch (SQLException e){
            logger.severe("Error fetching finance records: " + e.getMessage());
        }
        return financeRecords;
    }
}
