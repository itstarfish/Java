package org.benedict.library.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class BookLoan {

    private IntegerProperty id;
    private ObjectProperty<Book> book;
    private ObjectProperty<Reader> reader;
    private ObjectProperty<LocalDate> loanDate;
    private ObjectProperty<LocalDate> returnDate;

    private StringProperty returnStatus;

    /**
     * @param id
     * @param book
     * @param reader
     * @param loanDate
     * @param returnDate
     * @param returnStatus
     */
    public BookLoan(int id, Book book, Reader reader, LocalDate loanDate, LocalDate returnDate, String returnStatus) {
        this.id = new SimpleIntegerProperty(id);
        this.book = new SimpleObjectProperty<>(book);
        this.reader = new SimpleObjectProperty<>(reader);
        this.loanDate = new SimpleObjectProperty<>(loanDate);
        this.returnDate = new SimpleObjectProperty<>(returnDate);
        this.returnStatus = new SimpleStringProperty(returnStatus);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public Book getBook() {
        return book.get();
    }

    public ObjectProperty<Book> bookProperty() {
        return book;
    }

    public void setBook(Book book) {
        this.book.set(book);
    }

    public Reader getReader() {
        return reader.get();
    }

    public ObjectProperty<Reader> readerProperty() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader.set(reader);
    }

    public LocalDate getLoanDate() {
        return loanDate.get();
    }

    public ObjectProperty<LocalDate> loanDateProperty() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate.set(loanDate);
    }

    public LocalDate getReturnDate() {
        return returnDate.get();
    }

    public ObjectProperty<LocalDate> returnDateProperty() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate.set(returnDate);
    }

    public String getReturnStatus() {
        return returnStatus.get();
    }

    public StringProperty returnStatusProperty() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus.set(returnStatus);
    }


}
