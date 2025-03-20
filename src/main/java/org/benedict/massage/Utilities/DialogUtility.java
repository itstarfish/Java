package org.benedict.massage.Utilities;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.benedict.massage.Controllers.VisitStatus;
import org.benedict.massage.Models.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class DialogUtility {
    /**
     * Displays dialog for editing Customer information
     * @param customer - the Customer object for editing
     */

    public static Optional<Customer> showEditCustomerDialog(Customer customer){
        Dialog<Customer> dialog = new Dialog<>();
        dialog.setTitle("Redaguoti autorių");
        dialog.setHeaderText("Redaguokite pasirinkto kliento duomenis");

        ButtonType saveButtonType = new ButtonType("Išsaugoti", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType,ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField firstNameField = new TextField(customer.getFirstName());
        TextField lastNameField = new TextField(customer.getLastName());
        TextField emailField = new TextField(customer.getEmail());
        TextField cityField = new TextField(customer.getCity());

        grid.add(new Label("Vardas:"), 0,0);
        grid.add(firstNameField,1,0);
        grid.add(new Label("Pavardė"), 0,1);
        grid.add(lastNameField,1,1);
        grid.add(new Label("El. paštas:"),0,2);
        grid.add(emailField,1,2);
        grid.add(new Label("Miestas:"),0,3);
        grid.add(cityField,1,3);


        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton ->{
            if (dialogButton == saveButtonType){
                customer.setFirstName(firstNameField.getText().trim());
                customer.setLastName(lastNameField.getText().trim());
                customer.setEmail(emailField.getText().trim());
                customer.setCity(cityField.getText().trim());
                return customer;
            }
            return null;
        });

        return dialog.showAndWait();
    }


    /**
     * Displays dialog for editing Visit information
     *
     * @param visit - the Visit object for editing
     */
    public static Optional<Visit> showEditVisitDialog(Visit visit) {
        Dialog<Visit> dialog = new Dialog<>();
        dialog.setTitle("Redaguoti vizitą");
        dialog.setHeaderText("Redaguokite pasirinkto vizito duomenis");

        ButtonType saveButtonType = new ButtonType("Išsaugoti", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        ComboBox<Customer> customerField = new ComboBox<>();
        customerField.getItems().addAll(Model.getInstance().getCustomers());

        for (Customer customer : Model.getInstance().getCustomers()) {
            if (customer.getId() == visit.getCustomer().getId()) {
                customerField.getSelectionModel().select(visit.getCustomer());
                break;
            }
        }

        DatePicker visitDateField = new DatePicker(visit.getVisitDate());

        Spinner<Integer> startHoursSpinner = new Spinner<>(0, 23, visit.getVisitStartTime().getHour());
        Spinner<Integer> startMinutesSpinner = new Spinner<>(0, 59, visit.getVisitStartTime().getMinute());
        HBox startTimeBox = new HBox(5, startHoursSpinner, new Label(":"), startMinutesSpinner);

        Spinner<Integer> finishHoursSpinner = new Spinner<>(0, 23, visit.getVisitFinishTime().getHour());
        Spinner<Integer> finishMinutesSpinner = new Spinner<>(0, 59, visit.getVisitFinishTime().getMinute());
        HBox finishTimeBox = new HBox(5, finishHoursSpinner, new Label(":"), finishMinutesSpinner);

        ComboBox<String> visitStatusField = new ComboBox<>();
        visitStatusField.getItems().addAll(VisitStatus.PLANNED.getLabel(), VisitStatus.CANCELLED.getLabel(), VisitStatus.DONE.getLabel(), VisitStatus.PAID.getLabel());
        visitStatusField.getSelectionModel().select(visit.getVisitStatus().getLabel());

        TextArea detailsField = new TextArea(visit.getDetails());
        detailsField.setPrefHeight(100.0);
        detailsField.setPrefWidth(251.0);
        detailsField.setWrapText(true);

        grid.add(new Label("Klientas:"), 0, 0);
        grid.add(customerField, 1, 0);
        grid.add(new Label("Vizito data:"), 0, 1);
        grid.add(visitDateField, 1, 1);
        grid.add(new Label("Pradžios laikas:"), 0, 2);
        grid.add(startTimeBox, 1, 2);
        grid.add(new Label("Pabaigos laikas:"), 0, 3);
        grid.add(finishTimeBox, 1, 3);
        grid.add(new Label("Vizito būsena:"), 0, 4);
        grid.add(visitStatusField, 1, 4);
        grid.add(new Label("Pastabos apie vizitą:"), 0, 5);
        grid.add(detailsField, 1, 5);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                visit.setCustomer(customerField.getValue());
                visit.setVisitDate(visitDateField.getValue());
                visit.setVisitStartTime(LocalTime.of(startHoursSpinner.getValue(), startMinutesSpinner.getValue()));
                visit.setVisitFinishTime(LocalTime.of(finishHoursSpinner.getValue(), finishMinutesSpinner.getValue()));
                visit.setVisitStatus(VisitStatus.getEnumByLabel(visitStatusField.getValue()));
                visit.setDetails(detailsField.getText().trim());
                return visit;
            }
            return null;
        });

        return dialog.showAndWait();
    }

    /**
     * Displays dialog for adding a finance record for the current customer
     *
     * @param visit - the Visit object for editing
     */

    public static Optional<Visit> showRecordFinanceDialog(Visit visit) {
        Dialog<Visit> dialog = new Dialog<>();
        dialog.setTitle("Registruoti pajamų įrašą");
        dialog.setHeaderText("Nurodykite gautas pajamas.");

        ButtonType saveButtonType = new ButtonType("Išsaugoti", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        DatePicker paymentDate = new DatePicker(LocalDate.now());
        TextField paymentAmountField = new TextField();

        grid.add(new Label("Klientas: " + visit.getCustomer()), 0, 0);
        grid.add(new Label("Mokėjimo data:"), 0, 1);
        grid.add(paymentDate, 1, 1);
        grid.add(new Label("Suma:"), 0, 2);
        grid.add(paymentAmountField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    double paymentAmount = Double.parseDouble(paymentAmountField.getText().trim());
                    if (paymentAmount <= 0) {
                        AlertUtility.displayError("Suma turi būti teigiamas skaičius.");
                        return null;
                    }
                    visit.setVisitStatus(VisitStatus.PAID);
                    Model.getInstance().createFinanceRecord(visit,paymentAmount,paymentDate.getValue());
                } catch (NumberFormatException e) {
                    AlertUtility.displayError("Įveskite teisingą mokėjimo sumą.");
                    return null;
                }
                return visit;
            }
            return null;
        });

        return dialog.showAndWait();
    }

    /**
     * Displays dialog for editing a finance record
     *
     * @param finance - the Finance object for editing
     */
    public static Optional<Finance> showEditFinanceDialog(Finance finance) {
        Dialog<Finance> dialog = new Dialog<>();
        dialog.setTitle("Redaguoti finansų įrašą");
        dialog.setHeaderText("Redaguokite pasirinkto finansų įrašo duomenis");

        ButtonType saveButtonType = new ButtonType("Išsaugoti", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField paymentField = new TextField(String.valueOf(finance.getPayment()));
        DatePicker paymentDateField = new DatePicker(finance.getPaymentDate());

        ComboBox<Visit> visitField = new ComboBox<>();
        visitField.getItems().addAll(Model.getInstance().getVisits());
        visitField.getSelectionModel().select(finance.getVisit());

        grid.add(new Label("Mokėjimas:"), 0, 0);
        grid.add(paymentField, 1, 0);
        grid.add(new Label("Mokėjimo data:"), 0, 1);
        grid.add(paymentDateField, 1, 1);
        grid.add(new Label("Vizitas:"), 0, 2);
        grid.add(visitField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    double payment = Double.parseDouble(paymentField.getText().trim());
                    if (payment <= 0) {
                        AlertUtility.displayError("Mokėjimas turi būti teigiamas skaičius.");
                        return null;
                    }
                    finance.setPayment(payment);
                    finance.setPaymentDate(paymentDateField.getValue());
                    finance.setVisit(visitField.getValue());
                    return finance;
                } catch (NumberFormatException e) {
                    AlertUtility.displayError("Įveskite teisingą mokėjimo sumą.");
                    return null;
                }
            }
            return null;
        });

        return dialog.showAndWait();
    }
}
