package org.benedict.library.Models;

import javafx.beans.property.*;

public class Book {

    private IntegerProperty id;
    private IntegerProperty author;
    private DoubleProperty price;
    private StringProperty publish_date;
    private IntegerProperty page_number;
    private StringProperty description;
    private StringProperty category;
    private StringProperty title;
    private StringProperty isbn;

    /**
     *
     * @param id
     * @param isbn
     * @param title
     * @param category
     * @param description
     * @param page_number
     * @param publish_date
     * @param price
     * @param author
     */
    public Book(int id, String isbn, String title, String category, String description, int page_number, String publish_date, double price, int author){
        this.id = new SimpleIntegerProperty(id);
        this.isbn = new SimpleStringProperty(isbn);
        this.title = new SimpleStringProperty(title);
        this.category = new SimpleStringProperty(category);
        this.description = new SimpleStringProperty(description);
        this.page_number = new SimpleIntegerProperty(page_number);
        this.publish_date = new SimpleStringProperty(publish_date);
        this.price = new SimpleDoubleProperty(price);
        this.author = new SimpleIntegerProperty(author);
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

    public int getAuthor() {
        return author.get();
    }

    public IntegerProperty authorProperty() {
        return author;
    }

    public void setAuthor(int author) {
        this.author.set(author);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getPublish_date() {
        return publish_date.get();
    }

    public StringProperty publish_dateProperty() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date.set(publish_date);
    }

    public int getPage_number() {
        return page_number.get();
    }

    public IntegerProperty page_numberProperty() {
        return page_number;
    }

    public void setPage_number(int page_number) {
        this.page_number.set(page_number);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    @Override
    public String toString() {
        return String.format("Book [ISBN=%s, Title=%s, Category=%s, Description=%s, Page Number=%s, Publish date=%s, Price=%s, Author=%s]",getIsbn(),getTitle(),getCategory(),getDescription(),getPage_number(),getPublish_date(),getPrice(),getAuthor());
    }
}
