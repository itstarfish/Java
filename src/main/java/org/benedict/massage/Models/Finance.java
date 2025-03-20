package org.benedict.massage.Models;
import java.time.LocalDate;

import javafx.beans.property.*;

public class Finance {
    private IntegerProperty id;
    private DoubleProperty payment;
    private ObjectProperty<LocalDate> paymentDate;
    private ObjectProperty<Visit> visit;

    public Finance(int id, Visit visit, double payment, LocalDate paymentDate) {
        this.id = new SimpleIntegerProperty(id);
        this.visit = new SimpleObjectProperty<>(visit);
        this.payment = new SimpleDoubleProperty(payment);
        this.paymentDate = new SimpleObjectProperty<>(paymentDate);
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

    public double getPayment() {
        return payment.get();
    }

    public DoubleProperty paymentProperty() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment.set(payment);
    }

    public LocalDate getPaymentDate() {
        return paymentDate.get();
    }

    public ObjectProperty<LocalDate> paymentDateProperty() {
        return paymentDate;
    }

    public Visit getVisit() {
        return visit.get();
    }

    public ObjectProperty<Visit> visitProperty() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit.set(visit);
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate.set(paymentDate);
    }

    @Override
    public String toString() {
        return String.format("Finance{id=%d, payment=%f, paymentDate=%s}", getId(), getPayment(), getPaymentDate());
    }
}
