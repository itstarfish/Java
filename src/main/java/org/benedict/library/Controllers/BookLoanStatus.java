package org.benedict.library.Controllers;

public enum BookLoanStatus {
    ALL("Visos"),
    RETURNED("Grąžinta"),
    OVERDUE("Vėluojama grąžinti"),
    TAKEN("Paimta");

    private final String label;

    BookLoanStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
