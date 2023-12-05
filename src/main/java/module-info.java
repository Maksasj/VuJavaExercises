module com.example.tartar_mouse_edition {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tartar.tartar_mouse_edition to javafx.fxml;
    exports com.tartar.tartar_mouse_edition;
}