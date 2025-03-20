package org.benedict.massage.Controllers;

import java.util.Arrays;
import java.util.List;

public enum VisitStatus {
    ALL("Visi"),
    PLANNED("Suplanuotas"),
    CANCELLED("Atšauktas"),
    DONE("Įvykdytas"),
    PAID("Apmokėtas");

    private final String label;

    VisitStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static List<String> getAllLabels() {
        return Arrays.stream(values()).map(VisitStatus::getLabel).toList();
    }

    /**
     * Returns the VisitStatus enum by label or enum
     *
     * @param label the label or enum for search
     * @return the VisitStatus enum
     */
    public static VisitStatus getEnumByLabel(String label) {
        return Arrays.stream(values())
                .filter(status -> status.getLabel().equalsIgnoreCase(label) || status.name().equalsIgnoreCase(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown VisitStatus label or enum: " + label));
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
