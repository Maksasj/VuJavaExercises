module com.example.moody_blues {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.moody_blues.client to javafx.fxml;
    exports com.moody_blues.client;
    exports com.moody_blues.client.common;
    exports com.moody_blues.client.controllers;
    opens com.moody_blues.client.common to javafx.fxml;
    opens com.moody_blues.client.controllers to javafx.fxml;
}