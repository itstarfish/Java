package org.benedict.massage.Controllers;

import java.util.Arrays;
import java.util.List;

public enum VisitTimeFrame {
    ALL("Visi"),
    TODAY("Šiandienos"),
    TOMORROW("Rytdienos"),
    WEEK("Savaitės"),
    MONTH("Mėnesio");

    private final String label;

    VisitTimeFrame(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static List<String> getAllLabels() {
        return Arrays.stream(values()).map(VisitTimeFrame::getLabel).toList();
    }
}
