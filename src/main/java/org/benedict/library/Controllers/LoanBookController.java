package org.benedict.library.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.benedict.library.Models.*;
import org.benedict.library.Utilities.AlertUtility;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoanBookController implements Initializable{

    public ComboBox field_book;
    public ComboBox field_reader;
    public DatePicker field_return_date;
    public Button loan_book_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        field_book.getItems().addAll(Model.getInstance().getBooks());
        field_reader.getItems().addAll(Model.getInstance().getReaders());
        loan_book_btn.setOnAction(event -> onLoanBook());

    }

    private void onLoanBook() {

        if (field_reader.getSelectionModel().isEmpty() ||
                field_book.getSelectionModel().isEmpty() ||
                field_return_date.getValue() == null){
            AlertUtility.displayError("Visi laukai yra privalomi");
        } else {
            Book selectedBook = (Book) field_book.getValue();
            if (Model.getInstance().isBookLoaned(selectedBook)) {
                AlertUtility.displayError("Ši knyga jau yra paimta");
                return;
            }

            Reader selectedReader = (Reader) field_reader.getValue();
            LocalDate loanDate = LocalDate.now();
            LocalDate returnDate = field_return_date.getValue();
            String status = BookLoanStatus.TAKEN.getLabel();


            /**
             * Create the book loan
             */
            Model.getInstance().createBookLoan(selectedBook, selectedReader, loanDate, returnDate, status);

            AlertUtility.displayInformation("Knyga sėkmingai išduota");

            emptyFields();
        }
    }

    private void emptyFields(){
        field_book.getSelectionModel().clearSelection();
        field_reader.getSelectionModel().clearSelection();
        field_return_date.setValue(null);
    }


}
