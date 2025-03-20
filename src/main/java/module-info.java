module org.benedict.massage {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;

    opens org.benedict.massage.Controllers to javafx.fxml;

    exports org.benedict.massage;
    exports org.benedict.massage.Controllers;
    exports org.benedict.massage.Models;
    exports org.benedict.massage.Views;
}