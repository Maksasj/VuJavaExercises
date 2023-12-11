module com.example.tableviewer {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.tableviewer to javafx.fxml;
    exports com.example.tableviewer;
    opens com.example.tableviewer.componets to javafx.fxml;
    exports com.example.tableviewer.componets;
}