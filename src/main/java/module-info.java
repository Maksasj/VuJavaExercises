module com.example.omat {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.omat to javafx.fxml;
    exports com.example.omat;
    exports com.example.omat.controllers;
    opens com.example.omat.controllers to javafx.fxml;
}