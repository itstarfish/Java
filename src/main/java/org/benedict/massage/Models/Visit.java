package org.benedict.massage.Models;

import javafx.beans.property.*;
import org.benedict.massage.Controllers.VisitStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class Visit {

    private final IntegerProperty id;
    private ObjectProperty<Customer> customer;
    private ObjectProperty<LocalDate> visitDate;
    private ObjectProperty<LocalTime> visitStartTime;
    private ObjectProperty<LocalTime> visitFinishTime;
    private StringProperty details;
    private ObjectProperty<VisitStatus> visitStatus;


    public Visit(int id, Customer customer, LocalDate visitDate, LocalTime visitStartTime, LocalTime visitFinishTime, String visitStatus, String details) {
        this.id = new SimpleIntegerProperty(id);
        this.customer = new SimpleObjectProperty<>(customer);
        this.visitDate = new SimpleObjectProperty<>(visitDate);
        this.visitStartTime = new SimpleObjectProperty<>(visitStartTime);
        this.visitFinishTime = new SimpleObjectProperty<>(visitFinishTime);
        this.visitStatus = new SimpleObjectProperty<>(VisitStatus.getEnumByLabel(visitStatus));
        this.details = new SimpleStringProperty(details);
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

    public Customer getCustomer() {
        return customer.get();
    }

    public ObjectProperty<Customer> customerProperty() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer.set(customer);
    }

    public LocalDate getVisitDate() {
        return visitDate.get();
    }

    public ObjectProperty<LocalDate> visitDateProperty() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate.set(visitDate);
    }

    public LocalTime getVisitStartTime() {
        return visitStartTime.get();
    }

    public ObjectProperty<LocalTime> visitStartTimeProperty() {
        return visitStartTime;
    }

    public void setVisitStartTime(LocalTime visitStartTime) {
        this.visitStartTime.set(visitStartTime);
    }

    public LocalTime getVisitFinishTime() {
        return visitFinishTime.get();
    }

    public ObjectProperty<LocalTime> visitFinishTimeProperty() {
        return visitFinishTime;
    }

    public void setVisitFinishTime(LocalTime visitFinishTime) {
        this.visitFinishTime.set(visitFinishTime);
    }

    public VisitStatus getVisitStatus() {
        return visitStatus.get();
    }

    public ObjectProperty<VisitStatus> visitStatusProperty() {
        return visitStatus;
    }

    public void setVisitStatus(VisitStatus visitStatus) {
        this.visitStatus.set(visitStatus);
    }

    public String getDetails() {
        return details.get();
    }

    public StringProperty detailsProperty() {
        return details;
    }

    public void setDetails(String details) {
        this.details.set(details);
    }

    @Override
    public String toString() {
        return String.format("%s, %s %s-%s",
                getCustomer() != null ? getCustomer().toString() : "No Customer",
                getVisitDate() != null ? getVisitDate().toString() : "No Date",
                getVisitStartTime() != null ? getVisitStartTime().toString() : "No Start Time",
                getVisitFinishTime() != null ? getVisitFinishTime().toString() : "No Finish Time"
        );
    }

}
