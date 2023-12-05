module com.example.tartar_mouse_edition {
    requires javafx.controls;
    requires javafx.fxml;
    requires jaylib;

    opens com.tartar.tartar_mouse_edition.idea to javafx.fxml;
    exports com.tartar.tartar_mouse_edition.idea;
}