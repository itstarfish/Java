package org.benedict.library.Controllers;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.benedict.library.Models.BookLoan;
import org.benedict.library.Models.Model;
import org.benedict.library.Utilities.AlertUtility;
import org.benedict.library.Utilities.DialogUtility;
import org.benedict.library.Views.MenuItems;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookLoansController implements Initializable {
    public Button loan_book_btn;
    public ComboBox filterStatus;
    public Button filterButton;
    public TableView bookLoans_table;
    public TableColumn col_id;
    public TableColumn col_bookTitle;
    public TableColumn col_reader;
    public TableColumn col_loanDate;
    public TableColumn col_returnDate;
    public TableColumn col_status;
    public MenuItem remove_book;
    public MenuItem return_book;

    private FilteredList<BookLoan> filteredBookLoans;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filterStatus.getItems().addAll(BookLoanStatus.OVERDUE.getLabel(), BookLoanStatus.RETURNED.getLabel(), BookLoanStatus.ALL.getLabel());
        filterStatus.getSelectionModel().select(BookLoanStatus.ALL.getLabel());

        loan_book_btn.setOnAction(event -> onBookLoans());
        initTableColumns();
        loadBookLoansData();
        remove_book.setOnAction(event -> onRemoveBookLoan());
        return_book.setOnAction(event -> onReturnBook());

        setRowFactoryForBookLoansTable();



        filterButton.setOnAction(event -> applyFilters());
    }

    /**
     * Open book loans window
     */
    public void onBookLoans() {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(MenuItems.NEW_BOOK_LOAN);
    }

    /**
     * Init the table columns with book loan model
     */
    private void initTableColumns() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_bookTitle.setCellValueFactory(new PropertyValueFactory<>("book"));
        col_reader.setCellValueFactory(new PropertyValueFactory<>("reader"));
        col_loanDate.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
        col_returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("returnStatus"));
    }

    /**
     * Load book loans data into table
     */

    private void loadBookLoansData() {
        ObservableList<BookLoan> bookLoans = Model.getInstance().getBookLoans();
        filteredBookLoans = new FilteredList<>(bookLoans);
        bookLoans_table.setItems(filteredBookLoans);
    }

    /**
     * Handle book loans remove
     */
    private void onRemoveBookLoan() {
        BookLoan selectedBookLoan = (BookLoan) bookLoans_table.getSelectionModel().getSelectedItem();
        if (selectedBookLoan == null) {
            AlertUtility.displayError("Pasirinkite knygos išdavimą");
        } else {
            boolean confirmed = AlertUtility.displayConfirmation(
                    "Ar tikrai norite pašalinti knygos išdavimą?"
            );
            if (confirmed) {
                Model.getInstance().deleteBookLoan(selectedBookLoan.getId());
                ObservableList<BookLoan> bookLoans = Model.getInstance().getBookLoans();
                bookLoans.remove(selectedBookLoan);
                AlertUtility.displayInformation("Knygos išdavimas pašalintas sėkmingai");
                loadBookLoansData();
            }
        }


    }

    /**
     * Handle book return
     */
    private void onReturnBook() {
        BookLoan selectedBookLoan = (BookLoan) bookLoans_table.getSelectionModel().getSelectedItem();
        if (selectedBookLoan == null) {
            AlertUtility.displayError("Pasirinkite knygos išdavimą");
        } else {
            if (selectedBookLoan.getReturnStatus().equals(BookLoanStatus.TAKEN.getLabel())) {
                Optional<BookLoan> result = DialogUtility.showReturnBookDialog(selectedBookLoan);
                result.ifPresent(updatedBookLoan -> {
                    Model.getInstance().returnBook(updatedBookLoan);
                    loadBookLoansData();
                });
                AlertUtility.displayInformation("Knyga grąžinta sėkmingai");
            } else AlertUtility.displayError("Knyga jau gražinta");
        }
    }

    /**
     * Sets row factory for book loans table
     */

    private void setRowFactoryForBookLoansTable() {
        bookLoans_table.setRowFactory(tableView -> {
            TableRow<BookLoan> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    BookLoan selectedBookLoan = row.getItem();
                    editBookLoan(selectedBookLoan);
                }
            });

            return row;
        });
    }


    /**
     * Opens dialog for editing book loan
     *
     * @param bookLoan
     */

    private void editBookLoan(BookLoan bookLoan) {
        Optional<BookLoan> result = DialogUtility.showEditBookLoanDialog(bookLoan);
        result.ifPresent(updatedBookLoan -> {
            Model.getInstance().updateBookLoan(updatedBookLoan);
            loadBookLoansData();
        });
    }

    /**
     * Book Loans data filter
     */

    private void applyFilters() {
        String returnStatusFilter = (String) filterStatus.getValue();
        LocalDate today = LocalDate.now();

        filteredBookLoans.setPredicate(bookLoan -> {
            if (returnStatusFilter.equals(BookLoanStatus.ALL.getLabel())) {
                return true;
            }
            if (returnStatusFilter.equals(BookLoanStatus.RETURNED.getLabel())) {
                return bookLoan.getReturnStatus().equalsIgnoreCase(BookLoanStatus.RETURNED.getLabel());
            }
            if (returnStatusFilter.equals(BookLoanStatus.OVERDUE.getLabel())) {
                return bookLoan.getReturnStatus().equalsIgnoreCase(BookLoanStatus.TAKEN.getLabel())
                        && bookLoan.getReturnDate() != null
                        && bookLoan.getReturnDate().isBefore(today);
            }
            return true;
        });
    }
}

