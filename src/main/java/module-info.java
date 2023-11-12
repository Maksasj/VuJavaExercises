module com.example.omat {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires itextpdf;


    opens com.example.omat to javafx.fxml;
    opens com.example.omat.controllers to javafx.fxml;
    opens com.example.omat.common to javafx.fxml;
    exports com.example.omat;
    exports com.example.omat.controllers;
    exports com.example.omat.common;
    exports com.example.omat.students;
    opens com.example.omat.students to javafx.fxml;
    exports com.example.omat.students.attendance;
    opens com.example.omat.students.attendance to javafx.fxml;
}