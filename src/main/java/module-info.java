module com.lab2.lab2jfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lab2.lab2jfx to javafx.fxml;
    exports com.lab2.lab2jfx;
}