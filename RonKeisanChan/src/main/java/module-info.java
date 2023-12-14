module com.lab2.rkc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lab2.rkc to javafx.fxml;
    exports com.lab2.rkc;
    exports com.lab2.rkc.credit;
    exports com.lab2.rkc.scenes;
    opens com.lab2.rkc.credit to javafx.fxml;
    opens com.lab2.rkc.scenes to javafx.fxml;
}