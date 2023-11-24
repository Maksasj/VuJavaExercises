module com.example.moody_blues {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.moody_blues.client to javafx.fxml;
    exports com.moody_blues.client;
}