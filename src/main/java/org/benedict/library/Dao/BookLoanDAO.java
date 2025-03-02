package org.benedict.library.Dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.benedict.library.Models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Logger;

public class BookLoanDAO implements genericDAO{

    private final Connection conn;
    private static final Logger logger = Logger.getLogger(BookLoanDAO.class.getName());
    public BookLoanDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Object findById(int id) {
        return null;
    }

    public void create(Book book, Reader reader, LocalDate loanDate, LocalDate returnDate, String returnStatus){
        String sql = "INSERT INTO bookloans(book_id, reader_id, loan_Date, return_date, return_Status) VALUES(?,?,?,?,?)";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1, book.getId());
            stmt.setInt(2, reader.getId());
            stmt.setString(3, String.valueOf(loanDate));
            stmt.setString(4, String.valueOf(returnDate));
            stmt.setString(5, returnStatus);

            stmt.executeUpdate();

            logger.info("BookLoan added successfully");
        }catch (SQLException e){
            logger.severe("Error adding bookLoan: "+ e.getMessage());
        }
    }

    @Override
    public void update(Object entity) {
        if (!(entity instanceof BookLoan)){
            throw new IllegalArgumentException("Excepted BookLoan object");
        }

        BookLoan bookLoan = (BookLoan) entity;

        String sql = "UPDATE bookloans SET book_id = ?, reader_id = ?, loan_Date = ?, return_date = ?, return_Status = ? WHERE id = ?";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1, bookLoan.getBook().getId());
            stmt.setInt(2, bookLoan.getReader().getId());
            stmt.setString(3, bookLoan.getLoanDate().toString());
            stmt.setString(4, bookLoan.getReturnDate().toString());
            stmt.setString(5, bookLoan.getReturnStatus());
            stmt.setInt(6, bookLoan.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0){
                logger.info("BookLoan updated ");
            } else{
                logger.warning("No bookLoan found with id: " + bookLoan.getId());
            }

        }catch (SQLException e ){
            logger.severe("Error updating bookLoan: "+ e.getMessage());
        }
    }

    public void returnBook(Object entity) {
        if (!(entity instanceof BookLoan)){
            throw new IllegalArgumentException("Excepted BookLoan object");
        }

        BookLoan bookLoan = (BookLoan) entity;

        String sql = "UPDATE bookloans SET return_date = ?, return_Status = ? WHERE id = ?";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setString(1, bookLoan.getReturnDate().toString());
            stmt.setString(2, bookLoan.getReturnStatus());
            stmt.setInt(3, bookLoan.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0){
                logger.info("BookLoan updated ");
            } else{
                logger.warning("No bookLoan found with id: " + bookLoan.getId());
            }

        }catch (SQLException e ){
            logger.severe("Error updating bookLoan: "+ e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM bookloans WHERE id = ?";
        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0){
                logger.info("BookLoan with ID" + id + " was deleted successfully");
            } else {
                logger.info("No bookloan found with ID " + id);
            }
        }catch (SQLException e){
            logger.severe("Error deleting bookLoan: " + e.getMessage());
        }

    }

    @Override
    public ObservableList<BookLoan> findAll(){
        ObservableList<BookLoan> bookLoans = FXCollections.observableArrayList();
        String sql = "SELECT id, book_id, reader_id, loan_date, return_date, return_status FROM bookloans";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                Book book = Model.getInstance().getBookById(resultSet.getInt("book_id"));
                Reader reader = Model.getInstance().getReaderById(resultSet.getInt("reader_id"));
                LocalDate loanDate = resultSet.getObject("loan_date", LocalDate.class);
                LocalDate returnDate = resultSet.getObject("return_date", LocalDate.class);
                String returnStatus = resultSet.getString("return_status");
                
                BookLoan bookLoan = new BookLoan(id, book, reader, loanDate, returnDate, returnStatus);
                bookLoans.add(bookLoan);
            }
        }catch (SQLException e){
            logger.severe("Error fetching book loans: " + e.getMessage());
        }
        return bookLoans;
    }
}
