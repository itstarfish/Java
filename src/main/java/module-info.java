module org.benedict.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens org.benedict.library to javafx.fxml;
    exports org.benedict.library;
    exports org.benedict.library.Controllers;
    exports org.benedict.library.Models;
    exports org.benedict.library.Views;
}